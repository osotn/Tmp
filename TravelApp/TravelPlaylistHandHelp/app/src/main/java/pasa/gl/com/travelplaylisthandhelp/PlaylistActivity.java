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

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity implements DatabaseEvents {

    private final String TAG = "PlaylistActivity";

    // TravelApp database
    private Database database = null;

    private ListView list;

    private PlaylistAdapter adapter = null;
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
                onCurrentMultimediaChanged();

            }
        });

        // Our code
        Firebase.setAndroidContext(this);
        database = new Database();
        database.setEvents(this);

        list = (ListView) findViewById(R.id.list);
        adapter = new PlaylistAdapter(this, ListViewValueArr, getResources());
        list.setAdapter(adapter);
    }

    @Override
    public void onMultimediaChanged() {
        ListViewValueArr.clear();

        Multimedia[] multimedia_list = database.getMultimediaList();
        for (Multimedia multimedia : multimedia_list) {
            ListViewValueArr.add(multimedia);
        }

        if (adapter != null)
            adapter.notifyDataSetChanged();

        onCurrentMultimediaChanged();
    }

    @Override
    public void onCurrentMultimediaChanged() {
        CurrentMultimedia current = database.getCurrentMultimedia();
        if (current == null)
            return;
        list.post(new Runnable() {
            @Override
            public void run() {
                if (adapter != null)
                    adapter.notifyDataSetChanged();


                CurrentMultimedia current = database.getCurrentMultimedia();
                adapter.setCurrentMultimedia(current);

                int height = list.getHeight();
                int itemHeight = list.getChildAt(0).getHeight();
                int top = height / 2 - itemHeight / 2;
                int pos = current.getPosition();
                Log.d(TAG, "Set pos = " + pos + " top = " + top);
                list.setSelectionFromTop(pos, top);
                list.invalidate();

            }
        });
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
