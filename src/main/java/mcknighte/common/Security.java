package mcknighte.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Static security class to provide library methods such as generating random salts and hashing strings
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
public class Security {
    // ANS. (2012). Creating a random string with A-Z and 0-9 in Java. Retrieved from http://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
    /**
     * Generate a 64 character random salt
     *
     * @return the 64 character random salt
     */
    public static String generateRandomSalt() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 64) {
            int index = (int) (rnd.nextFloat() * chars.length());
            salt.append(chars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    /**
     * Get the SHA512 hash of a string without a salt
     *
     * @param passwordToHash the string to hash using SHA512
     * @return the SHA512 hashed version of the specified string
     */
    public static String sha512(String passwordToHash) {
        return sha512(passwordToHash, ""); // Call the sha512 method without a salt
    }

    // Abhi. (2013). Hash a password with SHA-512 in Java. Retrieved from http://stackoverflow.com/questions/33085493/hash-a-password-with-sha-512-in-java
    /**
     * Hash a string using SHA512 with a specified salt
     *
     * @param passwordToHash the string to hash using SHA512
     * @param salt the salt to use when hashing
     * @return the SHA512 salted and hashed version of the specified string
     */
    public static String sha512(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        return generatedPassword;
    }
}
