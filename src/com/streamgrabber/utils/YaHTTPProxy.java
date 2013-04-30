package com.streamgrabber.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class YaHTTPProxy extends HTTPProxy {

	public final static String LIST_OF_CONTENT_REQUEST = "http://music.yandex.ru/fragment/search?";
	public final static String[] LIST_OF_CONTENT_KEY_PARAMS = { "text=", "&type=", "&page=" };
	
	@Override
	public String makeRequestForString(String requestURL) throws IOException {
		
		URL url = new URL(requestURL);
		URLConnection urlConnection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		
		String inputLine = "";
		String responce = "";
		while ((inputLine = in.readLine()) != null){
			responce+=inputLine;
		}			
			
		in.close();	
				
		return responce;
	}

	@Override
	public InputStream makeRequestForStream(String requestURL) {
		return null;
	}	
	
	/**
	 * 
	 * @param HTMLelement
	 * @param attrKey
	 * @return {"json","Object"} or ""
	 */
	public static String extractJSONObject(final String HTMLelement,final String attrKey){
		
		Document doc = Jsoup.parse(HTMLelement);
		Element element = doc.body();
				
		final String attrVal = element.child(0).attributes().get(attrKey);
		
		int leftBraceIndex = attrVal.indexOf('{');
		int rightBraceIndex = attrVal.lastIndexOf('}');
				
		String jsonObject = "";
		if(leftBraceIndex != -1 && rightBraceIndex != -1){
			jsonObject = (attrVal.substring(leftBraceIndex,rightBraceIndex+1));
			}	
		
		return jsonObject;
	}	
	
	private static int getTracksCount(String htmlPage){
		
		return 0;
	}
	
}
