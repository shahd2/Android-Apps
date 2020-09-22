package com.example.user.maploc;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    GoogleMap mMap;
    LocationManager locationManager;
    double lati,longi;
    String phone1,phone2,phone3,msg;
    String address,city,state,country,postalCode,knownName;
    //  Location location;
    TextView add;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mapFragment.getMapAsync(this);
        add= (TextView) findViewById(R.id.textView8);
        SharedPreferences sharedPreferences1 = getSharedPreferences("NUMBER", MODE_PRIVATE);
        phone1 = sharedPreferences1.getString("NUM1", "No data found");
        phone2 = sharedPreferences1.getString("NUM2", "No data found");
        phone3 = sharedPreferences1.getString("NUM3", "No data found");
        SharedPreferences sharedPreferences2 = getSharedPreferences("MESSAGE", MODE_PRIVATE);
        msg= sharedPreferences2.getString("MSG", " ");
        
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

        button= (Button) findViewById(R.id.buttonsend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
               // Toast.makeText(MainActivity.this,knownName,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.phone:
                startActivity(new Intent(MapsActivity.this, phonenum.class));
                break;
            case R.id.message:
                startActivity(new Intent(MapsActivity.this, message.class));
                break;
            /*case R.id.enable:
                createShortcut(1);
                break;
            case R.id.disable:
                createShortcut(0);
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
    protected void sendSMSMessage() {
        //String uri =msg+" http://maps.google.com/maps?saddr=" +lati+","+longi;
        //String uri =msg+" https://www.google.com/maps/preview/@" +lati+","+longi;
        SmsManager smsManager = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        /*http://maps.google.com?q=
        https://www.google.com/maps/preview/@*/
        smsBody.append(msg);
        smsBody.append(" https://google.com/maps?q=");
        smsBody.append(lati);
        smsBody.append(",");
        smsBody.append(longi);
        smsBody.append("\n");
        smsBody.append(address+","+city+","+state+","+country+","+postalCode+".");
        smsManager.sendTextMessage(phone1, null, smsBody.toString(), null, null);
        smsManager.sendTextMessage(phone2, null, smsBody.toString(), null, null);
        smsManager.sendTextMessage(phone3, null, smsBody.toString(), null, null);
    }

    @Override
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
    /*public void createShortcut(){
        Intent intentShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        Parcelable appicon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.iconsmarker);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, appicon);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getApplicationContext(), MapsActivity.class));
        intentShortcut.putExtra("duplicate", false);
        sendBroadcast(intentShortcut);
    }*/
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            Toast.makeText(MapsActivity.this,"Sending message on Up",Toast.LENGTH_SHORT).show();
            sendSMSMessage();
            return true;
        }
        return false;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            Toast.makeText(MapsActivity.this,"Sending message on Down",Toast.LENGTH_SHORT).show();
            sendSMSMessage();
            return true;
        }
        return false;
    }
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MapsActivity.this.finish();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}