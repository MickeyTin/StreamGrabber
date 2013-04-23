package com.streamgrabber.structures;

public class MusicTrackInfo {

	private String trackId;
	private String storageDir;
	private String title;
	private String artist;
	private String artistId;
	private String album;
	private String albumId;
	private String duration;
	private String coverURL;
	
	
	
	public MusicTrackInfo(String trackId, String storageDir, String title,
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	
	
}
/*{"id":"9361356", "storage_dir":"415b1daa.9361356", 
 * "title":"Bitch", "artist":"Marc van Linden", "artist_id":"502793", 
 * "artist_var":"", "album":"Turn On The Radio, Vol. 7 - Club Music Radio Cuts And Edits", 
 * "album_id":"991116", "duration":"195000", 
 * "cover":"http:\/\/storage.music.yandex.ru\/get\/0608c8f6.a.991116\/1.30x30.jpg"}
 * */
 