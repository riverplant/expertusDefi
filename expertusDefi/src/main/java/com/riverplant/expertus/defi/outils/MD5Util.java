package com.riverplant.expertus.defi.outils;
import java.security.*;
public class MD5Util {
	/**
	 * 
	 * @param array
	 * @return
	 */
  public static String hex(byte[] array) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
      sb.append(Integer.toHexString((array[i]
          & 0xFF) | 0x100).substring(1,3));        
      }
      return sb.toString();
  }
  /**
   * 
   * @param message
   * @return
   */
  public static String md5Hex (String message) {
      try {
      MessageDigest md = 
          MessageDigest.getInstance("MD5");
      return hex (md.digest());
      } catch (NoSuchAlgorithmException e) {
    	  e.printStackTrace();
      } 
      return null;
  }
}