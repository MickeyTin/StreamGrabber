package com.streamgrabber.utils;

import java.io.InputStream;

public abstract class HTTPProxy {

	public abstract String makeRequestForString(String requestURL);
	
	public abstract InputStream makeRequestForStream(String requestURL);
	
	public abstract String composeRequest(String... params);	
}
