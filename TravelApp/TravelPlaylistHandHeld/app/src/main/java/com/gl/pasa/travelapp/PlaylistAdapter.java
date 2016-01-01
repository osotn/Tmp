package com.gl.pasa.travelapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        public TextView text;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.item_playlist_hand_held, parent, false);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);

            vi.setTag( holder );
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        Log.v(TAG, "getView() position = " + position);

        if (data.size() <= 0) {
            holder.text.setText("No Data");
        } else {
            // TODO
            holder.text.setText("TODO: Some data");
        }
        return vi;
    }
}
