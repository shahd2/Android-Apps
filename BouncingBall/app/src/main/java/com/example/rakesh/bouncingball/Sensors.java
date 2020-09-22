package com.example.rakesh.bouncingball;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.ListView;

/**
 * Shows a list of all Sensors on this phone.
 *
 *
 */
public class Sensors extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);

        ListView sensorList = (ListView)findViewById(R.id.sensor_list);
        sensorList.setAdapter(new SensorListAdapter(this));
    }
}