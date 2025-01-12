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
	public static final String addItemsToPlaylist="/playlists/{playlistId}/tracks";
	public static final String getItemsDetailsOfPlaylist="/playlists/{playlistId}/tracks";
	public static final String updateItemDetailsOfPlaylist="/playlists/{playlistId}/tracks";
	public static final String removeItemDetailsOfPlaylist="/playlists/{playlistId}/tracks";
	public static final String getCurrantUserPlaylist="/me/playlists";
	public static final String getUsersPlaylist="/users/{userId}/playlists";
	
	//Album api endpoints
	public static final String saveAlbum="/me/albums";
	public static final String getAlbum="/me/albums";
	public static final String removeAlbum="/me/albums";
	public static final String usersSavedAlbum="/me/albums/contains";
	public static final String getSingleAlbumInfo="/albums/{albumId}";
	public static final String getSevaralAlbumInfo="/albums";
	public static final String getAlbumTrack="/albums/{albumId}/tracks";
	public static final String getNewRelease="/browse/new-releases";
	
	//Artist api endpoints
	public static final String getArtistDetails="/artists/{artistId}";
	public static final String getArtistAlbum="/artists/{artistId}/albums";
	public static final String getArtistTopTrack="/artists/{artistId}/top-tracks";
	public static final String getArtistsDetails="/artists";
	public static final String getArtistRelatedArtist="/artists/{artistId}/related-artists";
	
	
	
	
	
	
}
