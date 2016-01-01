package com.gl.pasa.travelapp;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class Travel {
    private final String TAG = "Travel";
    private Firebase firebase;

    private String name;
    private CurrentMultimedia currentMultimedia;
    private Multimedia[] multimediaList;
    // TODO Users, ...

    private DatabaseChangeEvent currentMultimediaChangeEvent, multimediaListChangeEvent;

    public Travel(String ref_name, Firebase firebase_root) {
        name = ref_name;
        firebase = firebase_root.child(name);

        firebase.child("CurrentMultimedia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Travel name = "+name+" CurrentMultimedia Firebase onDataChange()");
                currentMultimedia = dataSnapshot.getValue(CurrentMultimedia.class);

                if (currentMultimediaChangeEvent != null) {
                    currentMultimediaChangeEvent.onDataChanged();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        firebase.child("MultimediaList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Travel name = "+name+" MultimediaList Firebase onDataChange()");
                multimediaList = dataSnapshot.getValue(Multimedia[].class);

                if (multimediaListChangeEvent != null) {
                    multimediaListChangeEvent.onDataChanged();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void setCurrentMultimediaChangeEvent(DatabaseChangeEvent event) {
        currentMultimediaChangeEvent = event;
    }
    public void setMultimediaListChangeEvent(DatabaseChangeEvent event) {
        multimediaListChangeEvent = event;
    }

    public CurrentMultimedia getCurrentMultimedia() {
        return currentMultimedia;
    }
    public Multimedia[] getMultimediaList() {
        return multimediaList;
    }
}
