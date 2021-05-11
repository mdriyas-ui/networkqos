/**
 * 
 */
package com.networkqos;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class VideoList extends ListActivity {
	String selectedfile=null;
	String username=null;
	String filelist=null;
	int icon;
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		icon=R.drawable.ic_launcher;
		username=getIntent().getStringExtra("username");
		filelist=getIntent().getStringExtra("list");
		String[] filenames=filelist.split("@");		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.videofiles,filenames));
		ListView listview = getListView();
		listview.setTextFilterEnabled(true);	
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				selectedfile = ((TextView)view).getText().toString();	
			
				CallTranscodeService service=new CallTranscodeService();
				service.execute();
				}
			
			});	
//		AlertDialog.Builder dia=new AlertDialog.Builder(getApplicationContext());
//		dia.setIcon(icon);
//		dia.setTitle("Network QoS");
//		dia.setMessage("please wait....");
//		dia.setCancelable(false);
//		AlertDialog dlog=dia.create();
//		dlog.show();
		
	}
	private class CallTranscodeService extends AsyncTask<String, Void, Void> {
	
	
	@Override
	protected void onPreExecute() {
		Toast.makeText(getApplicationContext(),"please wait...",Toast.LENGTH_SHORT).show();
		
	}
	@Override
	protected Void doInBackground(String... params) {
		selectedfile=CallServices.treansCoder(selectedfile, username, "Transcode");	
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		Intent intent1=new Intent(getBaseContext(),VideoViewActivity.class);
		intent1.putExtra("selectedfile", selectedfile);
		intent1.putExtra("list", filelist);
		intent1.putExtra("username", username);
		startActivity(intent1);
	}
	
	
	}
	

}
