package com.nantimes.vicloth.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.nantimes.vicloth.common.utils.RenderSocket;



public class MyWebApplicationContext extends ContextLoaderListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextInitialized(event);
		RenderSocket.Init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		super.contextDestroyed(event);
		RenderSocket.destroy();
	}
}
