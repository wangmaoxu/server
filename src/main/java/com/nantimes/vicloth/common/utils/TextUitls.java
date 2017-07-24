package com.nantimes.vicloth.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TextUitls {
	   public static String Convertinput2String(InputStream inputStream)
	    {
	        String str = "";
	        // ByteArrayOutputStream相当于内存输出流
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        // 将输入流转移到内存输出流中
	        try
	        {
	            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
	            {
	                out.write(buffer, 0, len);
	            }
	            // 将内存流转换为字符串
	            str = new String(out.toByteArray());
	        }
	        catch (IOException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return str;
	    }
	   public static boolean StringNotNull(String str){
		   if(str != null && !str.equals("")){
			   return true;
		   }
		   return false ;
	   }
	
}
