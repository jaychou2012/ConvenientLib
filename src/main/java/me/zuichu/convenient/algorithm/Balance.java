package me.zuichu.convenient.algorithm;

import java.io.*;
import java.security.*;
import java.util.*;

/**
 * Balance通用
 * V0.1 2018.11.25
 */
public class Balance {
    public static String MODE = "rwd";
    private static String[] strings = {"0", "1", "2",
            "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z"};
    public static int block = 8;
    public static int dataWidth = 4;

    /**
     * 计算权重值
     *
     * @param string 4位
     * @return
     */
    public static int getWeight(String string) {
        char[] strChar = string.toCharArray();
        String weight = "";
        for (int i = 0; i < strChar.length; i++) {
            boolean num = Character.isDigit(strChar[i]);
            if (num) {
                weight += strChar[i];
            } else {
                int index = Arrays.binarySearch(strings, strChar[i] + "");
                weight += index + "";
            }
        }
        return Integer.parseInt(weight);
    }

    public static String getUID() {
        return UUID.randomUUID().toString();
    }

    public static void setKey(String url, String key) {
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(url);
            fwriter.write(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getKey(String url) {
        try {
            FileReader reader = new FileReader(url);
            BufferedReader br = new BufferedReader(reader);
            String line;
            String string = "";
            while ((line = br.readLine()) != null) {
                string += line;
            }
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String stringMd5(String input) {
        String mode = "MD5";
        try {
            //拿到一个MD5转换器（如果想要SHA1加密参数换成"SHA1"）
            MessageDigest messageDigest = MessageDigest.getInstance(mode);
            //输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            //inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            //转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            //字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * byte转int
     *
     * @param b
     * @return
     */
    public static int byteToInt(byte b) {
        return b & 0xFF;
    }

    public static byte intToByte(int i) {
        return (byte) i;
    }

    /**
     * base64解码
     *
     * @param text
     * @return
     */
    public static String base64Decode(String text) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            return new String(decoder.decode(text), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * base64编码
     *
     * @param text
     * @return
     */
    public static String base64Encode(String text) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }


    public static String byteArrayToHex(byte[] byteArray) {
        //首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        //new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符）
        char[] resultCharArray = new char[byteArray.length * 2];
        //遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        //字符数组组合成字符串返回
        return new String(resultCharArray);
    }


    public static String StrToBinStr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        System.out.println("bin:" + result);
        return result;
    }

    //将二进制字符串转换为char
    public static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    //将二进制字符串转换成int数组
    public static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    public static String toStrings(String a) {
        String[] arr = a.split("\\s+");
        String sss = "";
        for (String ss : arr) {
            sss = sss + BinstrToChar(ss);
        }
        return sss;
    }

}
