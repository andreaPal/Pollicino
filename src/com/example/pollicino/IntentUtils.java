package com.example.pollicino;

import android.content.Intent;
import android.net.Uri;
import android.app.Activity;


public class IntentUtils {

	
	public static void navigatore(Activity activity, Point fine) {
		String coordinates[] = {"41.9058877", "12.4824104"};

		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
				//Uri.parse("google.navigation:q=" + fine.getLat() + "," + fine.getLng()));
			    Uri.parse("http://maps.google.com/maps?saddr="+fine.getLat()+","+fine.getLng()+"&daddr="+"41.9058877"+","+"12.4824104"));
		activity.startActivity(intent);
	}
}