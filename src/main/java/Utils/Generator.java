package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alouache Rabah
 */
public class Generator {

    public static final String MD5_ALGORITHM = "MD5";
    public static final String SHA_256_ALGORITHM = "SHA-256";

    public static String hashMD5(String str) {
        return Generator.encode(str, Generator.MD5_ALGORITHM);
    }

    public static String hashSHA256(String str) {
        return Generator.encode(str, Generator.SHA_256_ALGORITHM);
    }

    public static String encode(String password, String algorithm) {
        byte[] hash = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
            hash = md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException ex) {

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                sb.append(0);
                sb.append(hex.charAt(hex.length() - 1));
            } else {
                sb.append(hex.substring(hex.length() - 2));
            }
        }
        return sb.toString();
    }

}
