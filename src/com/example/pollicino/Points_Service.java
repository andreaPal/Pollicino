package com.example.pollicino;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class Points_Service extends Service implements LocationListener {
	private PointDAO dao;
	private LocationManager locationManager;
	private String provider;
	SharedPreferences prefs = MyApplication.getContext().getSharedPreferences("SETTINGS", 0);
	private int msec;
	
	public void onCreate() {
		super.onCreate();
		dao = new PointDAO_DB_impl();
		dao.open();
		msec = (prefs.getInt("aggiornamento", 5))*60000;
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    Criteria criteria = new Criteria();
	    //criteria.
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);
		locationManager.requestLocationUpdates(provider, msec, 1, this);
	    if (location != null) {
	        System.out.println("Provider " + provider + " has been selected.");
	        onLocationChanged(location);
	      } else {
	    	  System.out.println("Provider " + provider + " has not been selected.");
	      }
	}

	@Override
	public void onLocationChanged(Location location) {
	    double lat = (location.getLatitude());
	    double lng = (location.getLongitude());
	    dao.insertPoint(new Point(lat,lng));
        System.out.println("Location settata");
        System.out.println("Lat: "+ Double.toString(lat));
	}
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	 public void onDestroy() {
		 dao.close();
	 }


}
