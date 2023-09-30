package org.test;

import java.util.*;

public class RecentPlaylistSongs {
	private final int cap;
	private final Map<String, LinkedList<String>> playlist;

	public RecentPlaylistSongs(int cap) {
		this.cap = cap;
		this.playlist = new HashMap<>();
	}

	public void addPlayedSong(String user, String song) {
		playlist.putIfAbsent(user, new LinkedList<>());
		LinkedList<String> userSongs = playlist.get(user);

		if (userSongs.size() >= cap) {
			userSongs.removeFirst();
		}
		userSongs.add(song);
	}

	public List<String> getRecentlyPlayedSongs(String user) {
		return playlist.getOrDefault(user, new LinkedList<>());
	}

	public static void main(String[] args) {
		RecentPlaylistSongs store = new RecentPlaylistSongs(3);

		store.addPlayedSong("U1", "S1");
		store.addPlayedSong("U1", "S2");
		store.addPlayedSong("U1", "S3");
		store.addPlayedSong("U1", "S4");

		List<String> recentlyPlayed = store.getRecentlyPlayedSongs("U1");

		// Assertions
		assert recentlyPlayed.size() == 3 : "Expected 3 recently played songs for user U1";
		assert recentlyPlayed.get(0).equals("S2") : "Expected S2 as the most recent song for user U1";
		assert recentlyPlayed.get(1).equals("S3") : "Expected S3 as the second most recent song for user U1";
		assert recentlyPlayed.get(2).equals("S4") : "Expected S4 as the third most recent song for user U1";

		System.out.println("User 1's Recently Played Songs: " + recentlyPlayed);

	}}