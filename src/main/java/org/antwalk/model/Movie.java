package org.antwalk.model;

public class Movie {

	String title;
	int releaseYear;
	String rating;
	int duration;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Movie(String title, int releaseYear, String rating, int duration) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.duration = duration;
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear + ", rating=" + rating + ", duration="
				+ duration + "]";
	}
	
}
