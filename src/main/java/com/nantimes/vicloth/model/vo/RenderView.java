package com.nantimes.vicloth.model.vo;

import java.util.List;

import com.nantimes.vicloth.model.VResponse;

public class RenderView  extends VResponse{
	List<String> url;
	
	public RenderView(){
		super();
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}
	
	

}
