package com.haih.util.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA1 {

    /**
     * 串接arr参数，生成sha1 digest
     *
     * @param arr
     * @return
     */
    public static String gen(String... arr) throws NoSuchAlgorithmException {
      Arrays.sort(arr);
      StringBuilder sb = new StringBuilder();
      for (String a : arr) {
        sb.append(a);
      }
      return genStr(sb.toString());
    }
    
    public static String genStr(String str) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        sha1.update(str.getBytes());
        byte[] output = sha1.digest();
        return bytesToHex(output);
    }
    
    private static String bytesToHex(byte[] b) {
        char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }
}
