package com.streamgrabber.structures;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.streamgrabber.utils.HTTPProxy;
import com.streamgrabber.utils.YaHTTPProxy;

public class YaAlbum {

	private List<IMusicTrackInfo> trackList;
	private String title;
	private int tracksCount;	
	
	public YaAlbum() {
		trackList = new ArrayList<IMusicTrackInfo>();
		title = "";		
	}
		
	public void fillInfo(String htmlPageWithAlbumInfo) throws ParseException{
		
		title = HTTPProxy.getElementInnerText(htmlPageWithAlbumInfo, "h1");
		
		List<String> elementsList = HTTPProxy.getHTMLElements(htmlPageWithAlbumInfo,
				"class", "b-track b-track_type_mix js-track js-track");
		List<String> jsonObjects = new ArrayList<String>();
		for(String htmlElement:elementsList){
			String jsonObject = YaHTTPProxy.extractJSONObject(htmlElement, "onclick");
			trackList.add(new YaMusicTrackInfo(jsonObject));			
		}			
		
		tracksCount = jsonObjects.size();			
	}

	public List<IMusicTrackInfo> getTrackList() {
		return trackList;
	}

	public String getTitle() {
		return title;
	}

	public int getTracksCount() {
		return tracksCount;
	}				
}
