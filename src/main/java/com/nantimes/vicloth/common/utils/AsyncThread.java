package com.nantimes.vicloth.common.utils;

import java.util.HashMap;
import java.util.Map;

public class AsyncThread {
	private static Map<String, Thread> asyncMap = new HashMap<>();
	
	public static Thread getChaceByKey(String key){
		Thread chace = asyncMap.get(key);
		asyncMap.remove(key);
		return chace;
	}
	
	public static void setChacheThread(String key,Thread thread){
		Thread oldChache = asyncMap.get(key);
		if(oldChache !=null){
			oldChache.interrupt();
		}else {
			asyncMap.put(key, thread);
		}
		
	}
	
	

}
