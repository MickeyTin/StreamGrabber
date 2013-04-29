import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			streamGrabber.requestTrackList("hfjsgdhfksg", 1);
			List<IMusicTrackInfo> musicList = streamGrabber.getTrackList();
			if(musicList.isEmpty()){
				System.out.print("Not found any songs");
				return;
			}
			for (IMusicTrackInfo musicInfo : musicList) {
				System.out.println(musicInfo);
			}

			InputStream is = streamGrabber.openDownloadStream(musicList.get(0)
					.getTrackId());

			int bytesRead = 0;
			byte[] bytearray = new byte[10000000];
			while (true) {
				int currentRead = is.read(bytearray, bytesRead, 10);
				if (currentRead == -1) {
					break;
				}
				bytesRead += currentRead;
			}

			write(bytearray, "2pacy.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void write(byte[] aInput, String aOutputFileName) {

		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(
						aOutputFileName));
				output.write(aInput);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
