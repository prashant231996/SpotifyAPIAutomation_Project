package com.spotify.oauth2.api;

public class Routes {
	
	public static final String BASE_PATH="/v1";
	public static final String API="/api";
	public static final String TOKEN="/token";
	public static final String USERS="/users";
	public static final String PLAYLISTS="/playlists";
	public static final String cratePlayList="/users/{userId}/playlists";
	public static final String getPlayList="/playlists/{playlistId}";
	public static final String updatePlaylist="/playlists/{playlistId}";
	public static final String renewToken="/api/token";
	public static final String baseUri="https://api.spotify.com";
	
}
