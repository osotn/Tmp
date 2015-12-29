// TODO Rename package name
package pasa.gl.com.travelplaylisthandhelp;

import java.net.URI;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class Multimedia {
    public enum MultimediaType {
        UNKNOWN,
        MUSIC,
        MOVIE,
        LINK
    }

    private MultimediaType mType;
    private URI mUri;
    private int mDuration_sec;
    // TODO Only for For music.
    private String mSong;
    private String mArtist;
    // TODO For moview - title, sessons ?


    public Multimedia() {
        mType = MultimediaType.UNKNOWN;
    }

    public Multimedia(MultimediaType type, URI uri, int duration_sec, String song, String artist) {
        mType = type;
        mUri = uri;
        mDuration_sec = duration_sec;
        mSong = song;
        mArtist = artist;
    }

    public MultimediaType getType() {
        return mType;
    }

    public URI getUri() {
        return mUri;
    }

    public int getDuration() {
        return mDuration_sec;
    }

    public String getSong() {
        return mSong;
    }

    public String getArtist() {
        return mArtist;
    }

}
