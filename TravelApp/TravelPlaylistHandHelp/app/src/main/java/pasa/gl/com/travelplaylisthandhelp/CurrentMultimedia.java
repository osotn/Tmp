// TODO Rename package name
package pasa.gl.com.travelplaylisthandhelp;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class CurrentMultimedia {
    // Must be "JavaBean" class.
    private int position;
    private int remain_sec;

    // No-arg constructor
    public CurrentMultimedia() {}

    public CurrentMultimedia(int position, int remain_sec) {
        this.position = position;
        this.remain_sec = remain_sec;
    }

    // Get()/set() for all class data
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getRemain_sec() {
        return remain_sec;
    }
    public void setRemain_sec(int remain_sec) {
        this.remain_sec = remain_sec;
    }
}