package com.example.pollicino;

import android.content.Intent;
import android.net.Uri;
import android.app.Activity;


public class IntentUtils {

	
	public static void navigatore(Activity activity, Point fine) {
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
		  Uri.parse("google.navigation:q=" + fine.getLat() + "," + fine.getLng()));
		activity.startActivity(intent);
	}
}
