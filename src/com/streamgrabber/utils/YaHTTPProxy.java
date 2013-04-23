package com.streamgrabber.utils;

import java.io.InputStream;
import java.util.Stack;

public class YaHTTPProxy implements HTTPProxy {

	private final String LIST_OF_CONTENT_REQUEST = "http://music.yandex.ru/fragment/search?";
	private final String[] KEY_PARAMS = { "text=", "&type=", "&page=&" };
	
	@Override
	public String makeRequestForString(String requestURL) {
		
		
		
		return null;
	}

	@Override
	public InputStream makeRequestForStream(String requestURL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String composeRequest(final String... paramsValue) {		
		
		assert(paramsValue.length <= KEY_PARAMS.length);
		
		StringBuilder strBuilder = new StringBuilder(LIST_OF_CONTENT_REQUEST);
		
		for(int i=0;i<paramsValue.length;i++){
			strBuilder.append(KEY_PARAMS[i]);
			strBuilder.append(paramsValue[i]);
		}
		
		return strBuilder.toString();
	}

}
