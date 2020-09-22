package com.example.user.bluetooth;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;
    ToggleButton toggleButton;
    Button btnScan,btnPaired;
    Set<BluetoothDevice> bluetoothDevices;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        btnScan = (Button) findViewById(R.id.buttonScan);
        btnPaired = (Button) findViewById(R.id.buttonPairedDevices);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = (ListView) findViewById(R.id.list);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toggleButton.isChecked()){
                    if(!bluetoothAdapter.isEnabled()){
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent,0);
                        Toast.makeText(MainActivity.this, "Bluetooth Onn", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Already Enabled", Toast.LENGTH_SHORT).show();
                    }
                }else {
                        bluetoothAdapter.disable();
                    Toast.makeText(MainActivity.this, "Bluetooth Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent,1);
            }
        });
        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothDevices = bluetoothAdapter.getBondedDevices();
                ArrayList list = new ArrayList();
                for(BluetoothDevice device:bluetoothDevices){
                    list.add(device.getName());
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
    }
}
