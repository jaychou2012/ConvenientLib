package me.zuichu.convenient.algorithm;

import sun.rmi.transport.proxy.RMISocketInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Balance加密
 * V0.1 2018.11.25
 */
public class BalanceEncode {
    private static long RMax;
    private static long RMin;
    private static int RNumber;
    private static long R = RMin;
    private static long[] Rstart = new long[Balance.block - 2];
    private String[] mappingString = new String[Balance.block * Balance.dataWidth];
    private long begin, end = 0;

    private BalanceEncode() {

    }

    private static class BalanceEncodeHolder {
        private final static BalanceEncode instance = new BalanceEncode();
    }

    public static BalanceEncode getInstance() {
        return BalanceEncodeHolder.instance;
    }

    public void setBlockNumber(int block) {
        Balance.block = block;
    }

    public void setDataWidth(int width) {
        Balance.dataWidth = width;
    }

    public void saveKey(String fileUrl, String key) {
        Balance.setKey(fileUrl, key);
    }

    public String balanceEncode(String fileUrl) {
        File file = new File(fileUrl);
        begin = System.currentTimeMillis();
        long[] starts = getBlock(file.length());
        String uid = Balance.getUID().replace("-", "");
        String[] keys = getSeriesString(uid);
        String[] rdatas = getSeriesString(Balance.stringMd5(uid));
        int[] randomMap = randomMapping(0, (Balance.block * Balance.dataWidth) - 1, Balance.block * Balance.dataWidth);
        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, Balance.MODE);
            byte[] buffer = new byte[Balance.dataWidth];
            for (int i = 0; i < Balance.block; i++) {
                accessFile.seek(starts[i]);
                accessFile.read(buffer, 0, Balance.dataWidth);
                accessFile.seek(starts[i]);
                accessFile.write(getKeyByteString(rdatas[i]), 0, Balance.dataWidth);
                getStringMapping(buffer, i, starts[i], keys[i], randomMap);
                //UUID+MD5共8位一组=替换串
                //UUID每组重排序acsii码拼接=权重值
            }
            accessFile.close();
            end = (System.currentTimeMillis() - begin);
            String[][] mapping = getMatrixMapping(randomMap);
            return getKeyString(mapping, uid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取块信息
     * 4位8块
     *
     * @return start, index
     */
    private long[] getBlock(long length) {
        RMax = length - 2 * Balance.dataWidth;
        RMin = Balance.dataWidth + 1;
        R = RMin;
        RNumber = 0;
        Rstart = new long[Balance.block - 2];

        long[] blocks = new long[Balance.block];
        long[] start = getNextRandom();
        for (int i = 0; i < Balance.block; i++) {
            if (i == 0) {
                blocks[0] = 0;
            } else if (i == (Balance.block - 1)) {
                blocks[i] = length - Balance.dataWidth;
            } else {
                blocks[i] = start[i - 1];
            }
        }
        return blocks;
    }

    /**
     * 随机分块取每块开始值
     *
     * @return
     */
    private long[] getNextRandom() {
        //线性同余方程
        if ((RMax - R) < (R - RMin)) {//right
            RMax = R - Balance.dataWidth;
        } else {//left
            RMin = R + Balance.dataWidth;
        }
        if (RNumber != Balance.block - 2) {
            getLongRandom(RMin, RMax);
            getNextRandom();
        } else {
            return Rstart;
        }
        return Rstart;
    }

    private void getLongRandom(long min, long max) {
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        R = rangeLong;
        Rstart[RNumber] = R;
        RNumber++;
    }

    /**
     * 分组
     *
     * @param string 32位
     * @return
     */
    private String[] getSeriesString(String string) {
        String[] array = new String[Balance.block];
        for (int i = 0; i < Balance.block; i++) {
            array[i] = string.substring(i * Balance.dataWidth, (i + 1) * Balance.dataWidth);
        }
        return array;
    }

    private int[] randomMapping(int min, int max, int n) {
        int len = max - min + 1;
        if (max < min || n > len) {
            return null;
        }
        // 初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            // 待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            // 将随机到的数放入结果集
            result[i] = source[index];
            // 将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    private byte[] getKeyByteString(String key) {
        byte[] bytes = new byte[Balance.dataWidth];
        for (int i = 0; i < Balance.dataWidth; i++) {
            bytes[i] = Balance.intToByte(Integer.parseInt(key.substring(i, i + 1), 16));
        }
        return bytes;
    }

    private void getStringMapping(byte[] bytes, int blockNum, long start, String key, int[] randomMap) {
        for (int i = 0; i < bytes.length; i++) {
            mappingString[randomMap[blockNum * Balance.dataWidth + i]] = computeData(randomMap[blockNum * Balance.dataWidth + i], i
                    , blockNum, start, Balance.byteToInt(bytes[i]), Balance.getWeight(key));
        }
    }

    /**
     * 计算存储的数据
     *
     * @param index      存储索引
     * @param blockIndex 块内索引
     * @param blockNum   块号
     * @param data       原始数据byte值[]
     * @param keyWeight  权重
     * @return 9?
     */
    private String computeData(int index, int blockIndex, int blockNum, long start, int data, int keyWeight) {
        return (blockNum + index + 10) + "" + (blockIndex + index + 10) + (data + index + 100) + (start + keyWeight) + (keyWeight + 10000000);
        //[2:10,81][2:10:46][3:0-255][不固定:start][8:0-35353535]
        //[block+index+10][blockIndex+index+10][data+index+100][start+keyWeight][keyWeight+10000000]
//        原始数据mod块号+索引号+替代的字符串的原始or对称=存储数据
    }

    /**
     * 重复了似乎
     *
     * @param randomMap
     * @return
     */
    private String[][] getMatrixMapping(int[] randomMap) {
        String[][] mapping = new String[Balance.block][Balance.dataWidth];
        for (int i = 0; i < randomMap.length; i++) {
            mapping[randomMap[i] / Balance.dataWidth][randomMap[i] % Balance.dataWidth] = mappingString[randomMap[i]];
        }
        return mapping;
    }

    private String getKeyString(String[][] mapping, String key) {
        String mapString = matrixToString(mapping);
        String keyString = Balance.base64Encode(mapString + key);
        return keyString;
    }

    /**
     * matrix转String
     *
     * @param mapping
     * @return
     */
    public static String matrixToString(String[][] mapping) {
        String text = "";
        for (int i = 0; i < Balance.block; i++) {
            for (int j = 0; j < Balance.dataWidth; j++) {
                if (i == Balance.block - 1 && j == Balance.dataWidth - 1) {
                    text += mapping[i][j];
                } else {
                    text += mapping[i][j] + ",";
                }
            }
        }
        return text;
    }

    public long getEncodeMills() {
        return end;
    }
}
