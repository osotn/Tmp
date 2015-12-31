package pasa.gl.com.travelplaylisthandhelp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    private final String TAG = "PlaylistActivity";

    // TravelApp database
    private Database database = new Database();

    private ListView list;

    private PlaylistAdapter adapter;
    private ArrayList<Multimedia> ListViewValueArr = new ArrayList<Multimedia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playlist_hand_help);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        // Our code
        updateMultimedias();
        list = (ListView) findViewById(R.id.list);
        adapter = new PlaylistAdapter(this, ListViewValueArr, getResources());
        adapter.setPlayingPosition(database.curMultimedia());
        list.setAdapter(adapter);

        list.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                setListCurrentPosition(database.curMultimedia());
            }
        });


    }

    private void updateMultimedias() {
        ListViewValueArr.clear();

        Multimedia[] multimedias = database.getMultimedias();
        for (Multimedia multimedia : multimedias) {
            ListViewValueArr.add(multimedia);
        }

        adapter.notifyDataSetChanged();
    }

    private void setListCurrentPosition(int current_position) {
        int height = list.getHeight();
        int itemHeight = list.getChildAt(0).getHeight();
        Log.d(TAG, "First Visible Position =" +list.getFirstVisiblePosition());
        Log.d(TAG, "Last Visible Position =" +list.getLastVisiblePosition());
        Log.d(TAG, "height =" +height);
        list.setSelectionFromTop(current_position, height/2 - itemHeight/2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
