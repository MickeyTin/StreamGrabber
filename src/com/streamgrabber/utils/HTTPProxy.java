package com.streamgrabber.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class HTTPProxy {

	public abstract String makeRequestForString(String requestURL) throws IOException;
	
	public abstract InputStream makeRequestForStream(String requestURL);
		
	public static String composeRequest(String requestURL, String[] paramsKey,
			String... paramsValue) {

		assert (paramsValue.length <= paramsKey.length);

		StringBuilder strBuilder = new StringBuilder(requestURL);

		for (int i = 0; i < paramsValue.length; i++) {
			strBuilder.append(paramsKey[i]);
			strBuilder.append(paramsValue[i]);
		}

		return strBuilder.toString();
	}	
	
	public static List<String> getHTMLElements(String HTMLPage,String attrKey,String attrValueContain){
		
		Document doc = Jsoup.parse(HTMLPage);
		
		Elements elements =  doc.getElementsByAttributeValueStarting(attrKey, attrValueContain);
		
		List<String> elementsList = new ArrayList<String>();
		for(Element element:elements){
			elementsList.add(element.toString());
		}
		
		return elementsList;
	}	
	
	public static String getAttributeValue(String element,String attrName){
		
		Document doc = Jsoup.parse(element);
		
		Elements elements = doc.getElementsByAttribute(attrName);
		
		if(elements.isEmpty()){
			return "";
		}
		
		return elements.get(0).attributes().get(attrName);		
	}
	
	public static String getElementInnerText(String document,String tagName){		
		Document doc = Jsoup.parse(document);
		Elements elements = doc.getElementsByTag(tagName);
		if(!elements.isEmpty()){
			return elements.get(0).text();
		}
		return "";
	}
}
