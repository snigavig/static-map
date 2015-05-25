package dmax.staticmap.builder;

import android.content.Context;

import dmax.staticmap.Config;
import dmax.staticmap.PathPoint;

import static dmax.staticmap.R.string.pathColor;
import static dmax.staticmap.R.string.pathDef;
import static dmax.staticmap.R.string.pathWeight;

/**
 * Created by snigavig on 25.05.15.
 */
public class PathSegment extends Segment {

    @Override
    public void append(Config config, StringBuilder urlBuilder, Context context) {
        PathPoint[] path = config.getPath();

        if (path.length == 0) return;

        appendWithSeparator(urlBuilder, context.getString(pathDef));
        appendPathParameters(urlBuilder, context, pathColor, config.getPathColor());
        appendPathParameters(urlBuilder, context, pathWeight, config.getPathWeight());

        for (PathPoint pathPoint : path) {
            appendPathPoint(pathPoint, urlBuilder);
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
    }

    private void appendPathPoint(PathPoint pathPoint, StringBuilder urlBuilder) {
        urlBuilder.append(createLocation(pathPoint)).append(SEPARATOR_MARKER);
    }

    private String createLocation(PathPoint pathPoint) {
        return "" + pathPoint.getLatitude() + SEPARATOR_LOCATION + pathPoint.getLongitude();
    }

    private <T> void appendPathParameters(StringBuilder urlBuilder, Context context, int resId, T data) {
        urlBuilder.append(context.getString(resId, data)).append(SEPARATOR_MARKER);
    }
}