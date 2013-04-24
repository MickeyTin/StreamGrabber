package com.streamgrabber;

import java.io.IOException;
import java.util.List;

import com.streamgrabber.structures.IMusicTrackInfo;
import com.streamgrabber.structures.YaMusicTrackInfo;
import com.streamgrabber.utils.HTTPProxy;
import com.streamgrabber.utils.YaHTTPProxy;

public class YaStreamGrabber extends StreamGrabber {

	public static final String ATTR_KEY_CONTAIN_TRACK_INFO = "onclick";
	//public static final String ATTR_VAL_CONTAIN_TRACK_INFO = "onclick";

	@Override
	public synchronized void requestTrackList(String trackTitle, int tracksCount)
			throws IOException {

		HTTPProxy httpProxy = new YaHTTPProxy();
		String responce = httpProxy.makeRequestForString(HTTPProxy
				.composeRequest(YaHTTPProxy.LIST_OF_CONTENT_REQUEST,
						YaHTTPProxy.LIST_OF_CONTENT_KEY_PARAMS, new String[] {
								trackTitle, "tracks", "0" }));

		List<String> htmlElements = HTTPProxy.getHTMLElements(responce,
				"class", "b-track  js-track js-track-");

		for (String htmlElement : htmlElements) {
			IMusicTrackInfo musicTrackInfo = new YaMusicTrackInfo(
					YaHTTPProxy.extractJSONObject(htmlElement,
							ATTR_KEY_CONTAIN_TRACK_INFO));
			if (musicTrackInfo.hasInfo()) {
				trackList.add(musicTrackInfo);
			}
		}

	}
}
