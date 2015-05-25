package dmax.staticmap.sample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import dmax.staticmap.Callback;
import dmax.staticmap.Config;
import dmax.staticmap.Marker;
import dmax.staticmap.PathPoint;
import dmax.staticmap.StaticMap;

import static android.widget.AbsListView.LayoutParams.MATCH_PARENT;

/**
 * @user: Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * @date: 7/31/14
 * @time: 11:51 AM
 */
public class MapsAdapter extends BaseAdapter {

    private static final float DEFAULT_LATITUDE = 50.4020355f;
    private static final float DEFAULT_LONGITUDE = 30.5326905f;
    private static final int SIZE = 400; // dp
    private static final int MAP_DEFAULT = 0;
    private static final int MAP_SECURE = 1;
    private static final int MAP_ZOOM = 2;
    private static final int MAP_SIZE = 3;
    private static final int MAP_SCALE = 4;
    private static final int MAP_SATELLITE = 5;
    private static final int MAP_HYBRID = 6;
    private static final int MAP_TERRAIN = 7;
    private static final int MAP_ADDRESS = 8;
    private static final int MAP_LOCATION = 9;
    private static final int MAP_MARKER_ADDRESS = 10;
    private static final int MAP_MARKER_LOCATION = 11;
    private static final int MAP_MARKER_STYLE = 12;
    private static final int MAP_PATH = 13;
    private static final int COUNT = 14;

    private Context context;
    private Bitmap placeHolder;
    private SparseArray<Bitmap> maps = new SparseArray<Bitmap>();

    public MapsAdapter(Context context) {
        this.context = context;

        try {
            placeHolder = BitmapFactory.decodeStream(context.getAssets().open("placeholder.gif"));
        } catch (IOException e) {
            placeHolder = Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.ARGB_8888);
            placeHolder.eraseColor(Color.GRAY);
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Bitmap getItem(int position) {
        return maps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = convertView != null
                ? (ImageView) convertView
                : inflate(context);

        Bitmap map = getItem(position);
        if (map == null) {
            map = placeHolder;
            loadMap(position, image);
        }
        image.setImageBitmap(map);

        return image;
    }

    private ImageView inflate(Context context) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.CENTER);
        view.setLayoutParams(new LayoutParams(MATCH_PARENT, SIZE));
        return view;
    }

    private Config createDefaultConfig() {
        Config config = new Config();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int pxWidth = (int) (SIZE * (metrics.densityDpi / 160f));
        int pxHeight = (int) ((SIZE / 4) * (metrics.densityDpi / 160f));
        config.setImageSize(pxHeight, pxWidth);
        config.setCenter(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
        return config;
    }

    private void loadMap(final int position, final ImageView view) {
        Marker marker;
        Marker start;
        Marker end;
        PathPoint pathPoint;
        Config config;
        switch (position) {
            case MAP_DEFAULT:
                config = createDefaultConfig();
                config.setZoom(5);
                break;

            case MAP_SECURE:
                config = createDefaultConfig();
                config.setSecure(true);
                config.setZoom(5);
                break;

            case MAP_ZOOM:
                config = createDefaultConfig();
                config.setZoom(10);
                break;

            case MAP_SIZE:
                config = createDefaultConfig();
                config.setImageSize(200, 200);
                config.setZoom(5);
                break;

            case MAP_SCALE:
                config = createDefaultConfig();
                config.setImageSize(200, 200);
                config.setScale(2);
                config.setZoom(5);
                break;

            case MAP_SATELLITE:
                config = createDefaultConfig();
                config.setMapType(Config.MapType.satellite);
                config.setZoom(5);
                break;

            case MAP_HYBRID:
                config = createDefaultConfig();
                config.setMapType(Config.MapType.hybrid);
                config.setZoom(5);
                break;

            case MAP_TERRAIN:
                config = createDefaultConfig();
                config.setMapType(Config.MapType.terrain);
                config.setZoom(5);
                break;

            case MAP_ADDRESS:
                config = createDefaultConfig();
                config.setAddress("Ukraine");
                break;

            case MAP_LOCATION:
                config = createDefaultConfig();
                config.setCenter(50, 30);
                break;

            case MAP_MARKER_ADDRESS:
                config = createDefaultConfig();
                marker = config.addMarker();
                marker.setAddress("Kyiv");
                break;

            case MAP_MARKER_LOCATION:
                config = createDefaultConfig();
                marker = config.addMarker();
                marker.setLocation(50, 32);
                config.setZoom(5);
                break;

            case MAP_MARKER_STYLE:
                config = createDefaultConfig();
                marker = config.addMarker();
                marker.setLocation(50, 28);
                marker.setLabel("M");
                marker.setColor("0x00FFFF");
                marker.setSize(Marker.Size.mid);
                config.setZoom(7);
                break;
            case MAP_PATH:
                config = createDefaultConfig();
                start = config.addMarker();
                start.setLocation(50, 28);
                start.setLabel("S");
                start.setColor("0x00FFFF");
                start.setSize(Marker.Size.mid);

                end = config.addMarker();
                end.setLocation(50, 28.5f);
                end.setLabel("E");
                end.setColor("0x00FFFF");
                end.setSize(Marker.Size.mid);

                config.setPathColor("0x0000ff");
                config.setPathWeight(5);

                pathPoint = config.addPathPoint();
                pathPoint.setLocation(50.2f, 28.1f);

                pathPoint = config.addPathPoint();
                pathPoint.setLocation(50.4f, 28.2f);

                pathPoint = config.addPathPoint();
                pathPoint.setLocation(50.6f, 28.3f);

                pathPoint = config.addPathPoint();
                pathPoint.setLocation(50.8f, 28.4f);

                break;

            default: config = null;
        }

        Callback callback = new Callback() {
            @Override
            public void onMapGenerated(Bitmap mapImage) {
                maps.put(position, mapImage);
                view.setImageBitmap(mapImage);
                notifyDataSetChanged();
            }

            @Override
            public void onFailed(int errorCode, String errorMessage) {
                Toast.makeText(context,
                        errorCode == WRONG_URL ? "wrong url" : "can't load",
                        Toast.LENGTH_SHORT).show();
            }
        };

        StaticMap.requestMapImage(context, config, callback);
    }
}

