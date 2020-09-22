package com.example.user.translatorhci;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class MainActivity extends Activity  {

    private TextToSpeech tts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView text1=(TextView) findViewById(R.id.text);
      /*  tts = new TextToSpeech(this, this);
        ((Button) findViewById(R.id.btn2)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                speakOut(((TextView) findViewById(R.id.text)).getText().toString());
            }
        });
*/
        ((Button) findViewById(R.id.btn1)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                class bgStuff extends AsyncTask<Void, Void, Void>{

                    String translatedText = "";
                    @Override
                    protected Void doInBackground(Void... params) {
                        // TODO Auto-generated method stub
                        try {
                            String text = ((EditText) findViewById(R.id.etxt1)).getText().toString();
                            //Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();
                            translatedText = translate(text);
                            //Toast.makeText(MainActivity.this, translatedText, Toast.LENGTH_SHORT).show();
                            ((TextView) findViewById(R.id.text)).setText(translatedText);

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
                        ((TextView) findViewById(R.id.text)).setText(translatedText);
                        super.onPostExecute(result);
                    }

                }

                new bgStuff().execute();
            }
        });
    }

    public String translate(String text) throws Exception{


        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
         //change


        Translate.setClientId("HciTranslator"); //Change this
        Translate.setClientSecret("XpGeDVfJWoBr4JXSv/n763REdv72UgJwT0L+ItVtoPo=");
        String translatedText = "";

        translatedText = Translate.execute(text,Language.AUTO_DETECT,Language.CHINESE_SIMPLIFIED);

        return translatedText.toString();
    }


  /*  public void onInit(int status) {
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
    }*/
}