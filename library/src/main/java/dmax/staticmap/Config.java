package dmax.staticmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Use this class to set map params and add markers.
 * More details at <a href="https://developers.google.com/maps/documentation/staticmaps">
 * https://developers.google.com/maps/documentation/staticmaps</a>
 *
 * @user: Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * @date: 7/28/14
 * @time: 4:59 PM
 */
public class Config {

    public static final dmax.staticmap.Marker[] MARKERS_ARRAY_TEMPLATE = new dmax.staticmap.Marker[0];
    public static final dmax.staticmap.PathPoint[] PATH_ARRAY_TEMPLATE = new dmax.staticmap.PathPoint[0];
    private MapType mapType;
    private boolean secure = false;
    private int zoomRatio = -1;
    private int scaleRatio;
    private int width = -1;
    private int height = -1;
    private float centerLatitude = -1f;
    private float centerLongitude = -1f;
    private String address;
    private List<dmax.staticmap.Marker> markers = new ArrayList<dmax.staticmap.Marker>();
    private List<dmax.staticmap.PathPoint> path = new ArrayList<PathPoint>();
    private String pathColor;
    private int pathWeight = -1;

    public Config setCenter(float latitude, float longitude) {
        this.centerLatitude = latitude;
        this.centerLongitude = longitude;
        return this;
    }

    public Config setImageSize(int height, int width) {
        this.width = width;
        this.height = height;
        return this;
    }

    public dmax.staticmap.Marker[] getMarkers() {
        return markers.toArray(MARKERS_ARRAY_TEMPLATE);
    }

    public dmax.staticmap.PathPoint[] getPath() {
        return path.toArray(PATH_ARRAY_TEMPLATE);
    }

    public MapType getMapType() {
        return mapType;
    }

    public Config setMapType(MapType type) {
        this.mapType = type;
        return this;
    }

    public boolean isSecure() {
        return secure;
    }

    public Config setSecure(boolean secure) {
        this.secure = secure;
        return this;
    }

    public int getZoom() {
        return zoomRatio;
    }

    public Config setZoom(int zoomRatio) {
        this.zoomRatio = zoomRatio;
        return this;
    }

    public int getScale() {
        return scaleRatio;
    }

    public Config setScale(int scaleRatio) {
        this.scaleRatio = scaleRatio;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getCenterLatitude() {
        return centerLatitude;
    }

    public float getCenterLongitude() {
        return centerLongitude;
    }

    public String getAddress() {
        return address;
    }

    public Config setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPathColor() {
        return pathColor;
    }

    public Config setPathColor(String color) {
        this.pathColor = color;
        return this;
    }

    public int getPathWeight() {
        return pathWeight;
    }

    public Config setPathWeight(int weight) {
        this.pathWeight = weight;
        return this;
    }

    public dmax.staticmap.Marker addMarker() {
        dmax.staticmap.Marker marker = new dmax.staticmap.Marker();
        markers.add(marker);
        return marker;
    }

    public dmax.staticmap.PathPoint addPathPoint() {
        dmax.staticmap.PathPoint pathPoint = new dmax.staticmap.PathPoint();
        path.add(pathPoint);
        return pathPoint;
    }

    public enum MapType {
        roadmap, satellite, hybrid, terrain
    }
}
