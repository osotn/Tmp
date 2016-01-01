package com.gl.pasa.travelapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class PlaylistAdapter extends BaseAdapter {

    private final String TAG = "PlaylistAdapter";
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;

    public PlaylistAdapter(Activity activity, ArrayList data, Resources res) {
        this.activity = activity;
        this.data = data;
        this.res = res;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView text_error; // Used if list is empty.
        public LinearLayout list_item;
        public ImageView image_type;
        public ImageView image_state;
        public TextView text_song;
        public TextView text_artist;
        public TextView text_time;
        public View gradient_pad;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.item_playlist_hand_held, parent, false);

            holder = new ViewHolder();
            holder.text_error = (TextView) vi.findViewById(R.id.text_song);
            holder.list_item = (LinearLayout) vi.findViewById(R.id.list_item);
            holder.image_type = (ImageView) vi.findViewById(R.id.image_type);
            holder.image_state = (ImageView) vi.findViewById(R.id.image_state);
            holder.text_song = (TextView) vi.findViewById(R.id.text_song);
            holder.text_artist = (TextView) vi.findViewById(R.id.text_artist);
            holder.text_time = (TextView) vi.findViewById(R.id.text_time);
            holder.gradient_pad = vi.findViewById(R.id.gradient_pad);

            vi.setTag( holder );
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        Log.v(TAG, "getView() position = " + position);

        if (data.size() <= 0) {
            holder.text_error.setText("No Data");
        } else {
            Multimedia multimedia = (Multimedia) data.get(position);

            holder.text_song.setText(multimedia.getSong());
            holder.text_artist.setText(multimedia.getArtist());
        }
        return vi;
    }
}
