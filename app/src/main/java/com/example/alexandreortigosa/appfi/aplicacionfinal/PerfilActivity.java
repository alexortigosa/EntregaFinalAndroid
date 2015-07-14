package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;


public class PerfilActivity extends BaseActivity implements View.OnClickListener{
    private static final int SELECT_PHOTO = 100;
    private CustomSqlLite usdbh;
    private SQLiteDatabase db;
    ImageView ifoto;
    Button imageSel;
    Button logOut;
    TextView tNombre;
    TextView tIntentos;
    TextView tCalle;
    File fFoto;
    // Acquire a reference to the system Location Manager
    LocationManager lm;
    LocationListener lis;
    List<Address> l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        l = null;
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        ifoto = (ImageView) findViewById(R.id.imageView);
        imageSel = (Button) findViewById(R.id.button30);
        imageSel.setOnClickListener(this);
        logOut=(Button) findViewById(R.id.button31);
        logOut.setOnClickListener(this);
        tNombre=(TextView) findViewById(R.id.textView9);
        tIntentos=(TextView) findViewById(R.id.textView13);
        tCalle = (TextView)findViewById(R.id.textView11);
        tCalle.setText("");
        tNombre.setText(getUserText());
        usdbh = new CustomSqlLite(getApplicationContext(), this.dbName, null, 2);
        inicializarFoto();
        new MyTaskPerfil().execute();
        lis = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                Geocoder gc = new Geocoder(getApplicationContext());
                try {
                    l = gc.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < l.size(); ++i) {
                    Log.v("LOG", l.get(i).getAddressLine(0).toString());

                    if(i==0) tCalle.setText("");
                    tCalle.setText(tCalle.getText() + "\n" + l.get(i).getAddressLine(0).toString());
                }
                Log.v("LOG", ((Double) location.getLatitude()).toString());
                lm.removeUpdates(lis);
            }
        };

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, lis);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lis);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
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
    private void selectImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
    private void inicializarFoto(){
        try {
            Uri selectedImage = getImage();
            InputStream imageStream = getContentResolver().openInputStream(selectedImage);
            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
            ifoto.setImageBitmap(yourSelectedImage);

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button30:
                selectImage();
                break;
            case R.id.button31:
                logout();
                goToLog();

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){

                    try {
                        Uri selectedImage = imageReturnedIntent.getData();

                        setImage(getRealPathFromURI(getApplicationContext(), selectedImage));
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        ifoto.setImageBitmap(yourSelectedImage);

                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }

                }
        }
    }

    public void getAddressForLocation(Location location) throws IOException {

        if (location == null) {


            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            int maxResults = 1;

            Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = gc.getFromLocation(latitude, longitude, maxResults);

            if (addresses.size() == 1) {
                tCalle.setText(addresses.get(0).getAddressLine(0));
            }

        }
    }







    public class MyTaskPerfil extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Integer aVoid) {

            super.onPostExecute(aVoid);
            tIntentos.setText(aVoid+"");

        }

        @Override
        protected Integer doInBackground(Void... params) {


            int res = 0;

            db = usdbh.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT MAX(intentos) FROM Ranking WHERE nombre='" + tNombre.getText() + "'", null);
            //Nos aseguramos de qu
            // e existe al menos un registro
            if (c.getCount() != 0){
                c.moveToFirst();
                res=c.getInt(0);
            }



            return res;
        }
    }
}
