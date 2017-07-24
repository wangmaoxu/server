package com.nantimes.vicloth.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.log.Log;
import com.nantimes.vicloth.model.bo.FaceServerReply;




public class RenderSocket {
	private static final String sRemoveCmd="/home/wmx/asset/remove.sh";
	private static final String sUnzipCmd="/home/wmx/asset/unzip.sh";
	private static ServerSocket server,heartServer;
	private static Socket heart;
	private RenderSocket(){

		
	}

	public static void Init(){
		initServerSocket();
		initHeartSocket();
	}
	
	private static  void initServerSocket(){
		try {
			server = new ServerSocket(12347);
			accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	private static void initHeartSocket(){
	
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
				
					try {
						heartServer = new ServerSocket(43218);
				        while(true)  
				        {  
				        	Socket s = heartServer.accept();  
				            Thread t = new Thread( new heartHandler(s));  
				            t.start();  
				        }  
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			});
			
			thread.start();
	
		
	}
	
	private static void accept() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
			       Socket s = null;  
			        while(true)  
			        {  
			            try {
							s = server.accept();
						    Thread t = new Thread( new Handler(s));  
					        t.start(); 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
			        
			        }  
				
			}
		});
		thread.start();
		
		
	}

	public static void destroy(){
//		if(server !=null){
//			try {
//				server.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(heartServer !=null){
//			try {
//				heartServer.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}
	public static Socket  getHeart(){
		return heart;
	}
	
	static class Handler implements Runnable{
		 private Socket s ;  
	      
		    public Handler(Socket s )  
		    {  
		        this.s = s;  
		    }  

		@Override
		public void run() {
			byte[] buff = new byte[2026];
			InputStream is;
			try {
				is = s.getInputStream();
				is.read(buff);
				String json = new String(buff,"UTF-8").trim();
				System.out.println("reply:"+json);
				FaceServerReply reply=JSON.parseObject(json, FaceServerReply.class);
				if(reply.getKey().equals("unzip")){
					String cmd[] = new String[2];
					cmd[0]=sUnzipCmd;
					cmd[1]=URLDecoder.decode(reply.getAddress(), "utf-8");
					System.out.println("address:"+cmd[1]);
					CmdExec.doExec(cmd);
				}else {
					String cmd[] = new String[2];
					cmd[0]=sRemoveCmd;
					cmd[1]=reply.getUsername();
					CmdExec.doExec(cmd);
					Thread thread =AsyncThread.getChaceByKey(reply.getKey());
					if(thread !=null){
						thread.interrupt();
					}
				}
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			
		}
		
		
	}
	
	static class heartHandler implements Runnable{
		private Socket socket;
   	 	boolean run=true;  
   	 	private long receiveTimeDelay=1000*60; 
        long lastReceiveTime = System.currentTimeMillis();  
		public heartHandler(Socket socket) {
			heart = socket;
			this.socket = socket;
		}

		@Override
		public void run() {
			while(run){
				  if(System.currentTimeMillis()-lastReceiveTime>receiveTimeDelay){  
	                    overThis();  
	                }else{ 
	                	InputStream in;
						try {
							if(socket.isClosed()){
								heart=null;
								break;
							}
							in = socket.getInputStream();
						  	if(in.available()>0){
						  		byte[] b = new byte[100];
						  		
								in.read(b);
								String key = new String(b,"UTF-8").trim();
								System.out.println("key:"+key.trim());
		                		lastReceiveTime = System.currentTimeMillis();  
		                		
		                	}else{
		                		Thread.sleep(1000*10); 
		                	}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							overThis();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							overThis();
						}
	              
	                }
			}
			
		}
		
		private void overThis() {
			System.out.println("socket.close");
	         if(run)run=false;  
	            if(socket!=null){  
	                try {  
	                	socket.close();  
	                	heart = null;
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            } 
			
		}
		
	}

}
