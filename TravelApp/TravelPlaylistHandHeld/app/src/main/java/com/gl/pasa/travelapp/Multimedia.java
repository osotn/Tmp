package com.gl.pasa.travelapp;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class Multimedia {
    // Must be "JavaBean" class.
    private String uri;
    private int duration_sec;
    // TODO Only for For music.
    private String song;
    private String artist;
    // TODO For moview - title, sessons ?
    public enum MultimediaType {
        UNKNOWN,
        MUSIC,
        MOVIE,
        LINK
    }
    private MultimediaType type;

    // No-arg constructor
    public Multimedia() {}

    public Multimedia(MultimediaType type, String uri, int duration_sec, String song,
                      String artist) {
        this.type = type;
        this.uri = uri;
        this.duration_sec = duration_sec;
        this.song = song;
        this.artist = artist;
    }

    // Get()/set() for all class data
    public MultimediaType getType() {
        return type;
    }
    public void setType(MultimediaType type) {
        this.type = type;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public int getDuration_sec() {
        return duration_sec;
    }
    public void setDuration_sec(int duration_sec) {
        this.duration_sec = duration_sec;
    }
    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
}