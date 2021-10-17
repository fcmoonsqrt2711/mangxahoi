package com.tav.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class PassWordUtil { 
    private static Logger logger = Logger.getLogger(PassWordUtil.class);
    private static PassWordUtil instance;

    private PassWordUtil() {
    }

    public synchronized String encrypt(String plaintext) throws Exception {
        
        MessageDigest md = MessageDigest.getInstance("SHA-1"); // step 2
        md.update(plaintext.getBytes("UTF-8")); // step 3
        byte raw[] = md.digest(); // step 4
        return (new BASE64Encoder()).encode(raw); // step 5
    }

    public static synchronized PassWordUtil getInstance() // step 1
    {
        if (instance == null) {
            instance = new PassWordUtil();
        }
        return instance;
    }

    public static void main(String[] arg) throws Exception {
        System.out.println(":::::::" + PassWordUtil.getInstance().encrypt("pmtc"));

    }
}
