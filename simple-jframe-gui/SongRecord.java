/*
*	Samantha L. Misurda
*	Assignment 3
* 	SongRecord.java
*/

public class SongRecord
{
	private int ranking;
	private String songTitle;
	private String artist;
	private int year;

	public SongRecord(String inputLine) {
		String [] songData = inputLine.split("\t");
		ranking = Integer.parseInt(songData[0]);
		songTitle = songData[1];
		artist = songData[2];
		year = Integer.parseInt(songData[3]);
	}

	public int getRanking() {
		return ranking;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public String getArtist() {
		return artist;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		return getRanking() + "\t" + getSongTitle() + "\t" + getArtist() + "\t" + getYear();
	}
}