package dmax.staticmap;

/**
 * Created by snigavig on 25.05.15.
 */
public class PathPoint {

    private float latitude = -1;
    private float longitude = -1;

    PathPoint() {
        // use package-private scope for to force user
        // to create Marker using Config.addMarker method
    }

    public void setLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
