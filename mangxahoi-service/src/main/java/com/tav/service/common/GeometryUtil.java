/*
* Copyright 2017 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.common;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lequytuan
 * @version 1.0
 * @since 27-Mar-17 20:00 PM
 */
public class GeometryUtil {

    public static final int SRID = 1;

    public static final String POLYGON = "POLYGON";
    public static final String MULTIPOLYGON = "MULTIPOLYGON";

    public static final String START_OF_MULTIPOLYGON = "MULTIPOLYGON(((";
    public static final String END_OF_MULTIPOLYGON = ")))";

    public static final String START_OF_POLYGON = "POLYGON((";
    public static final String END_OF_POLYGON = "))";

    public static final String SEPARATE_OF_POLYGON = ", ";
    public static final String SEPARATE_OF_MULTIPOLYGON = "\\)\\),\\(\\(";
    public static final String SEPARATE_OF_LAT_AND_LONG = " ";

    public static final String EMPTY = "";

    /**
     * convert from string to geometry
     *
     * @param wktPoint String
     * @return Geometry
     */
    public static Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            if (fromText != null) {
                geom = fromText.read(wktPoint);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }

    /**
     * get Latitude from point
     *
     * @param point
     * @return Latitude
     */
    public static String getLatitude(Point point) {
        return String.valueOf(point.getCoordinate().y);
    }

    /**
     * get Longitude from point
     *
     * @param point
     * @return Longitude
     */
    public static String getLongitude(Point point) {
        return String.valueOf(point.getCoordinate().x);
    }

    /**
     * get Point from latitude & longitude
     *
     * @param longitude : x
     * @param latitude : y
     * @return Point
     */
    public static Point coordinateToPoint(String longitude, String latitude) {
        double y = Double.parseDouble(latitude);
        double x = Double.parseDouble(longitude);
        Point point = new Point(new Coordinate(x, y), new PrecisionModel(), SRID);
        return point;
    }

    /**
     * get Latitude from point
     *
     * @param point
     * @return Latitude
     */
    public static Double getLat(Point point) {
        return point == null ? null : point.getCoordinate().y;
    }

    /**
     * get Longitude from point
     *
     * @param point
     * @return Longitude
     */
    public static Double getLong(Point point) {
        return point == null ? null : point.getCoordinate().x;
    }

    /**
     * get Point from latitude & longitude
     *
     * @param longitude : x
     * @param latitude : y
     * @return Point
     */
    public static Point convertLatLongToPoint(Double longitude, Double latitude) {
        if (latitude == null || longitude == null) {
            return null;
        }
        return new Point(new Coordinate(longitude, latitude), new PrecisionModel(), SRID);
    }

    /**
     * get list of polygon
     *
     * @param multiplePolygon
     * @return list of polygon (format: [x y, x y];[x y, x y])
     */
    public static String getListPolygon(String multiplePolygon) {
        String polygon = "";
        if (StringUtil.isNull(multiplePolygon)) {
            return EMPTY;
        } else if (multiplePolygon.startsWith(START_OF_MULTIPOLYGON)) {
            multiplePolygon = multiplePolygon.replace(START_OF_MULTIPOLYGON,
                    EMPTY);
            multiplePolygon = multiplePolygon.replace(END_OF_MULTIPOLYGON,
                    EMPTY);
            String[] polygons = multiplePolygon.split(SEPARATE_OF_MULTIPOLYGON);
            boolean first = true;
            for (String p : polygons) {
                if (first) {
                    polygon += p;
                } else {
                    polygon += ";" + p;
                }
                first = false;
            }
        } else if (multiplePolygon.startsWith(START_OF_POLYGON)) {
            multiplePolygon = multiplePolygon.replace(START_OF_POLYGON, EMPTY);
            multiplePolygon = multiplePolygon.replace(END_OF_POLYGON, EMPTY);
            polygon = multiplePolygon;
        }
        return polygon;
    }

}
