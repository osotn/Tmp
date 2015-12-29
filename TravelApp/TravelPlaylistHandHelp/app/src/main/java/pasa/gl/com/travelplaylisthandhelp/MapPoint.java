// TODO Rename package name
package pasa.gl.com.travelplaylisthandhelp;

import android.location.Location;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class MapPoint {

    public enum MapPointType {
        UNKNOWN,
        COFFEE_STOP,
        TOILET_STOP
        // TODO Add types.
    }

    private Location mLocation;
    private String mDescription;
    private MapPointType mType;

    MapPoint() {
        mDescription = "";
        mType = MapPointType.UNKNOWN;
    }

    public Location getLocation() {
        return mLocation;
    }

    public String getDescription() {
        return mDescription;
    }

    public MapPointType getType() {
        return mType;
    }


    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setType(MapPointType type) {
        this.mType = type;
    }
}
