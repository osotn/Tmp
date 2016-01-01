// TODO Rename package name
package pasa.gl.com.travelplaylisthandhelp;

import android.util.Log;

import java.net.URI;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by oleksandr.v.sotnikov on 12/25/2015.
 */
public class Database {
    private final String TAG = "Database";
    private Firebase mFirebaseRef;
    private CurrentMultimedia currentMultimedia = null;
    private Multimedia[] multimediaList = null;

    private DatabaseEvents events = null;

    // TODO Implement as Singletone. We need only one database for TravelApp

    public Database() {
        mFirebaseRef = new Firebase(
                "https://travelapppasa.firebaseio.com/").child("Travels").child("TestTravel");


/*
        Multimedia[] multimedias = new Multimedia[16];
        multimedias[0] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 56,
                "I'm a Rock",
                "Simon and Garfunkel");
        multimedias[1] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                3*60 + 1,
                "Don't Sing",
                "Data");
        multimedias[2] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                "",
                42*60 + 18,
                "Criminal Minds",
                "2 season 1");
        multimedias[3] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 49,
                "Monster Man",
                "Iggy Pop");
        multimedias[4] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 45,
                "Passenger",
                "Iggy Pop");
        multimedias[5] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                "",
                43*60 + 35,
                "Criminal Minds",
                "2 season 2");
        multimedias[6] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                "",
                42*60 + 53,
                "Criminal Minds",
                "2 season 3");
        multimedias[7] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 57,
                "Shout",
                "Bla-bla");

        multimedias[8] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 56,
                "I'm a Rock",
                "Simon and Garfunkel");
        multimedias[9] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                3*60 + 1,
                "Don't Sing",
                "Data");
        multimedias[10] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                "",
                42*60 + 18,
                "Criminal Minds",
                "2 season 1");
        multimedias[11] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 49,
                "Monster Man",
                "Iggy Pop");
        multimedias[12] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 45,
                "Passenger",
                "Iggy Pop");
        multimedias[13] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                "",
                43*60 + 35,
                "Criminal Minds",
                "2 season 2");
        multimedias[14] = new Multimedia(
                Multimedia.MultimediaType.MOVIE,
                "",
                42*60 + 53,
                "Criminal Minds",
                "2 season 3");
        multimedias[15] = new Multimedia(
                Multimedia.MultimediaType.MUSIC,
                "",
                2*60 + 57,
                "Shout",
                "Bla-bla");

        mFirebaseRef.child("MultimediaList").setValue(multimedias);
*/

        mFirebaseRef.child("CurrentMultimedia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Firebase - onDataChange()");

                currentMultimedia = dataSnapshot.getValue(CurrentMultimedia.class);

                if (events != null)
                    events.onCurrentMultimediaChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mFirebaseRef.child("MultimediaList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Firebase - onDataChange()");

                multimediaList = dataSnapshot.getValue(Multimedia[].class);

                if (events != null)
                    events.onMultimediaChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public CurrentMultimedia getCurrentMultimedia() {
        return currentMultimedia;
    }

    public Multimedia[] getMultimediaList() {
        return multimediaList;
    }

    public void setEvents(DatabaseEvents events) {
        this.events = events;
    }
}