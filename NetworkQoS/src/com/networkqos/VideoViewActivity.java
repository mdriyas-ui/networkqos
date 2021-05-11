package com.networkqos;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

	// Declare variables
	ProgressDialog pDialog;
	VideoView videoview;
	int icon;
	

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		icon=R.drawable.ic_launcher;
		String selectedfile=getIntent().getStringExtra("selectedfile");
		final String username=getIntent().getStringExtra("username");
//		selectedfile=CallServices.treansCoder(selectedfile, username, "Transcode");	
		//Toast.makeText(getApplicationContext(),selectedfile, Toast.LENGTH_SHORT).show();
		final String filelist=getIntent().getStringExtra("list");
		
		// Insert your Video URL
		String VideoURL = "http://192.168.43.202:8080/NetworkQoSService/videos1/"+selectedfile;
		// Get the layout from video_main.xml
		//setContentView(R.layout.videoview_main);
		// Find your VideoView in your video_main.xml layout
//		videoview = (VideoView) findViewById(R.id.VideoView);
//		// Execute StreamVideo AsyncTask
//
//		// Create a progressbar
		pDialog = new ProgressDialog(VideoViewActivity.this);
		// Set progressbar title
		pDialog.setTitle("Network QoS");
		pDialog.setIcon(icon);
		// Set progressbar message
		pDialog.setMessage("Thank you for watching");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.setButton("OK", new android.content.DialogInterface.OnClickListener(){

			@Override
			public void onClick(android.content.DialogInterface arg0, int arg1) {
				Intent intent1=new Intent(getBaseContext(),VideoList.class);
				intent1.putExtra("list", filelist);
				intent1.putExtra("username", username);
				startActivity(intent1);
				
			}
			});
		
		
		// Show progressbar
		pDialog.show();

		try {
			// Start the MediaController
//			MediaController mediacontroller = new MediaController(
//					VideoViewActivity.this);
//			mediacontroller.setAnchorView(videoview);
//			// Get the URL from String VideoURL
//			Uri video = Uri.parse(VideoURL);
//			videoview.setMediaController(mediacontroller);
//			videoview.setVideoURI(video);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.parse(VideoURL), "video/*");
			startActivity(intent);
			

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}

//		videoview.requestFocus();
//		videoview.setOnPreparedListener(new OnPreparedListener() {
//			// Close the progress bar and play the video
//			public void onPrepared(MediaPlayer mp) {
//				pDialog.dismiss();
//				videoview.start();
//			}
//		});

	}

}
