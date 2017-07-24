package com.nantimes.vicloth.controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.nantimes.vicloth.common.utils.AsyncThread;
import com.nantimes.vicloth.common.utils.RenderSocket;
import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.model.bo.FaceServerReply;
import com.nantimes.vicloth.model.bo.FaceServerSend;
import com.nantimes.vicloth.model.vo.BodyView;
import com.nantimes.vicloth.model.vo.UserView;
import com.nantimes.vicloth.service.IBodyService;

@Controller
@RequestMapping("/body")
public class BodyCotroller {
	@Autowired IBodyService _bodyService;
	
	Logger logger;
	String key;
	
	private  BodyCotroller() {
		logger = Logger.getLogger(BodyCotroller.class);
	}
	
	@RequestMapping(value="/load",method = RequestMethod.GET)
	@ResponseBody
	public BodyView load(@RequestBody String id){
		return	_bodyService.find(Integer.valueOf(id));
		
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	@ResponseBody
	public VResponse update(@RequestBody BodyView bodyView ,@RequestParam String uuid){
		bodyView.setId(Integer.valueOf(uuid));
		_bodyService.update(bodyView);
		
		return new VResponse();
		
	}
	
	@RequestMapping(value ="/upload",method = RequestMethod.POST)
	@ResponseBody
	public WebAsyncTask< VResponse> upload(@RequestParam(value = "file", required = false) MultipartFile file
			, @RequestParam String uuid, @RequestParam String eyeMidColor,@RequestParam String gender, HttpServletRequest request){
			int time =200;
			String fileName = file.getOriginalFilename();
			logger.debug(fileName);
			key = uuid;
			String path = "/var/lib/tomcat7/webapps/user/photo/";
			File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 
	        try {
				file.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				
				e.printStackTrace();
			}
	        BodyView bodyView = new BodyView();
	        bodyView.setId(Integer.valueOf(uuid));
	        bodyView.setEyeMidColor(eyeMidColor);
	        bodyView.setGender(gender);
	        bodyView.setFace("http://123.56.19.60:8080/user/photo/"+fileName);
	        UserView userView =_bodyService.update(bodyView);
	        FaceServerSend send = new FaceServerSend();
	        send.setGender(gender);
	        send.setKey(key);
	        send.setUuid(uuid);
	        send.setUsername(userView.getAccount());
	        send.setAddress("http://123.56.19.60:8080/user/photo/"+fileName);
	        Socket socket = RenderSocket.getHeart();
	        if(socket !=null){
	        
				OutputStream os;
				try {
					os = socket.getOutputStream();
					String jsonString = JSON.toJSONString(send);
					os.write(new String(jsonString.getBytes(),"UTF-8").getBytes());
					os.flush();
					time = 1000*60*3;
				} catch (IOException e) {
				
					e.printStackTrace();
				} 
		
	        }
	        Callable<VResponse> asyncTask = new Callable<VResponse>() {

				@Override
				public VResponse call() throws Exception {
					 Thread thread = Thread.currentThread();
					 AsyncThread.setChacheThread(key, thread);
					 try {
						 Thread.sleep(1000*60*4);
					 }catch(InterruptedException ex) {
						
						 return new VResponse();
					 }
					
					return new VResponse();
				}
			};
			WebAsyncTask<VResponse> webAsyncTask  = new WebAsyncTask<>(time,asyncTask);
			webAsyncTask.onTimeout(timeOutCallBack());
			
			
			
	        
	        
		return webAsyncTask;
	}
	public Callable<VResponse> timeOutCallBack(){

        Callable<VResponse> callback = new Callable<VResponse>() {
            @SuppressWarnings("deprecation")
			@Override
            public VResponse call() throws Exception {
            	Thread thread =AsyncThread.getChaceByKey(key);
            	thread.interrupt();
            	VResponse vResponse = new VResponse();
            	vResponse.setMessage("我超时了");
               return vResponse;
            }
        };
        return callback;
    }
	

	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public VResponse messageNotReadable(HttpMessageNotReadableException exception, HttpServletResponse response){
	    Logger.getLogger(UserManagerController.class).error("请求参数不匹配。", exception);

	    return new VResponse();
	}
	
	 @ExceptionHandler
	    @ResponseBody
	    public String handleException(IllegalStateException ex) {
	        return "Handled exception: " + ex.getMessage();
	    }

	
	
}
