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
	
	//Audio Book api endpoints
	public static final String saveAudioBooks="/me/audiobooks";
	public static final String getAnAudioBook="/audiobooks/{audioBookId}";
	public static final String chkUsersSavedAudioBook="/me/audiobooks/contains";
	public static final String getSevaralAudioBooks="/audiobooks";
	public static final String getAudioBookChapter="/audiobooks/{audioBookId}/chapters";
	public static final String getUsersSavedAudioBooks="/me/audiobooks";
	public static final String removeUsersSavedAudioBooks="/me/audiobooks";
	
	//Category API endpoints
	public static final String getSingleCategory="/browse/categories/{categoryId}";
	public static final String getSevaralCategories="/browse/categories";
	
	//Chapter API endpoints
	public static final String getChapterDetail="/chapters/{chapterId}";
	public static final String getChaptersDetail="/chapters";
	
	//Episode API endpoints
	public static final String getEpisodeDetail="/episodes/{episodeId}";
	public static final String getSevaralEpisods="/episodes";
	public static final String saveEpisode="/me/episodes";
	public static final String getUsersSavedEpisode="/me/episodes";
	public static final String checkUsersSavedEpisode="/me/episodes/contains";
	public static final String removeUsersSavedEpisode="/me/episodes";
	
	//Player API endpoints
	public static final String getPlayBackState="/me/player";
	public static final String addItemToPlayBackQueue="/me/player/queue";
	public static final String transferbackPlay="/me/player";
	public static final String getAvailableDevices="/me/player/devices";
	public static final String startAndResumePlayBack="/player/play";

    //Markets API endpoints
    public static final String getAvailableMarkets="/markets";

    //User API endpoints
    public static final String getCurrantUserProfile="/me";
    public static final String getUsersProfile="/users/{userId}";


	
	
	
	
	
	
}
