import java.io.IOException;
import java.util.List;

import com.streamgrabber.StreamGrabber;
import com.streamgrabber.YaStreamGrabber;
import com.streamgrabber.structures.IMusicTrackInfo;


public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StreamGrabber streamGrabber = new YaStreamGrabber();
		try {
			streamGrabber.requestTrackList("2pac", 0);
			List<IMusicTrackInfo> musicList = streamGrabber.getTrackList();
			for(IMusicTrackInfo musicInfo:musicList){
				System.out.println(musicInfo.getTitle());
			}
		} catch (IOException e) {		
			e.printStackTrace();
		}

	}

}
