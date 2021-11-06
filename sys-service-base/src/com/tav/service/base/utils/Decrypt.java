/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 *
 * @author hunglq9
 */
public class Decrypt {

	public static void main(String[] args) {
	     	StandardPBEStringEncryptor  textEncryptor = new StandardPBEStringEncryptor ();
	        textEncryptor.setPassword("123456a@");
	        System.err.println("=========DYCRIPT=========");
	        System.err.println(textEncryptor.encrypt("jdbc:postgresql://127.0.0.1:5432/mangxahoi"));
	        
                System.err.println(textEncryptor.encrypt("postgres"));
	        System.err.println(textEncryptor.encrypt("root"));
                
//                System.err.println(textEncryptor.decrypt("vb05tA5sZ9vPekhPWz3x0NqRpB6ZHf+0qQ8GX+X5tuOTnY/VxXBiw/JsKZtlCo+d"));
//	        System.err.println(textEncryptor.decrypt("s6HiRLmQY9d+OZ8XfAuG7MNdJYZWDlDRGFSna3nd5bo7sIO0kg9ATzTIRzP/dQ1ftS7grfMI+v0="));
	        
	    }

}
