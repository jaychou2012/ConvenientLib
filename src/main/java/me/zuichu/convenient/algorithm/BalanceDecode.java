package me.zuichu.convenient.algorithm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Balance解密
 * V0.1 2018.11.25
 */
public class BalanceDecode {
    private String uid = "";
    private int uidLength = 32;
    private String[] keyMap;
    private ArrayList<long[]> block0 = new ArrayList<>();
    private ArrayList<long[]> block1 = new ArrayList<>();
    private ArrayList<long[]> block2 = new ArrayList<>();
    private ArrayList<long[]> block3 = new ArrayList<>();
    private ArrayList<long[]> block4 = new ArrayList<>();
    private ArrayList<long[]> block5 = new ArrayList<>();
    private ArrayList<long[]> block6 = new ArrayList<>();
    private ArrayList<long[]> block7 = new ArrayList<>();
    private long begin, end = 0;

    private BalanceDecode() {

    }

    private static class BalanceDecodeHolder {
        private final static BalanceDecode instance = new BalanceDecode();
    }

    public static BalanceDecode getInstance() {
        return BalanceDecodeHolder.instance;
    }

    public void setBlockNumber(int block) {
        Balance.block = block;
    }

    public void setDataWidth(int width) {
        Balance.dataWidth = width;
    }

    public String getKey(String fileUrl) {
        return Balance.getKey(fileUrl);
    }

    public void balanceDecode(String fileUrl, String key) {
        begin = System.currentTimeMillis();
        keyMap = getKeyMappingStringDe(key);
        getBlocks(keyMap);
        try {
            File file = new File(fileUrl);
            RandomAccessFile accessFile = new RandomAccessFile(file, Balance.MODE);
            for (int i = 0; i < Balance.block; i++) {
                if (i == 0) {
                    accessFile.seek(block0.get(0)[3]);
                    accessFile.write(getBufferBytes(block0), 0, Balance.dataWidth);
                } else if (i == 1) {
                    accessFile.seek(block1.get(0)[3]);
                    accessFile.write(getBufferBytes(block1), 0, Balance.dataWidth);
                } else if (i == 2) {
                    accessFile.seek(block2.get(0)[3]);
                    accessFile.write(getBufferBytes(block2), 0, Balance.dataWidth);
                } else if (i == 3) {
                    accessFile.seek(block3.get(0)[3]);
                    accessFile.write(getBufferBytes(block3), 0, Balance.dataWidth);
                } else if (i == 4) {
                    accessFile.seek(block4.get(0)[3]);
                    accessFile.write(getBufferBytes(block4), 0, Balance.dataWidth);
                } else if (i == 5) {
                    accessFile.seek(block5.get(0)[3]);
                    accessFile.write(getBufferBytes(block5), 0, Balance.dataWidth);
                } else if (i == 6) {
                    accessFile.seek(block6.get(0)[3]);
                    accessFile.write(getBufferBytes(block6), 0, Balance.dataWidth);
                } else if (i == 7) {
                    accessFile.seek(block7.get(0)[3]);
                    accessFile.write(getBufferBytes(block7), 0, Balance.dataWidth);
                }
            }
            //数字和+数字间距=权重
            //求余、取余、调整长度、与链接变量进行循环运算
            //int存9位长数值
            //后32位存储2倍数据，即64位数据
            //[2:10,81][3:0-255][6:0-353535]
            //[block+index+10][data+index][keyWeight]
            accessFile.close();
            end = (System.currentTimeMillis() - begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getKeyMappingStringDe(String string) {
        String key = Balance.base64Decode(string);
        uid = key.substring(key.length() - uidLength);
        String mapString = key.substring(0, key.length() - uidLength);
        return mapString.split(",");
    }

    private void getBlocks(String[] keyMap) {
        ArrayList<int[]> blocks = new ArrayList<>();
        for (int i = 0; i < keyMap.length; i++) {
            parseData(keyMap[i], i);
        }
        sortByBlockIndex();
    }

    /**
     * 解析
     *
     * @param keyString 原始字串数据
     * @param index     索引
     * @return 块号，数据
     */
    private void parseData(String keyString, int index) {
        long[] key = new long[4];
        int keyWeight = Integer.parseInt(keyString.substring(keyString.length() - 8, keyString.length())) - 10000000;//keyWeight
        key[0] = Integer.parseInt(keyString.substring(0, 2)) - 10 - index;//blockNum
        key[1] = Integer.parseInt(keyString.substring(2, 4)) - 10 - index;//blockIndex
        key[2] = Integer.parseInt(keyString.substring(4, 7)) - index - 100;//data
        key[3] = Long.parseLong(keyString.substring(7, keyString.length() - 8)) - keyWeight;//start
        if ((int) key[0] == 0) {
            block0.add(key);
        } else if ((int) key[0] == 1) {
            block1.add(key);
        } else if ((int) key[0] == 2) {
            block2.add(key);
        } else if ((int) key[0] == 3) {
            block3.add(key);
        } else if ((int) key[0] == 4) {
            block4.add(key);
        } else if ((int) key[0] == 5) {
            block5.add(key);
        } else if ((int) key[0] == 6) {
            block6.add(key);
        } else if ((int) key[0] == 7) {
            block7.add(key);
        }
    }

    /**
     * 块内排序
     */
    private void sortByBlockIndex() {
        Collections.sort(block0, new SortByBlockIndex());
        Collections.sort(block1, new SortByBlockIndex());
        Collections.sort(block2, new SortByBlockIndex());
        Collections.sort(block3, new SortByBlockIndex());
        Collections.sort(block4, new SortByBlockIndex());
        Collections.sort(block5, new SortByBlockIndex());
        Collections.sort(block6, new SortByBlockIndex());
        Collections.sort(block7, new SortByBlockIndex());
    }

    class SortByBlockIndex implements Comparator {
        public int compare(Object o1, Object o2) {
            long[] k1 = (long[]) o1;
            long[] k2 = (long[]) o2;
            if (k1[1] > k2[1]) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * 解密key转byte
     *
     * @param key
     * @return
     */
    public static byte[] getBufferBytes(ArrayList<long[]> key) {
        byte[] bytes = new byte[Balance.dataWidth];
        for (int i = 0; i < Balance.dataWidth; i++) {
            bytes[i] = Balance.intToByte((int) key.get(i)[2]);
        }
        return bytes;
    }

    public long getDecodeMills() {
        return end;
    }
}
