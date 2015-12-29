// TODO Rename package name
package pasa.gl.com.travelplaylisthandhelp;

import java.net.URI;

/**
 * Created by oleksandr.v.sotnikov on 12/25/2015.
 */
public class Database {
    // TODO Implement as Singletone. We need only one database for TravelApp


    public Travel createTravel(String name) {
        Travel travel = new Travel(name);
        // TODO Add in database.
        return travel;
    }

    public MapPoint[] getPoints() {

        // TODO Get from Database;

        // TEST Only
        MapPoint[] points = new MapPoint[10];

        return points;
    }

    public Multimedia[] getMultimedias() {

        // TODO Get from Database;


        // TEST only
        Multimedia[] multimedias = new Multimedia[8];
        multimedias[0] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                URI.create(""),
                2*60 + 56,
                "I'm a Rock",
                "Simon and Garfunkel");
        multimedias[1] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                URI.create(""),
                3*60 + 1,
                "Don't Sing",
                "Data");
        multimedias[2] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                URI.create(""),
                42*60 + 18,
                "Criminal Minds",
                "2 season 1");
        multimedias[3] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                URI.create(""),
                2*60 + 49,
                "Monster Man",
                "Iggy Pop");
        multimedias[4] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                URI.create(""),
                2*60 + 45,
                "Passenger",
                "Iggy Pop");
        multimedias[5] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                URI.create(""),
                43*60 + 35,
                "Criminal Minds",
                "2 season 2");
        multimedias[6] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                URI.create(""),
                42*60 + 53,
                "Criminal Minds",
                "2 season 3");
        multimedias[7] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                URI.create(""),
                2*60 + 57,
                "Shout",
                "Bla-bla");

        return multimedias;
    }
}
