package pasa.gl.com.travelplaylisthandhelp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class PlaylistAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;

    public PlaylistAdapter(Activity a, ArrayList d, Resources resLocal) {
        activity = a;
        data = d;
        res = resLocal;

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
        public TextView text1;
        public TextView text2;
        public ImageView image;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.tabitem_playlist_hand_help, parent, false);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text1 = (TextView) vi.findViewById(R.id.text1);
            holder.text2 = (TextView) vi.findViewById(R.id.text2);
            holder.image = (ImageView) vi.findViewById(R.id.image);

            vi.setTag( holder );
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        if (data.size() <= 0) {
            holder.text.setText("No Data");
        } else {
            Multimedia tempValues = (Multimedia) data.get(position);

            int idAudio = res.getIdentifier(
                    "pasa.gl.com.travelplaylisthandhelp:drawable/ic_image_audiotrack_grey_54_active_36dp", null, null);
            int idMovie = res.getIdentifier(
                    "pasa.gl.com.travelplaylisthandhelp:drawable/ic_av_movie_grey_54_active_36dp", null, null);

            holder.text.setText(tempValues.getSong());
            holder.text1.setText(tempValues.getArtist());
            String s = String.format("%d:%02d", tempValues.getDuration() / 60,
                    tempValues.getDuration() % 60);
            holder.text2.setText(s);
            holder.image.setImageResource(
                    tempValues.getType() == Multimedia.MultimediaType.MUSIC ? idAudio : idMovie);
        }
        return vi;
    }
}
