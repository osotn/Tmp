package com.gl.pasa.travelapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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

    private final String ICON_AUDIOTRACK = "com.gl.pasa.travelapp:drawable/ic_image_audiotrack_grey_54_active_36dp";
    private final String ICON_MOVIE      = "com.gl.pasa.travelapp:drawable/ic_av_movie_grey_54_active_36dp";
    private final String ICON_EQUALIZER  = "com.gl.pasa.travelapp:drawable/ic_av_equalizer_cyan_36dp";
    private final String GRADIENT_PAD    = "com.gl.pasa.travelapp:drawable/gradient_drawable_grey";

    private static LayoutInflater inflater = null;

    private Activity activity;
    private ArrayList data;
    private Resources res;
    private CurrentMultimedia currentMultimedia;

    public PlaylistAdapter(Activity activity, ArrayList data, Resources res) {
        this.activity = activity;
        this.data = data;
        this.res = res;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView text_error;
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

            // TODO There we can select view for HandHeld or HeadUnit
            vi = inflater.inflate(R.layout.item_playlist_hand_held, parent, false);

            holder = new ViewHolder();
            holder.text_error = (TextView) vi.findViewById(R.id.text_song);// Used as error view.
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

            holder.list_item.setBackgroundColor(Color.WHITE);
            holder.image_type.setImageResource(
                    getResId((multimedia.getType() == Multimedia.MultimediaType.MUSIC) ? ICON_AUDIOTRACK : ICON_MOVIE));
            holder.image_state.setVisibility(View.GONE);
            holder.text_song.setText(multimedia.getSong());
            holder.text_song.setTextColor(Color.BLACK);
            holder.text_artist.setText(multimedia.getArtist());
            holder.text_artist.setTextColor(res.getColor(R.color.colorGrey54_active));
            holder.text_time.setText(secToString(multimedia.getDuration_sec()));
            holder.gradient_pad.setBackgroundColor(Color.WHITE);

            if (currentMultimedia != null) {
                int currentPosition = currentMultimedia.getPosition();

                if (position < currentPosition) {
                    holder.list_item.setBackgroundResource(R.color.colorGrey_background);
                    holder.gradient_pad.setBackgroundResource(R.color.colorGrey_background);
                }

                if (position == (currentPosition - 1)) {
                    holder.gradient_pad.setBackgroundResource(getResId(GRADIENT_PAD));
                }

                if (position == currentPosition) {
                    holder.image_state.setVisibility(View.VISIBLE);
                    holder.image_state.setImageResource(getResId(ICON_EQUALIZER));
                    holder.text_song.setTextColor(res.getColor(R.color.colorCyan_text));
                    holder.text_artist.setTextColor(res.getColor(R.color.colorCyan_text));
                    holder.text_time.setText("-" + secToString(currentMultimedia.getRemain_sec()));
                }
            }
        }
        return vi;
    }

    public void setCurrentMultimedia (CurrentMultimedia currentMultimedia) {
        Log.d(TAG, "current multimedia position = " + currentMultimedia.getPosition());
        this.currentMultimedia = currentMultimedia;
    }

    private String secToString(int sec) {
        return String.format("%d:%02d", sec / 60, sec % 60);
    }

    private int getResId(String name) {
        return res.getIdentifier(name, null, null);
    }
}
