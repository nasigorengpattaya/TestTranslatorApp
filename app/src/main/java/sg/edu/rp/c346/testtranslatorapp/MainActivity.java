package sg.edu.rp.c346.testtranslatorapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class MainActivity extends AppCompatActivity {

    GoogleTranslateActivity translator;
    EditText translateedittext, chineseText;
    TextView translatabletext, chineseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        translateedittext = (EditText) findViewById(R.id.translateedittext);
        Button translatebutton = (Button) findViewById(R.id.translatebutton);

        translatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new EnglishToTagalog().execute();
            }
        });
    }

    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                translator = new GoogleTranslateActivity("AIzaSyDBZoK0ullNEILhsnyUh1BHthcdsLOCqjU");
                Thread.sleep(1000);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(MainActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();
            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void translated(){
//        String translatetomalay = translateedittext.getText().toString();//get the value of text
//        String translatetochinese = chineseText.getText().toString();
        translatabletext = (TextView) findViewById(R.id.translatabletext);
        chineseTextView = (TextView) findViewById(R.id.chineseText);

        String translatetomalay = translatabletext.getText().toString();//get the value of text
        String translatetochinese = chineseTextView.getText().toString();

        String text = translator.translte(translatetomalay, "en", "ms");
        String chinese = translator.translte(translatetochinese, "en", "ko");

        translatabletext.setText(text);
        chineseTextView.setText(chinese);
    }


}
