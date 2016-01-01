package com.gl.pasa.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    private final String TAG = "PlaylistActivity";

    private ListView list;
    private PlaylistAdapter adapter;
    private ArrayList<String> ListViewValueArr = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_hand_held);

        // Firebase: need to add it for every Activity.
        Firebase.setAndroidContext(this);

        list = (ListView) findViewById(R.id.list);
        adapter = new PlaylistAdapter(this, ListViewValueArr, getResources());
        list.setAdapter(adapter);
    }
}
