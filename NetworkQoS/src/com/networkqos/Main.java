/**
 * 
 */
package com.networkqos;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	
	/*
	 * variable declaration
	 */
	String urlString,bandwidth;
	EditText uname,pass;
	TextView signin,signup;
	ProgressBar mainpb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		uname=(EditText)findViewById(R.id.loguser);
		pass=(EditText)findViewById(R.id.logpass2);
		mainpb=(ProgressBar)findViewById(R.id.mainpb);
		signin=(TextView)findViewById(R.id.signin);
		signup=(TextView)findViewById(R.id.signup);
		signin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActiveTask task=new ActiveTask();
				task.execute();
			}
		});
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1=new Intent(getBaseContext(), Register.class);
				startActivity(intent1);
			}
		});
	}
	
	private class ActiveTask extends AsyncTask<String,Void,Void> {
		String username=uname.getText().toString();
		String password=pass.getText().toString();
		String res=null;
		@Override
		protected void onPreExecute() {
			mainpb.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			res=CallServices.loginService(username, password, "signin");
//			res = CallAnotherService.responseService(username, password, "signin");
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if(res.equals("success")) {			
			Intent intent=new Intent(getBaseContext(),SelectOption.class);
			intent.putExtra("username",username);
			startActivity(intent);
			}
			else {
				Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
			}
			mainpb.setVisibility(View.INVISIBLE);
		}
		
	}

}
