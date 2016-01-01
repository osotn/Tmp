package com.gl.pasa.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    private final String TAG = "PlaylistActivity";

    private Travel travel;

    private ListView list;
    private PlaylistAdapter adapter;
    private ArrayList<Multimedia> ListViewValueArr = new ArrayList<Multimedia>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_hand_held);

        // Firebase: Add it for every Activity.
        Firebase.setAndroidContext(this);

        list = (ListView) findViewById(R.id.list);
        adapter = new PlaylistAdapter(this, ListViewValueArr, getResources());
        list.setAdapter(adapter);

        travel = Database.getInstance().getTravel();

        travel.setCurrentMultimediaChangeEvent(new DatabaseChangeEvent() {
            @Override
            public void onDataChanged() {
                Log.d(TAG, "Current Multimedia has changed");

            }
        });
        travel.setMultimediaListChangeEvent(new DatabaseChangeEvent() {
            @Override
            public void onDataChanged() {
                Log.d(TAG, "Multimedia List has changed");
                changedMultimediaList();
            }
        });
    }

    private void changedMultimediaList() {
        ListViewValueArr.clear();

        Multimedia[] multimedia_list = travel.getMultimediaList();
        if (multimedia_list != null) {
            for (Multimedia multimedia : multimedia_list) {
                ListViewValueArr.add(multimedia);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
