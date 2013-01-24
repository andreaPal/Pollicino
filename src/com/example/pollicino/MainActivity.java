package com.example.pollicino;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button_start = (Button) findViewById(R.id.button_start);
		final Button button_stop = (Button) findViewById(R.id.button_stop);
		final Button button_impostazioni = (Button) findViewById(R.id.button_impostazioni);
		final Button button_mappa = (Button) findViewById(R.id.button_mappa);
		final Button button_stats = (Button) findViewById(R.id.button_stats);
		final Button button_clean = (Button) findViewById(R.id.button_clean);


		final Intent intent_imp = new Intent(this,Impostazioni.class);
		final Intent intent_mappa = new Intent(this,Mappa.class);
		final Intent intent_stats = new Intent(this,Stats.class);



		button_start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService(new Intent(MainActivity.this, Points_Service.class));

			}
		});
		
		button_stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
		
		button_stats.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(intent_stats);
			}
		});
		
		button_clean.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
