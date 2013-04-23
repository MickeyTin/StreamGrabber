package com.streamgrabber;

import java.util.ArrayList;
import java.util.List;

import com.streamgrabber.structures.IMusicTrackInfo;

public abstract class StreamGrabber {

	protected List<IMusicTrackInfo> trackList;
	
	public StreamGrabber() {
		trackList = new ArrayList<IMusicTrackInfo>();		
	}
	
	public abstract void requestTrackList(String trackTitle,int tracksCount);
	
	public List<IMusicTrackInfo> getTrackList(){
		return trackList;
	}
	
	
	
}
