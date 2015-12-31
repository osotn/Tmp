// TODO Rename package name
package pasa.gl.com.travelplaylisthandhelp;

/**
 * Created by oleksandr.v.sotnikov on 12/28/2015.
 */
public class CurrentMultimedia {
    private Long position;
    private Long remain;

    public CurrentMultimedia(int pos, int sec) {
        position = new Long(pos);
        remain = new Long(sec);
    }

    public int getPosition() {
        return position.intValue();
    }

    public int getRemain() {
        return remain.intValue();
    }
}

