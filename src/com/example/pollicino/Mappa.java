package com.example.pollicino;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class Mappa extends MapActivity implements LocationListener {
	private PointDAO dao;
    private MapView mapView;
    private MapController mc;
    private Punti_Mappa itemizedoverlay;

    // Otteniamo il riferimento al LocationManager
    LocationManager locationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mappa);
		dao = new PointDAO_DB_impl();
		dao.open();
		List<Point> points = dao.getAllPoint();
		mapView = (MapView) findViewById(R.id.mapview1);
		mapView.setBuiltInZoomControls(true);
		mc = mapView.getController();
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedoverlay = new Punti_Mappa(drawable, this);
		for (Point p1:points) {
			System.out.println(Double.toString(p1.getLat()));
			OverlayItem overlayitem = new OverlayItem(p1.getGeoPoint(), "Coordinate", "Lat: "+Double.toString(p1.getLat())+ " Lng: "+Double.toString(p1.getLng()));
			itemizedoverlay.addOverlay(overlayitem);
		}
		itemizedoverlay.populateNow();
		mapOverlays.add(itemizedoverlay);
		//Point p2 = dao.getLastPoint();
		/*if (p2 != null) {
			GeoPoint gp = new GeoPoint (
					(int) (p2.getLat() * 1E6),
					(int) (p2.getLng() * 1E6));
		    mc.animateTo(gp);
		}*/
  
	    //mc.setZoom(17); 
	    mapView.invalidate(); 
	}
	
	protected boolean isRouteDisplayed() {
	    return false;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		super.onCreateOptionsMenu(menu);
		int base = menu.FIRST;
		MenuItem item_naviga = menu.add(base, 1, 1, "Vai verso ultimo punto");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId()==1) chiama_navigatore();
		else return super.onOptionsItemSelected(item);
		return true;
	}

	private void chiama_navigatore() {
		Point point = dao.getLastPoint();
		IntentUtils.navigatore(this,point);
		dao.deletePoint(point);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}

