package com.adpostm.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptPassword {
	
	public static String getEncryptedPassword(String password) {
		
		try {
			final MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			md5Digest.update(password.getBytes());
			final byte messageDigest[] = md5Digest.digest();
			final StringBuffer hexString = new StringBuffer();
			for(final byte elememnt : messageDigest) {
				final String hex = Integer.toHexString(0xFF & elememnt);
				if(hex.length() == 1)
						hexString.append('0');
				
				hexString.append(hex);
				password = hexString.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return password;
	}
}
