package com.example.pollicino;

import com.example.pollicino.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Impostazioni extends Activity implements OnSeekBarChangeListener {
	SharedPreferences prefs = MyApplication.getContext().getSharedPreferences("SETTINGS", 0);
	private SeekBar seekbar;
	private TextView textProgress,textProgress2;
	public int sb_progress;
	public int aggiornamento;
	SharedPreferences.Editor editor = prefs.edit();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_impostazioni);
		
		//final TextView set_frequenza = (TextView) findViewById(R.id.set_frequenza);
		seekbar = (SeekBar) this.findViewById(R.id.seekBar1);
		seekbar.setOnSeekBarChangeListener(this);
		textProgress = (TextView) findViewById(R.id.tp);
		final Button button_progress = (Button) findViewById(R.id.button_progress);
		final Button button_void = (Button) findViewById(R.id.button_void);
		button_progress.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editor.putInt("aggiornamento", sb_progress);
				editor.commit();
				finish();
			}
		});
        button_void.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		aggiornamento = prefs.getInt("aggiornamento", 5);
		seekbar.setProgress(aggiornamento);
		textProgress.setText("Intervallo di update: " + Integer.toString(aggiornamento) + " minuti");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_impostazioni, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekbar, int progress, boolean arg2) {
		sb_progress = progress;
		textProgress.setText("Valore settato: " + Integer.toString(sb_progress) + " minuti");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
