package com.example.user.multipurpose;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MediaPlayer extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Intent intent = new Intent(MediaPlayer.this,PlayListActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.button2){
            Intent intent = new Intent(MediaPlayer.this,VideoStoredInSDCard.class);
            startActivity(intent);

        }

    }
    public void createShortcut(){
        Intent intentShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        Parcelable appicon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.icons8_circledplayfilled);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, appicon);
        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getApplicationContext(), MainActivity.class));
        intentShortcut.putExtra("duplicate", false);
        sendBroadcast(intentShortcut);
    }
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MediaPlayer.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MediaPlayer.this.finish();
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
