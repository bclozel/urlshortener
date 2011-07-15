package org.pullrequest.shorturl.service;

/**
 * 
 * Codec Service that encodes/decodes decimal numbers from/to a 64 base.
 * A few examples:
 * <li> 1 => "0"</li>
 * <li> 11 => "a"</li>
 * <li> 63 => "="</li>
 * <li> 64 => "10"</li>
 * 
 */
public class CodecService {

    public static final String codeset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-=";
    public static final long base = 64;
    public static final long MAX_LENGTH = 6;
    public static final long MAX_UID = Math.round(Math.pow(base,MAX_LENGTH));

    /**
     * Pick a random number in the following interval:
     * [1,MAX_UID-1]
     * @return a random uid
     */
    public static long generateUID() {
        
        return (long)(Math.random()*MAX_UID-2)+1;
    }

    /**
     * Encode a number in another space using the given chars codeset
     * @param uid number to be encoded
     * @return encoded string
     * @see CodecService.codeset
     */
    public static String encode(long uid) {

        String hash = "";

        while (uid > 0) {

            int idx = (int) (uid % base);

            hash = codeset.charAt(idx) + hash;
            uid = (long)Math.floor(uid / base);
        }

        return hash;

    }

    /**
     * Decode a string  from the given codeset to its long decimal value
     * @param hash string to be decoded
     * @return decimal value
     * @see CodecService.codeset
     */
    public static long decode(String hash) {

        long uid = 0;
        int idx = 0;
        int length = hash.toCharArray().length;

        for (int aChar : hash.toCharArray()) {

            int n = codeset.indexOf(aChar);
            if (n == -1) {
                return 0;
            }

            uid += n * Math.pow(base, length - idx - 1);

            idx++;
        }

        return uid;
    }
}
