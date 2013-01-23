package com.example.pollicino;

import com.google.android.maps.GeoPoint;

public class Point {
	private long id;
	private double lat;
	private double lng;
	
	Point () {}
	Point (double lat, double lng) {
		this.lat=lat;
		this.lng=lng;
	}
	
	Point (long id, double lat, double lng) {
		this.id=id;
		this.lat=lat;
		this.lng=lng;
	}
	
	public long getID() {
		return id;
	}
	
	public void setID(long id) {
		this.id=id;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLng() {
		return lng;
	}
	
	public void setLat(double lat) {
		this.lat=lat;
	}
	
	public void setLng(double lng) {
		this.lng=lng;
	}
	public GeoPoint getGeoPoint() {
		GeoPoint p = new GeoPoint(
	            (int) (lat * 1E6), 
	            (int) (lng * 1E6));
		return p;
	}
	
}
