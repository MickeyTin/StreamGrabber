package com.streamgrabber.utils;
import java.io.InputStream;

public class YaHTTPProxy implements HTTPProxy {

	private final String LIST_OF_CONTENT_REQUEST = "http://music.yandex.ru/fragment/search?";
	private final String[] LIST_OF_CONTENT_KEY_PARAMS = { "text=", "&type=", "&page=" };
	
	@Override
	public String makeRequestForString(String requestURL) {
		
		
		
		return null;
	}

	@Override
	public InputStream makeRequestForStream(String requestURL) {
		return null;
	}

	@Override
	public String composeRequest(String requestURL, String[] paramsKey,
			String... paramsValue) {

		assert (paramsValue.length <= paramsKey.length);

		StringBuilder strBuilder = new StringBuilder(requestURL);

		for (int i = 0; i < paramsValue.length; i++) {
			strBuilder.append(paramsKey[i]);
			strBuilder.append(paramsValue[i]);
		}

		return strBuilder.toString();
	}

}
