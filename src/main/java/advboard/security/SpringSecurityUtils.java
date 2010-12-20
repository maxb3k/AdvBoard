package advboard.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.MessageDigest;
import java.util.Formatter;

public class SpringSecurityUtils
{
    /**
     * Retrun true if current user is Anon
     * @return true only if current user is Anon
     */
    public static boolean isAnonymous()
    {
        return AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass());
    }

    /**
     * Converts string into md5 hex string using UTF8 charset.
     *
     * @param message - string to convert
     * @return message encoded in MD5 as Hex
     */
    public static String md5Encode(String message)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest((message).getBytes("UTF-8"));
            return asHex(hash);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * The laziest and slowest way to convert bytes to hex string.
     *
     * @param bytes - byte array
     * @return String containing hex representation of bytes
     */
    public static String asHex(byte[] bytes)
    {
        Formatter formatter = new Formatter();
        for (byte b : bytes)
        {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
