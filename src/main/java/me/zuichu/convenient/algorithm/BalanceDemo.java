package me.zuichu.convenient.algorithm;

public class BalanceDemo {
    private static BalanceEncode encode;
    private static BalanceDecode decode;
    private static String fileUrl = "D:/qq.exe";
    private static String keyUrl = "D:/key.txt";

    public static void main(String[] args) {
        encode = BalanceEncode.getInstance();
        decode = BalanceDecode.getInstance();
        //encode
//        encode();
        //decode
        decode();
    }

    private static void encode() {
        String key = encode.balanceEncode(fileUrl);
        encode.saveKey(keyUrl, key);
        System.out.println("encode：" + encode.getEncodeMills());
        System.out.println("encode：" + key);
    }

    private static void decode() {
        String key = decode.getKey(keyUrl);
        decode.balanceDecode(fileUrl, key);
        System.out.println("decode：" + decode.getDecodeMills());
        System.out.println("decode：" + key);
    }
}
