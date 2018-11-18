package me.zuichu.convenient.compute;

/**
 * 数据计算类，部分方法需要JDK 1.8+（JDK1.8或以上版本），有备注
 */
public class Compute {

    private char[] strChar;

    public static String getBinaryChar(String string) {
        char[] strChar = string.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    public static int getAscIICode(String letter) {
        byte[] i = letter.getBytes();
        int ascii = 0;
        for (byte b : i) {
            ascii = b;
            System.out.println(ascii);
        }
        return ascii;
    }

    public static String getAscIICodeString(String string) {
        byte[] i = string.getBytes();
        String ascii = "";
        for (byte b : i) {
            ascii += b + " ";
        }
        System.out.println(ascii);
        return ascii.trim();
    }

    /**
     * 返回a的b次幂
     *
     * @param a
     * @param b
     * @return
     */
    public static double getPow(double a, double b) {
        return Math.pow(a, b);
    }

    public static double getE() {
        return Math.E;
    }

    public static double getPI() {
        return Math.PI;
    }

    /**
     * 返回f乘以2的scaleFactor次方的值
     * JDK 1.8+
     *
     * @param f
     * @param scaleFactor
     * @return
     */
    public static float scalb(float f, int scaleFactor) {
        return Math.scalb(f, scaleFactor);
    }

    /**
     * 返回f乘以2的scaleFactor次方的值
     * JDK 1.8+
     *
     * @param d
     * @param scaleFactor
     * @return
     */
    public static double scalb(double d, int scaleFactor) {
        return Math.scalb(d, scaleFactor);
    }

    /**
     * 返回比f小一点的浮点数
     * JDK 1.8+
     *
     * @param f
     * @return
     */
    public static float nextDown(float f) {
        return Math.nextDown(f);
    }

    /**
     * 返回比d小一点的double类型数
     * JDK 1.8+
     *
     * @param d
     * @return
     */
    public static double nextDown(double d) {
        return Math.nextDown(d);
    }

    /**
     * 返回比f大一点的浮点数
     * (返回 f 和正无穷大之间与 f 相邻的浮点值)
     * JDK 1.8+
     *
     * @param f
     * @return
     */
    public static float nextUp(float f) {
        return Math.nextUp(f);
    }

    /**
     * 返回比d大一点的double类型数
     * (返回 d 和正无穷大之间与 d 相邻的浮点值)
     * JDK 1.8+
     *
     * @param d
     * @return
     */
    public static double nextUp(double d) {
        return Math.nextUp(d);
    }

    /**
     * 返回第一个参数和第二个参数之间与第一个参数相邻的浮点数。
     * 如果两个参数比较起来相等，则返回第二个参数
     * JDK 1.8+
     *
     * @param start
     * @param direction
     * @return
     */
    public static float nextAfter(float start, double direction) {
        return Math.nextAfter(start, direction);
    }

    /**
     * 返回第一个参数和第二个参数之间与第一个参数相邻的double类型数。
     * 如果两个参数比较起来相等，则返回第二个参数
     * JDK 1.8+
     *
     * @param start
     * @param direction
     * @return
     */
    public static double nextAfter(double start, double direction) {
        return Math.nextAfter(start, direction);
    }

    /**
     * 返回float参数使用的无偏指数，log以2为底f的整数对数
     * JDK 1.8+
     *
     * @param f
     * @return
     */
    public static int getExponent(float f) {
        return Math.getExponent(f);
    }

    /**
     * 返回double参数使用的无偏指数，log以2为底d的整数对数
     * JDK 1.8+
     *
     * @param d
     * @return
     */
    public static int getExponent(double d) {
        return Math.getExponent(d);
    }

    /**
     * 返回带有第二个浮点参数符号的第一个浮点参数
     * JDK 1.8+
     *
     * @param magnitude
     * @param sign
     * @return
     */
    public static float copySign(float magnitude, float sign) {
        return Math.copySign(magnitude, sign);
    }

    /**
     * 返回带有第二个浮点参数符号的第一个浮点参数
     * JDK 1.8+
     *
     * @param magnitude
     * @param sign
     * @return
     */
    public static double copySign(double magnitude, double sign) {
        return Math.copySign(magnitude, sign);
    }

    /**
     * 返回x+1的以e为底的自然对数
     *
     * @param x
     * @return
     */
    public static double log1p(double x) {
        return Math.log1p(x);
    }

    /**
     * 返回x-1的以e为底的自然对数
     *
     * @param x
     * @return
     */
    public static double expm1(double x) {
        return Math.expm1(x);
    }

    /**
     * 对于给定的直角三角形的两个直角边，求其斜边的长度
     *
     * @param x
     * @param y
     * @return
     */
    public static double hypot(double x, double y) {
        return Math.hypot(x, y);
    }

    /**
     * 求x的双曲正切值
     *
     * @param x
     * @return
     */
    public static double tanh(double x) {
        return Math.tanh(x);
    }

    /**
     * 求x的双曲余弦值
     *
     * @param x
     * @return
     */
    public static double cosh(double x) {
        return Math.cosh(x);
    }

    /**
     * 求x的双曲正弦值
     *
     * @param x
     * @return
     */
    public static double sinh(double x) {
        return Math.sinh(x);
    }

    /**
     * 返回f的符号，0返回0，正数返回1.0，负数返回-1.0
     *
     * @param f
     * @return
     */
    public static float signum(float f) {
        return Math.signum(f);
    }

    /**
     * 返回d的符号，0返回0，正数返回1.0，负数返回-1.0
     *
     * @param d
     * @return
     */
    public static double signum(double d) {
        return Math.signum(d);
    }

    /**
     * 返回参数的ulp大小
     * 假设在float 2.0和3.0之间有8,388,609个数，
     * 那么在2.0和3.0之间的数的ulp就是8,388,609/1.0约等于0.0000001
     *
     * @param f
     * @return
     */
    public static float ulp(float f) {
        return Math.ulp(f);
    }

    /**
     * 返回参数的ulp大小
     * 假设在float 2.0和3.0之间有8,388,609个数，
     * 那么在2.0和3.0之间的数的ulp就是8,388,609/1.0约等于0.0000001
     *
     * @param d
     * @return
     */
    public static double ulp(double d) {
        return Math.ulp(d);
    }

    public static double min(double a, double b) {
        return Math.min(a, b);
    }

    public static float min(float a, float b) {
        return Math.min(a, b);
    }

    public static long min(long a, long b) {
        return Math.min(a, b);
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static double max(double a, double b) {
        return Math.max(a, b);
    }

    public static float max(float a, float b) {
        return Math.max(a, b);
    }

    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static double abs(double a) {
        return Math.abs(a);
    }

    public static float abs(float a) {
        return Math.abs(a);
    }

    public static long abs(long a) {
        return Math.abs(a);
    }

    public static int abs(int a) {
        return Math.abs(a);
    }

    /**
     * floorDiv(x, y) * y + floorMod(x, y) == x
     * 返回最大的（最接近正无穷大）double值，
     * 该值小于等于参数，并等于某个整数
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static long floorMod(long x, long y) {
        return Math.floorMod(x, y);
    }

    /**
     * floorDiv(x, y) * y + floorMod(x, y) == x
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static int floorMod(int x, int y) {
        return Math.floorMod(x, y);
    }

    /**
     * 返回小于等于代数商的最大整数
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static long floorDiv(long x, long y) {
        return Math.floorDiv(x, y);
    }

    /**
     * 返回小于等于代数商的最大整数
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static long floorDiv(int x, int y) {
        return Math.floorDiv(x, y);
    }

    /**
     * 求Long的int值，超出范围则抛异常
     * JDK 1.8+
     *
     * @param value
     * @return
     */
    public static int toIntExact(long value) {
        return Math.toIntExact(value);
    }

    /**
     * 求参数的符号取反值
     * JDK 1.8+
     *
     * @param a
     * @return
     */
    public static long negateExact(long a) {
        return Math.negateExact(a);
    }

    /**
     * 求参数的符号取反值
     * JDK 1.8+
     *
     * @param a
     * @return
     */
    public static long negateExact(int a) {
        return Math.negateExact(a);
    }

    /**
     * 返回参数的减1值，溢出抛出异常
     * JDK 1.8+
     *
     * @param a
     * @return
     */
    public static long decrementExact(long a) {
        return Math.decrementExact(a);
    }

    /**
     * 返回参数的减1值，溢出抛出异常
     * JDK 1.8+
     *
     * @param a
     * @return
     */
    public static long decrementExact(int a) {
        return Math.decrementExact(a);
    }

    /**
     * 返回参数的加1的值，溢出抛出异常
     * JDK 1.8+
     *
     * @param a
     * @return
     */
    public static long incrementExact(long a) {
        return Math.incrementExact(a);
    }

    /**
     * 返回参数的加1的值，溢出抛出异常
     * JDK 1.8+
     *
     * @param a
     * @return
     */
    public static long incrementExact(int a) {
        return Math.incrementExact(a);
    }

    /**
     * 返回参数的乘积
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static long multiplyExact(long x, long y) {
        return Math.multiplyExact(x, y);
    }

    /**
     * 返回参数的乘积
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static int multiplyExact(int x, int y) {
        return Math.multiplyExact(x, y);
    }

    /**
     * 返回参数的差
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static long subtractExact(long x, long y) {
        return Math.subtractExact(x, y);
    }

    /**
     * 返回参数的差
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static int subtractExact(int x, int y) {
        return Math.subtractExact(x, y);
    }

    /**
     * 求参数和
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static long addExact(long x, long y) {
        return Math.addExact(x, y);
    }

    /**
     * 求参数和
     * JDK 1.8+
     *
     * @param x
     * @param y
     * @return
     */
    public static int addExact(int x, int y) {
        return Math.addExact(x, y);
    }

    /**
     * 返回0-1之间的随机double类型数
     *
     * @return
     */
    public static double random() {
        return Math.random();
    }

    /**
     * 返回参数的四舍六入五成双
     *
     * @param a
     * @return
     */
    public static long round(double a) {
        return Math.round(a);
    }

    /**
     * 返回参数的四舍六入五成双
     *
     * @param a
     * @return
     */
    public static int round(float a) {
        return Math.round(a);
    }

    /**
     * 从X轴正向逆时针旋转到点(x,y) 时经过的角度，弧度。-PI到PI
     *
     * @param y
     * @param x
     * @return
     */
    public static double atan2(double y, double x) {
        return Math.atan2(y, x);
    }

    /**
     * 返回最接近参数的整数,如果有2个数同样接近,则返回偶数的那个
     *
     * @param a
     * @return
     */
    public static double rint(double a) {
        return Math.rint(a);
    }

    /**
     * 返回不大于参数的整数
     *
     * @param a
     * @return
     */
    public static double floor(double a) {
        return Math.floor(a);
    }

    /**
     * 返回不小于参数的最小整数
     *
     * @param a
     * @return
     */
    public static double ceil(double a) {
        return Math.ceil(a);
    }

    /**
     * 余数的算术值等于f1 - f2 × n,
     * 其中n是整数数学最接近商的确切数学值f1/f2,
     * 而如果两个整数都同样接近f1/f2，则n是整数，它是偶数
     *
     * @param f1
     * @param f2
     * @return
     */
    public static double IEEEremainder(double f1, double f2) {
        return Math.IEEEremainder(f1, f2);
    }

    /**
     * 计算参数的实立方根
     *
     * @param a
     * @return
     */
    public static double cbrt(double a) {
        return Math.cbrt(a);
    }

    /**
     * 计算参数的实平方根
     *
     * @param a
     * @return
     */
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    /**
     * 返回log以10为底的对数值
     *
     * @param a
     * @return
     */
    public static double log10(double a) {
        return Math.log10(a);
    }

    /**
     * 返回log以e为底的对数值
     *
     * @param a
     * @return
     */
    public static double log(double a) {
        return Math.log(a);
    }

    /**
     * 以e为底以a为指数的值
     *
     * @param a
     * @return
     */
    public static double exp(double a) {
        return Math.exp(a);
    }

    /**
     * 将参数转化为角度
     *
     * @param angrad
     * @return
     */
    public static double toDegrees(double angrad) {
        return Math.toDegrees(angrad);
    }

    /**
     * 将参数(角度)转化为弧度
     *
     * @param angdeg
     * @return
     */
    public static double toRadians(double angdeg) {
        return Math.toRadians(angdeg);
    }

    /**
     * 求指定double类型参数的反正切值
     *
     * @param a
     * @return
     */
    public static double atan(double a) {
        return Math.atan(a);
    }

    /**
     * 求指定double类型参数的反余弦值
     *
     * @param a
     * @return
     */
    public static double acos(double a) {
        return Math.acos(a);
    }

    /**
     * 求指定double类型参数的反正弦值
     *
     * @param a
     * @return
     */
    public static double asin(double a) {
        return Math.asin(a);
    }

    /**
     * 求指定double类型参数的正切值
     *
     * @param a
     * @return
     */
    public static double tan(double a) {
        return Math.tan(a);
    }

    /**
     * 求指定double类型参数的余弦值
     *
     * @param a
     * @return
     */
    public static double cos(double a) {
        return Math.cos(a);
    }

    /**
     * 求指定double类型参数的正弦值
     *
     * @param a
     * @return
     */
    public static double sin(double a) {
        return Math.sin(a);
    }
}
