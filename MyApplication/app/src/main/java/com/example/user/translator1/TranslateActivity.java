package com.example.user.translator1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
 
public class TranslateActivity extends AppCompatActivity implements OnInitListener,OnMapReadyCallback, LocationListener {
 
    public static final String EXTRA_MESSAGE = null;
	private TextToSpeech tts;
	private String Lang = "ENGLISH";
	GoogleMap mMap;
	TextView add;
	double lati,longi;
	String address,city,state,country,postalCode,knownName;
	LocationManager locationManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		mapFragment.getMapAsync(this);
		add= (TextView) findViewById(R.id.textView8);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
        //Speak Button
        tts = new TextToSpeech(this, this);
        ((Button) findViewById(R.id.bSpeak)).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	if (Lang.equals("GERMAN")) {
               	 tts.setLanguage(Locale.GERMAN);
               	
       	       }
       	       if (Lang.equals("ENGLISH")) {
       	    	   tts.setLanguage(Locale.ENGLISH);
       	       }
       	       if (Lang.equals("CHINESE")) {
       	    	   tts.setLanguage(Locale.CHINESE);
       	       }
       	       if (Lang.equals("JAPANESE")) {
       	    	  tts.setLanguage(Locale.JAPANESE);
       	       }
       	       if (Lang.equals("FRENCH")) {
       	    	  tts.setLanguage(Locale.FRENCH);
       	       }
       	       if (Lang.equals("KOREAN")) {
       	    	   tts.setLanguage(Locale.KOREAN);
       	       }
       	       if (Lang.equals("ITALIAN")) {
       	    	   tts.setLanguage(Locale.ITALIAN);
       	       }
       	    if (((TextView) findViewById(R.id.tvTranslatedText)).getText().toString().equals("")){
       	    	speakOut(((TextView) findViewById(R.id.txtMessage1)).getText().toString());
        }else{
        	speakOut(((TextView) findViewById(R.id.tvTranslatedText)).getText().toString());
        }
            }
        });
        
        //Translate Button
        ((Button) findViewById(R.id.bTranslate)).setOnClickListener(new OnClickListener() {

        	@Override
        	public void onClick(View v) {
        	  // TODO Auto-generated method stub
        	   
        	   
        	   
        	  class bgStuff extends AsyncTask<Void, Void, Void>{
        	       
        	      String translatedText = "";
        	      @Override
        	      protected Void doInBackground(Void... params) {
        	          // TODO Auto-generated method stub
        	          try {
        	              String text = ((TextView) findViewById(R.id.txtMessage1)).getText().toString();
        	              translatedText = translate(text,Lang);
        	          } catch (Exception e) {
        	              // TODO Auto-generated catch block
        	              e.printStackTrace();
        	              translatedText = e.toString();
        	          }
        	           
        	          return null;
        	      }

        	      @Override
        	      protected void onPostExecute(Void result) {
        	          // TODO Auto-generated method stub
        	          ((TextView) findViewById(R.id.tvTranslatedText)).setText(translatedText);
        	          super.onPostExecute(result);
        	      }
        	       
        	  }
        	   
        	  new bgStuff().execute();
        	}
        	});

        
      ///Language Spinners 
        Spinner spinner = (Spinner) findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ((Spinner) findViewById(R.id.language_spinner)).setOnItemSelectedListener(new OnItemSelectedListener() {
            
        	public void onItemSelected(AdapterView<?> parent, View view, 
                    int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Lang= (String) parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
   }
 
  ///translator 
  	 public String translate(String text, String Lang) throws Exception{
  	       
  	        
  		    // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
		 Translate.setClientId("HCI1"); //Change this
		 Translate.setClientSecret("b/rEUyP31oliQaLdmP711nTxGyY3sOyI1UaqC4urMRM=");
  		        
  		       String translatedText = "";
  		       if (Lang.equals("GERMAN")) {
  		    	   translatedText = Translate.execute(text,Language.GERMAN);
  		       }
  		       if (Lang.equals("ENGLISH")) {
  		    	   translatedText = Translate.execute(text,Language.ENGLISH);
  		       }
  		       if (Lang.equals("CHINESE")) {
  		    	   translatedText = Translate.execute(text,Language.CHINESE_SIMPLIFIED);
  		       }
  		       if (Lang.equals("JAPANESE")) {
  		    	   translatedText = Translate.execute(text,Language.JAPANESE);
  		       }
  		       if (Lang.equals("FRENCH")) {
  		    	   translatedText = Translate.execute(text,Language.FRENCH);
  		       }
  		       if (Lang.equals("KOREAN")) {
  					translatedText = Translate.execute(text,Language.KOREAN);
  		       }
  		       if (Lang.equals("ITALIAN")) {
  		    	   translatedText = Translate.execute(text,Language.ITALIAN);
  		       }
  		       
  		       //translatedText = Translate.execute(text,Language.GERMAN);
  		        
  		       return translatedText;
  		   }
 
@Override
public void onInit(int status) {
    // TODO Auto-generated method stub
    if (status == TextToSpeech.SUCCESS) {
          
        int result = tts.setLanguage(Locale.GERMAN);
 
        if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.e("TTS", "This Language is not supported");
        } else {
             
            //speakOut("Ich");
        }
 
    } else {
        Log.e("TTS", "Initilization Failed!");
    }
}
    
private void speakOut(String text) {
    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
}

	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		//LatLng loc = new LatLng(lati,longi);
		//mMap.addMarker(new MarkerOptions().position(loc).title("HERE"));
		//mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
		//Toast.makeText(this,"My Location:"+loc, Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onLocationChanged(Location location) {
		mMap.clear();
		lati = location.getLatitude();
		longi = location.getLongitude();
		LatLng latLng = new LatLng(lati,longi);
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.title("I'm here!");
		markerOptions.position(latLng);
		mMap.addMarker(markerOptions);
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17.0f));
		//mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17.0f));
		Geocoder geocoder;
		List<Address> addresses;
		geocoder = new Geocoder(this, Locale.getDefault());
		Log.e("latitude", "latitude--" + lati);
		try {
			Log.e("latitude", "inside latitude--" + lati);
			addresses = geocoder.getFromLocation(lati, longi, 1);
			if (addresses != null && addresses.size() > 0) {
				address = addresses.get(0).getAddressLine(0);
				city = addresses.get(0).getLocality();
				state = addresses.get(0).getAdminArea();
				country = addresses.get(0).getCountryName();
				postalCode = addresses.get(0).getPostalCode();
				knownName = addresses.get(0).getFeatureName();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add.setText(address+", "+city+", "+state+".");
      /* char x= (char) lati;
        latitude.setText(x);
        char y= (char) longi;
        longitude.setText(y);*/
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}
	@Override
	public void onProviderEnabled(String provider) {}
	@Override
	public void onProviderDisabled(String provider) {}

}