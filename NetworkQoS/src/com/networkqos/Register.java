/**
 * 
 */
package com.networkqos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Register extends Activity {
	
	EditText reguser1,regpass1,regconfirm,regemail;
	Button regsub;
	ProgressBar pb;
	
	int draw1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeration);		
		reguser1=(EditText)findViewById(R.id.reguser1);
		regpass1=(EditText)findViewById(R.id.regpass1);
		regconfirm=(EditText)findViewById(R.id.regconfirm);
		regemail=(EditText)findViewById(R.id.regemailadd);
		regsub=(Button)findViewById(R.id.regsub);
		pb=(ProgressBar)findViewById(R.id.regpb);
		draw1=R.drawable.ic_launcher;
		
		regsub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!(regpass1.getText().toString().equals(regconfirm.getText().toString()))) {
					Toast.makeText(getApplicationContext(), "password and confirm password should be equal", Toast.LENGTH_SHORT).show();
					
				}
				else if(!((reguser1.getText().toString().equals("")) && (regpass1.getText().toString().equals("")) && (regconfirm.getText().toString().equals("")) && (regemail.getText().toString().equals("")))) {
					ActiveTask1 task=new ActiveTask1();
					task.execute();
				}	
				else {
					Toast.makeText(getApplicationContext(), "enter values in all field", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
		String username=reguser1.getText().toString();
		String password=regpass1.getText().toString();
		String email=regemail.getText().toString();
		String res=null;
		@Override
		protected void onPreExecute() {
			pb.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			res=CallServices.registerService(username, password,email,"signup");
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			pb.setVisibility(View.INVISIBLE);
			if(res.equals("success")) {
				AlertDialog.Builder dia=new AlertDialog.Builder(Register.this);
				dia.setIcon(draw1);
				dia.setTitle("Trust Agent");
				dia.setMessage("account created");	
				dia.setCancelable(false);	
				dia.setPositiveButton("Got it", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {					
						Intent intent=new Intent(getBaseContext(),MainActivity.class);
						intent.putExtra("username",username);
						startActivity(intent);
						dialog.cancel();
						
					}				
				});
				AlertDialog dialo=dia.create();
				dialo.show();
			}
			else {
				Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
			}
			
		}
	}
		
}
