package com.nantimes.vicloth.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdExec {
	
	public static boolean doExec(String[] args){
		long start = System.currentTimeMillis();
		 try {
			 StringBuffer buff = new StringBuffer();
			 for(String arg :args){
				 buff.append(arg).append("  ");
			 }
			 System.out.println("cmd:"+buff.toString());
			Process process =Runtime.getRuntime().exec(args);
			process.waitFor();
			StringBuilder sb = new StringBuilder("echo:");
			String line = null;
			InputStream is= process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) !=null ) {
				
				sb.append(line)
				.append("\n");
			}
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 long end = System.currentTimeMillis();
		 System.out.println("time of cmd:"+(end -start)/1000.0+"s");
		
		return true;
		
		
	}

}
