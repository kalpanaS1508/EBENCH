package com.ebench.Utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class VerificationCode {
//    public static String generateHash(String valueToBeHashed) throws NoSuchAlgorithmException {
//        String stringToHash = valueToBeHashed;
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        messageDigest.update(stringToHash.getBytes());
//        byte[] digest = messageDigest.digest();
//        return DatatypeConverter.printHexBinary(digest);
//    }
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
