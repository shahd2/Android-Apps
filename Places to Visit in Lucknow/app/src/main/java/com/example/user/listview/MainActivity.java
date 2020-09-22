package com.example.user.listview;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String[] Places = {"Historical Monuments","Nature & Parks","Temples","Markets","Malls"};
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,Places);
    listView.setAdapter(arrayAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0){
                startActivity(new Intent(MainActivity.this,HistoricalMonuments.class));
            }
            if(position == 1){
                startActivity(new Intent(MainActivity.this,NatureParks.class));
            }
            if(position == 2){
                startActivity(new Intent(MainActivity.this,Temples.class));
            }
            if(position == 3){
                startActivity(new Intent(MainActivity.this,Markets.class));
            }
            if(position == 4){
                startActivity(new Intent(MainActivity.this,Malls.class));
            }
        }
    });
}

}
