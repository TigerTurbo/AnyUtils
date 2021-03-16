package com.yshuoo.anyutils.password;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * 密码工具类
 *
 */
@Slf4j
public class PasswordUtil {

    public final static char[] LOWER_CASES = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public final static char[] UPPER_CASES = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public final static char[] NUMS_LIST = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public final static char[] SYMBOLS_ARRAY = {'_'};


    /***
     * 利用Apache的工具类实现SHA-256加密
     * @param msg 加密前报文
     * @return 加密后报文
     */
    public static String getSHA256(String msg) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(msg.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("getSHA256 occur exception", e);
            throw new IllegalStateException(e);
        }
    }


    /**
     * 密码至少包大小写字母+数字+下划线
     */
    public static boolean checkPwdRobustness(String pwd) {
        int matchCnt = 0;
        if (pwd.matches(".*[0-9].*")) {
            matchCnt++;
        }
        if (pwd.matches(".*[a-z].*")) {
            matchCnt++;
        }
        if (pwd.matches(".*[A-Z].*")) {
            matchCnt++;
        }
        if (pwd.contains("_")) {
            matchCnt++;
        }
        return matchCnt >= 4;
    }

    /**
     * 生成随机密码
     *
     * @param pwdLen 密码长度
     * @return 密码的字符串
     */
    public static String genRandomPwd(int pwdLen) {
        if (pwdLen < 6 || pwdLen > 20) {
            throw new IllegalArgumentException("password length must be rang 6~20!");
        }
        int two = 2;
        int lower = pwdLen / two;
        int upper = (pwdLen - lower) / two;
        int num = (pwdLen - lower) / two;
        int symbol = pwdLen - lower - upper - num;
        StringBuilder pwd = new StringBuilder();
        Random random = new Random();
        int position;
        while ((lower + upper + num + symbol) > 0) {
            if (lower > 0) {
                position = random.nextInt(pwd.length() + 1);
                pwd.insert(position, LOWER_CASES[random.nextInt(LOWER_CASES.length)]);
                lower--;
            }
            if (upper > 0) {
                position = random.nextInt(pwd.length() + 1);
                pwd.insert(position, UPPER_CASES[random.nextInt(UPPER_CASES.length)]);
                upper--;
            }
            if (num > 0) {
                position = random.nextInt(pwd.length() + 1);
                pwd.insert(position, NUMS_LIST[random.nextInt(NUMS_LIST.length)]);
                num--;
            }
            if (symbol > 0) {
                position = random.nextInt(pwd.length() + 1);
                pwd.insert(position, SYMBOLS_ARRAY[random.nextInt(SYMBOLS_ARRAY.length)]);
                symbol--;
            }
        }
        return pwd.toString();
    }

}
