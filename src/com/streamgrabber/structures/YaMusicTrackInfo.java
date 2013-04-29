package com.streamgrabber.structures;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YaMusicTrackInfo implements IMusicTrackInfo {

	private String trackId;
	private String storageDir;
	private String title;
	private String artist;
	private String artistId;
	private String album;
	private String albumId;
	private String duration;
	private String coverURL;
	private String extension;

	private static final String[] REQUIRED_JSON_KEYS = { "id", "storage_dir",
		"title", "artist", "artist_id", "album", "album_id", "duration",
		"cover" };
	private static final int REQUIRED_JSON_KEYS_COUNT = 9;
	
	public void eraseAllData() {
		this.trackId = "";
		this.storageDir = "";
		this.title = "";
		this.artist = "";
		this.artistId = "";
		this.album = "";
		this.albumId = "";
		this.duration = "";
		this.coverURL = "";
		this.extension= ""; 
	}
	
	public YaMusicTrackInfo(String trackId, String storageDir, String title,
			String artist, String artistId, String album, String albumId,
			String duration, String coverURL) {
		this.trackId = trackId;
		this.storageDir = storageDir;
		this.title = title;
		this.artist = artist;
		this.artistId = artistId;
		this.album = album;
		this.albumId = albumId;
		this.duration = duration;
		this.coverURL = coverURL;
	}

	public YaMusicTrackInfo(String jsonObject) {				
				
		try {
			if (isValidJSONobject(jsonObject)) {
				JSONParser parser = new JSONParser();
				JSONObject jObj = (JSONObject) parser.parse(jsonObject);
				
				assert(REQUIRED_JSON_KEYS_COUNT == REQUIRED_JSON_KEYS.length);
				
				this.trackId = (String) jObj.get(REQUIRED_JSON_KEYS[0]);
				this.storageDir = (String) jObj.get(REQUIRED_JSON_KEYS[1]);
				this.title = (String) jObj.get(REQUIRED_JSON_KEYS[2]);
				this.artist = (String) jObj.get(REQUIRED_JSON_KEYS[3]);
				this.artistId = (String) jObj.get(REQUIRED_JSON_KEYS[4]);
				this.album = (String) jObj.get(REQUIRED_JSON_KEYS[5]);
				this.albumId = (String) jObj.get(REQUIRED_JSON_KEYS[6]);
				this.duration = (String) jObj.get(REQUIRED_JSON_KEYS[7]);
				this.coverURL = (String) jObj.get(REQUIRED_JSON_KEYS[8]);
								
			}else{
				eraseAllData();
			}			
		} catch (ParseException e) {			
			eraseAllData();
		} catch (Throwable e) {
			eraseAllData();
		}
		
	}
	
	@Override
	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getStorageDir() {
		return storageDir;
	}

	public void setStorageDir(String storageDir) {
		this.storageDir = storageDir;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	@Override
	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	@Override
	public String getDuration() {		
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	@Override
	public String getDownloadURL() {
		//TODO:implement 
		return null;
	}

	private static boolean isValidJSONobject(String jsonObject) throws ParseException{
		
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject) parser.parse(jsonObject);
		
		for(String requiredJSONKey:REQUIRED_JSON_KEYS){
			if(!jObj.containsKey(requiredJSONKey)){
				return false;
			}
		}
				
		return true;
	}

	@Override
	public boolean hasInfo() {
		return !title.isEmpty();
	}

	@Override
	public String toString() {
		return "YaMusicTrackInfo [trackId=" + trackId + ", storageDir="
				+ storageDir + ", title=" + title + ", artist=" + artist
				+ ", artistId=" + artistId + ", album=" + album + ", albumId="
				+ albumId + ", duration=" + duration + ", coverURL=" + coverURL
				+ "]";
	}

	@Override
	public String getExtension() {
		return extension;
	}	
	
	public void setExtension(String extension){
		this.extension = extension;
	}
	
	
}
/*
 * {"id":"9361356", "storage_dir":"415b1daa.9361356", "title":"Bitch",
 * "artist":"Marc van Linden", "artist_id":"502793", "artist_var":"",
 * "album":"Turn On The Radio, Vol. 7 - Club Music Radio Cuts And Edits",
 * "album_id":"991116", "duration":"195000",
 * "cover":"http:\/\/storage.music.yandex.ru\/get\/0608c8f6.a.991116\/1.30x30.jpg"}
 */
