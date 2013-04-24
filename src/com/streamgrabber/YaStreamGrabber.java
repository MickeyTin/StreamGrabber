package com.streamgrabber;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.streamgrabber.structures.IMusicTrackInfo;
import com.streamgrabber.structures.YaMusicTrackInfo;
import com.streamgrabber.utils.HTTPProxy;
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

		
		
		return null;
	}
	
	
}
