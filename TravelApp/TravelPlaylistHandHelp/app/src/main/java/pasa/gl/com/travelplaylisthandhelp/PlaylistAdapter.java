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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
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
    private CurrentMultimedia curMultimedia = null;

    public PlaylistAdapter(Activity a, ArrayList d, Resources resLocal) {
        activity = a;
        data = d;
        res = resLocal;


        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setCurrentMultimedia(CurrentMultimedia current) {
        curMultimedia = current;
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
        public LinearLayout list_item;
        public TextView text;
        public TextView text1;
        public TextView text2;
        public ImageView image;
        public ImageView image2;
        public View gradient_pad;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.tabitem_playlist_hand_help, parent, false);

            holder = new ViewHolder();
            holder.list_item = (LinearLayout) vi.findViewById(R.id.list_item);
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text1 = (TextView) vi.findViewById(R.id.text1);
            holder.text2 = (TextView) vi.findViewById(R.id.text2);
            holder.image = (ImageView) vi.findViewById(R.id.image);
            holder.image2 = (ImageView) vi.findViewById(R.id.image2);
            holder.gradient_pad = vi.findViewById(R.id.gradient_pad);

            vi.setTag( holder );
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        Log.d(TAG, "getView() position="+position);

        if (data.size() <= 0) {
            holder.text.setText("No Data");
        } else {
            Multimedia tempValues = (Multimedia) data.get(position);

            int idAudio = res.getIdentifier(
                    "pasa.gl.com.travelplaylisthandhelp:drawable/ic_image_audiotrack_grey_54_active_36dp", null, null);
            int idMovie = res.getIdentifier(
                    "pasa.gl.com.travelplaylisthandhelp:drawable/ic_av_movie_grey_54_active_36dp", null, null);
            int isPlaying = res.getIdentifier(
                    "pasa.gl.com.travelplaylisthandhelp:drawable/ic_av_equalizer_cyan_36dp", null, null);

            holder.image2.setImageResource(isPlaying);

            int current_position = -1;
            int remain_sec = 0;
            if (curMultimedia != null) {
                current_position = curMultimedia.getPosition();
                remain_sec = curMultimedia.getRemain();
            }

            if (position <= (current_position-1)) {

                Log.d(TAG, "getView() set grey");
                holder.list_item.setBackgroundColor(0xFFD3D3D3);
                holder.gradient_pad.setBackgroundColor(0xFFD3D3D3);

                if (position == (curMultimedia.getPosition()-1)) {
                    Log.d(TAG, "getView() set gradient");
                    holder.gradient_pad.setBackgroundResource(res.getIdentifier(
                            "pasa.gl.com.travelplaylisthandhelp:drawable/gradient_drawable_grey", null, null));
                }

            } else {
                Log.d(TAG, "getView() set white");
                holder.list_item.setBackgroundColor(0xFFFFFFFF);
                holder.gradient_pad.setBackgroundColor(0xFFFFFFFF);
            }

            if (position == current_position) {

                holder.image2.setVisibility(View.VISIBLE);
                holder.text.setTextColor(0xFF03a9f4);
                holder.text1.setTextColor(0xFF03a9f4);

                String s = String.format("-%d:%02d", remain_sec / 60, remain_sec % 60);
                holder.text2.setText(s);

            } else {
                holder.text.setTextColor(0xFF000000);
                holder.text1.setTextColor(0xFF767676);
                holder.image2.setVisibility(View.GONE);

                String s = String.format("%d:%02d", tempValues.getDuration() / 60,
                        tempValues.getDuration() % 60);
                holder.text2.setText(s);
            }



            holder.text.setText(tempValues.getSong());
            holder.text1.setText(tempValues.getArtist());


            holder.image.setImageResource(
                    tempValues.getType() == Multimedia.MultimediaType.MUSIC ? idAudio : idMovie);
        }
        return vi;
    }
}
