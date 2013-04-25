import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
			streamGrabber.requestTrackList("2pac", 1);
			List<IMusicTrackInfo> musicList = streamGrabber.getTrackList();
			for(IMusicTrackInfo musicInfo:musicList){
				System.out.println(musicInfo);
			}
			
			InputStream is = streamGrabber.openDownloadStream(musicList.get(0).getTrackId());
			
			int bytesRead = 0;
			byte[] bytearray = new byte[10000000];
			while(true){
				int currentRead =is.read(bytearray,bytesRead,10);
				if(currentRead == -1){
					break;
				}
				bytesRead += currentRead;
				
			}
			
		} catch (IOException e) {	
			e.printStackTrace();
		}

	}

}
