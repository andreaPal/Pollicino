package com.example.pollicino;

import java.util.List;

import com.example.pollicino.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Stats extends Activity {
	private TextView textPunti;
	private PointDAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		textPunti = (TextView) findViewById(R.id.stats_punti);
		dao = new PointDAO_DB_impl();
		dao.open();
		List<Point> points = dao.getAllPoint();
		textPunti.setText("Numero di punti: "+points.size());
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_stats, menu);
		return true;
	}

}
