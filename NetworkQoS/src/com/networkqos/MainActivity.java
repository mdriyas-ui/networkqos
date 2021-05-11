package com.networkqos;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/*
	 * variable declaration
	 */
	static String urlString,bandwidth,phonetype,simstate,networkname,networktype,osversion;
	static TextView bw,ss,nt,pt,nn,uname;
	static Button bn;
	static ProgressBar pb;
	static ProgressDialog pDialog;
	static int icon;
	static String username;
	 /*
     * SIM state constants
     */
    public static final String SIM_ABSENT = "Absent";
    public static final String SIM_READY = "Ready";
    public static final String SIM_PIN_REQUIRED = "PIN required";
    public static final String SIM_PUK_REQUIRED = "PUK required";
    public static final String SIM_NETWORK_LOCKED = "Network locked";
    public static final String SIM_UNKNOWN = "Unknown";

    /*
     * Network type constants
     */
    public static final String NETWORK_CDMA = "CDMA: Either IS95A or IS95B (2G)";
    public static final String NETWORK_EDGE = "EDGE (2.75G)";
    public static final String NETWORK_GPRS = "GPRS (2.5G)";
    public static final String NETWORK_UMTS = "UMTS (3G)";
    public static final String NETWORK_EVDO_0 = "EVDO revision 0 (3G)";
    public static final String NETWORK_EVDO_A = "EVDO revision A (3G - Transitional)";
    public static final String NETWORK_EVDO_B = "EVDO revision B (3G - Transitional)";
    public static final String NETWORK_1X_RTT = "1xRTT  (2G - Transitional)";
    public static final String NETWORK_HSDPA = "HSDPA (3G - Transitional)";
    public static final String NETWORK_HSUPA = "HSUPA (3G - Transitional)";
    public static final String NETWORK_HSPA = "HSPA (3G - Transitional)";
    public static final String NETWORK_IDEN = "iDen (2G)";
    public static final String NETWORK_LTE = "LTE (4G)";
    public static final String NETWORK_EHRPD = "EHRPD (3G)";
    public static final String NETWORK_HSPAP = "HSPAP (3G)";
    public static final String NETWORK_UNKOWN = "Unknown";

    /*
     * Phone type constants
     */
    public static final String PHONE_CDMA = "CDMA";
    public static final String PHONE_GSM = "GSM";
    public static final String PHONE_SIP = "SIP";
    public static final String PHONE_NONE = "No radio";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb=(ProgressBar)findViewById(R.id.progressBar2);
		bn=(Button)findViewById(R.id.bt);
		bw=(TextView)findViewById(R.id.bandwidth);
		pt=(TextView)findViewById(R.id.phone_type);
		nt=(TextView)findViewById(R.id.network_type);
		ss=(TextView)findViewById(R.id.sim_state);
		nn=(TextView)findViewById(R.id.network_name);
		uname=(TextView)findViewById(R.id.uname);
		username=getIntent().getStringExtra("username");
		uname.setText("welcome "+username+",");
		icon=R.drawable.ic_launcher;
		bn.setOnClickListener(new OnClickListener() {
		
		
			@Override
			public void onClick(View v) {
				
				 // Get the telephony system service to find out network details
		        final TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		        // Update text views with readable values.
		        updateViews(tm);

		        // Since these attributes can change, we will register a
		        // {@code PhoneStateListener} to listen for these changes and
		        // update the view.
		        tm.listen(new PhoneStateListener() {
		            @Override
		            public void onServiceStateChanged(ServiceState serviceState) {
		                // Update our TextViews
		                updateViews(tm);
		            }

		            @Override
		            public void onDataConnectionStateChanged(int state) {
		                // A change in data connection state may be due to availability
		                // of a different network type
		                updateViews(tm);
		            }

		        }, PhoneStateListener.LISTEN_SERVICE_STATE | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
				
		     // calculate bandwidth
				 Downloadfile df=new Downloadfile();
					df.execute(); 
			}
		});
		
	}
	
//	 @Override
//	    protected void onResume() {
//	        super.onResume();
//	        final TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//
//	        // Update text views with readable values.
//	        updateViews(tm);
//	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 /**
     * Update text views with telephony attributes.
     */
    private final void updateViews(TelephonyManager tm) {

        // The telephony system service returns integer constants for various
        // telephony attributes.
       simstate=tm.getSimSerialNumber();
       phonetype=mapDeviceTypeToName(tm.getPhoneType());
       networkname=tm.getNetworkOperatorName();
       networktype= mapNetworkTypeToName(tm.getNetworkType());
       osversion=Build.VERSION.RELEASE;
        ss.setText("SIM State: " + simstate);       
        nt.setText("Network Type: " +networktype);       
        pt.setText("Phone Type: " + phonetype);     
        nn.setText("Network Operator: " +networkname);
      
    }

    /**
     * Returns a string describing the current SIM state.
     */
    private static String mapSimStateToName(int simState) {
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                return SIM_ABSENT;
            case TelephonyManager.SIM_STATE_READY:
                return SIM_READY;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                return SIM_PIN_REQUIRED;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                return SIM_PUK_REQUIRED;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                return SIM_NETWORK_LOCKED;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                return SIM_UNKNOWN;
            default:
                // shouldn't happen.
                return null;
        }
    }

    /**
     * Returns a string indicating the phone radio type.
     */
    private static String mapDeviceTypeToName(int device) {

        switch (device) {
            case TelephonyManager.PHONE_TYPE_GSM:
                return PHONE_GSM;
    
            case TelephonyManager.PHONE_TYPE_NONE:
                return PHONE_NONE;
            default:
                // shouldn't happen.
                return null;
        }
    }

    /**
     * Returns a string describing the network type.
     */
    public static String mapNetworkTypeToName(int networkType) {

        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return NETWORK_EDGE;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return NETWORK_EDGE;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return NETWORK_UMTS;
    
//            case TelephonyManager.NETWORK_TYPE_HSPAP:
  //              return NETWORK_HSPAP;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return NETWORK_UNKOWN;
        }
    }
	class Downloadfile extends AsyncTask<String, Void, Void> {
		String list=null;
		@Override
		protected Void doInBackground(String... params) {
			//urlString="http://llc.mtsac.edu/handouts/powerpoint.pdf";	
			urlString="http://download.wavetlan.com/SVV/Media/HTTP/H264/Talkinghead_Media/H264_test3_Talkingheadclipped_mp4_480x360.mp4";
			bandwidth=new Bandwidth().getBandwidth(urlString);			
			list=CallServices.getVideosFromMobileInfo(bandwidth,phonetype,networkname,simstate,networktype,osversion,username, "mobileinfo");
			return null;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Void result) {				
			bw.setText("Network Bandwidth: "+bandwidth+" kbps");				
			pb.setVisibility(View.INVISIBLE);		
			
			pDialog = new ProgressDialog(MainActivity.this);
			// Set progressbar title
			pDialog.setTitle("Network QoS");
			pDialog.setIcon(icon);
			// Set progressbar message
			pDialog.setMessage("successfully retrived the list");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.setButton("ok", new android.content.DialogInterface.OnClickListener(){

				@Override
				public void onClick(android.content.DialogInterface arg0, int arg1) {
					Intent intent1=new Intent(getBaseContext(),VideoList.class);
					intent1.putExtra("list", list);
					intent1.putExtra("username", username);
					startActivity(intent1);					
				}
				});
			
			// Show progressbar
			pDialog.show();
			
		}
		
		@Override
		protected void onPreExecute() {
			pb.setVisibility(View.VISIBLE);
			
			
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			
		}
	}

}
