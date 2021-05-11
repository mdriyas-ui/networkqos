package com.networkqos;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SelectOption extends Activity {
	TextView watch,upload,unamedisp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_option);
		final String uname = getIntent().getStringExtra("username");
		  final String URL="http://192.168.43.202:8080/NetworkQoSService/";
		unamedisp = (TextView)findViewById(R.id.unamedisp);
		unamedisp.setText("welcome,"+uname);
		watch = (TextView)findViewById(R.id.viewstream);
		upload = (TextView)findViewById(R.id.uploadvideo);
		watch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(),MainActivity.class);
				i.putExtra("username", uname);
				startActivity(i);
			}
		});
		upload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent ij = new Intent(Intent.ACTION_VIEW);
				ij.setData(Uri.parse(URL));
				startActivity(ij);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_option, menu);
		return true;
	}

}
