package com.gl.pasa.travelapp;

import com.firebase.client.Firebase;

/**
 * Created by user on 1/1/16.
 */
public class Database {
    private final String TAG = "Database";

    private static Database ourInstance = new Database();

    private final String FIREBASE_PROJECT = "https://travelapppasa.firebaseio.com/";
    private Firebase firebase;

    public Database() {
        firebase = new Firebase(FIREBASE_PROJECT);
    }

    public static Database getInstance() {
        return ourInstance;
    }

    // TODO create Travel

    // TEST simple version, only one travel.
    public Travel getTravel() {
        // TEST This Travel has created yet.
        Travel travel = new Travel("TestTravel", firebase.child("Travels"));
        return travel;
    }

}
