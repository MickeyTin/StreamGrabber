package com.streamgrabber;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

import com.streamgrabber.structures.IMusicTrackInfo;
import com.streamgrabber.structures.YaMusicTrackInfo;
import com.streamgrabber.utils.HTTPProxy;
import com.streamgrabber.utils.MD5Proxy;
import com.streamgrabber.utils.YaHTTPProxy;

public class YaStreamGrabber extends StreamGrabber {

	public static final String ATTR_KEY_CONTAIN_TRACK_INFO = "onclick";
	//public static final String ATTR_VAL_CONTAIN_TRACK_INFO = "onclick";

	private volatile int tracksLeft;
	private Object syncObject = new Object();
	
	@Override
	public synchronized void requestTrackList(final String trackTitle, final int maxTracksCount)
			throws IOException {//TODO:add multithreading

		int pageCount = 0;
		tracksLeft = maxTracksCount;
		while (tracksLeft > 0) {
			HTTPProxy httpProxy = new YaHTTPProxy();
			String responce = httpProxy.makeRequestForString(HTTPProxy
					.composeRequest(YaHTTPProxy.LIST_OF_CONTENT_REQUEST,
							YaHTTPProxy.LIST_OF_CONTENT_KEY_PARAMS,
							new String[] { trackTitle, "tracks", String.valueOf(pageCount++)}));

			List<String> htmlElements = HTTPProxy.getHTMLElements(responce,
					"class", "b-track  js-track js-track-");

			if(htmlElements.isEmpty()){
				break;
			}
			
			for (String htmlElement : htmlElements) {
				IMusicTrackInfo musicTrackInfo = new YaMusicTrackInfo(
						YaHTTPProxy.extractJSONObject(htmlElement,
								ATTR_KEY_CONTAIN_TRACK_INFO));
				if (musicTrackInfo.hasInfo()) {
					trackList.add(musicTrackInfo);
				}
			}
			
			tracksLeft = maxTracksCount - trackList.size();
		}		
	}

	@Override
	public InputStream openDownloadStream(String trackId) {
		
		HTTPProxy httpProxy = new YaHTTPProxy();
		YaMusicTrackInfo trackInfo = null;
		//step 1 : search for track in our trackList
		for(IMusicTrackInfo musicTrackInfo:trackList){
			if(musicTrackInfo.getTrackId().equals(trackId)){
				trackInfo = (YaMusicTrackInfo) musicTrackInfo;
				break;
			}
		}
		if(trackInfo == null){
			return null;
		}
		
		try {
			//step 2: Get XML with file name
			String trackNameXML = httpProxy.makeRequestForString
					(String.format("http://storage.music.yandex.ru/get/%s/2.xml", trackInfo.getStorageDir()));					
			
			String fileName = HTTPProxy.getAttributeValue(trackNameXML, "filename");
			
			if(fileName == null || fileName.isEmpty()){
				return null;
			}
				
			//step 3: Get XML with download info
			String downloadInfoXML = httpProxy.makeRequestForString(String
					.format("http://storage.music.yandex.ru/download-info/%s/%s",
							trackInfo.getStorageDir(), fileName));	
			 if(downloadInfoXML ==  null || downloadInfoXML.isEmpty()){
				 return null;
			 }
			
			 class DownloadStruct{
				 public String host="";
				 public String ts="";
				 public String path="";
				 public String region="";
				 public String s="";
				 
				public boolean isFilled() {
					return !(host.isEmpty() || ts.isEmpty() || path.isEmpty()
							|| region.isEmpty()||s.isEmpty());
				}				 
			 };
			 
			 DownloadStruct downloadStruct = new DownloadStruct();
			 
			 downloadStruct.host = HTTPProxy.getElementInnerText(downloadInfoXML, "host");
			 downloadStruct.path = HTTPProxy.getElementInnerText(downloadInfoXML, "path");
			 downloadStruct.region = HTTPProxy.getElementInnerText(downloadInfoXML, "region");
			 downloadStruct.ts = HTTPProxy.getElementInnerText(downloadInfoXML, "ts");
			 downloadStruct.s = HTTPProxy.getElementInnerText(downloadInfoXML, "s");
						 
			 if(!downloadStruct.isFilled()){
				 return null;
			 }
			 
			 //step 3.5 Generate secret Key
			 String secretKey = new MD5Proxy().MD5("XGRlBW9FXlekgbPrRHuSiA"+
			 downloadStruct.path.substring(1) +downloadStruct.s);
			 //step 4 : download request
			 
			String downloadRequest = String
					.format("http://%s/get-mp3/%s/%s%s?track-id=%s&/region=%s&from=service-search",
							downloadStruct.host,
							secretKey,
							downloadStruct.ts,
							downloadStruct.path,
							trackInfo.getTrackId(),
							downloadStruct.region);
						
			URL u = new URL(downloadRequest);			
			
			URLConnection uc = u.openConnection();			
			
			InputStream is =  uc.getInputStream();
			return is; 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
//	<download-info>
//		<regional-host>
//			elisto05e.music.yandex.ru.cache-kiev04.music.yandex.ru
//		</regional-host>
//		<regional-host>
//			elisto05e.music.yandex.ru.cache-kiev02.music.yandex.ru
//		</regional-host>
//		<host>elisto05e.music.yandex.ru</host>
//		<path>/5/data-0.1:41495141144:3507095</path>
//		<ts>13e41a92610</ts>
//		<region>143</region>
//		<s>acfea3ef94e4a01c26f6e750012971de</s>
//	</download-info>
}
