package com.streamgrabber;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.streamgrabber.structures.IMusicTrackInfo;

public abstract class StreamGrabber {

	protected List<IMusicTrackInfo> trackList;
	
	public StreamGrabber() {
		trackList = new ArrayList<IMusicTrackInfo>();		
	}	
	
	public List<IMusicTrackInfo> getTrackList(){
		return trackList;
	}
	
	abstract public void requestTrackList(String trackTitle,int tracksCount) throws IOException;
	abstract public InputStream openDownloadStream(String trackId);
	abstract public void requestTrackList(String albumId) throws IOException;		
	
}
