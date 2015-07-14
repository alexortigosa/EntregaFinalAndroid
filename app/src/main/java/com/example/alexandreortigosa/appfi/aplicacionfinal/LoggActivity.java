package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class LoggActivity extends BaseActivity {

    private CustomSqlLite usdbh;
    private SQLiteDatabase db;
    private EditText eUser;
    private EditText ePassword;
    ImageView image;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Loggin", "Punto 1");
        setContentView(R.layout.activity_logg);
        eUser = (EditText) findViewById(R.id.editTextUser);
        ePassword = (EditText) findViewById(R.id.editTextPassword);
        usdbh = new CustomSqlLite(getApplicationContext(), this.dbName, null, 2);
        image = (ImageView) findViewById(R.id.imageView2);



        if(isLogged()){
            goSucces();
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream in = new URL("http://img3.wikia.nocookie.net/__cb20111019225432/es.gta/images/2/24/Android_Logo.png").openStream();
                    bmp = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    // log error
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (bmp != null)
                    image.setImageBitmap(bmp);
            }

        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void validateUser(View view) throws ExecutionException, InterruptedException {


        new MyTask().execute();



    }



    private void goSucces(){
        Intent intent = new Intent(getApplicationContext(), AcceptLoggin.class);
        //intent.putExtra(intentUserString, eUser.getText());
        if(!isLogged()) setLoggin( eUser.getText() + "");
        startActivity(intent);
    }

    private void goDecline(){
        Intent intent = new Intent(getApplicationContext(), DeclineLoggin.class);
        startActivity(intent);
    }


    public class MyTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Boolean aVoid) {

            super.onPostExecute(aVoid);
            if(!aVoid){
                goDecline();
            }
            else{
                goSucces();
            }

        }

        @Override
        protected Boolean doInBackground(Void... params) {

                EditText eUser = (EditText) findViewById(R.id.editTextUser);
                EditText ePassword = (EditText) findViewById(R.id.editTextPassword);
                boolean res = false;

                    db = usdbh.getWritableDatabase();
                    Cursor c = db.rawQuery(" SELECT * FROM Usuarios WHERE nombre='" + eUser.getText() + "' AND password='" + ePassword.getText() + "'", null);
                    //Nos aseguramos de qu
                    // e existe al menos un registro
                    if (c.getCount() != 0) res = true;



            return res;
        }
    }

}
