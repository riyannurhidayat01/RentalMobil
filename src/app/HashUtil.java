/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author ASUS
 */
import org.mindrot.jbcrypt.BCrypt;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtil {
    public static String bcryptHash(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    public static String pbkdf2Hash(String text) throws Exception {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        PBEKeySpec spec = new PBEKeySpec(text.toCharArray(), salt, 65536, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    public static String scryptHash(String text) {
        return com.lambdaworks.crypto.SCryptUtil.scrypt(text, 16384, 8, 1);
    }
}

