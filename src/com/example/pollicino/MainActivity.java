package com.example.pollicino;

import java.util.List;

import com.example.pollicino.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private PointDAO dao;
	private TextView textPunti;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		dao = new PointDAO_DB_impl();
		dao.open();
		List<Point> points = dao.getAllPoint();
		setContentView(R.layout.activity_main);
		final Button button_start = (Button) findViewById(R.id.button_start);
		final Button button_stop = (Button) findViewById(R.id.button_stop);
		final Button button_impostazioni = (Button) findViewById(R.id.button_impostazioni);
		final Button button_mappa = (Button) findViewById(R.id.button_mappa);
		final Button button_clean = (Button) findViewById(R.id.button_clean);
		final Toast toast_start = Toast.makeText(this,"Tracking attivato",Toast.LENGTH_LONG);
		final Toast toast_stop = Toast.makeText(this,"Tracking disattivato",Toast.LENGTH_LONG);
		textPunti = (TextView) findViewById(R.id.stats_punti);
		textPunti.setText("Numero di punti: "+ points.size());

		final Intent intent_imp = new Intent(this,Impostazioni.class);
		final Intent intent_mappa = new Intent(this,Mappa.class);

		button_start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toast_start.show();
				textPunti.setText("Numero di punti: tracking... ");				
				button_start.setEnabled(false);
				startService(new Intent(MainActivity.this, Points_Service.class));

			}
		});
		
		button_stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toast_stop.show();
				button_start.setEnabled(true);
				dao = new PointDAO_DB_impl();
				dao.open();
				List<Point> points = dao.getAllPoint();				
				textPunti.setText("Numero di punti: "+ points.size());				
				stopService(new Intent(MainActivity.this, Points_Service.class));
				startActivity(intent_mappa);
			}
		});
		
		button_impostazioni.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(intent_imp);
				
			}
		});
		
		button_mappa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(intent_mappa);
				
			}
		});
		
		
		
		button_clean.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textPunti.setText("Numero di punti: 0");				
				Clean.clean();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
