package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ifoto = (ImageView) findViewById(R.id.imageView);
        imageSel = (Button) findViewById(R.id.button30);
        imageSel.setOnClickListener(this);
        logOut=(Button) findViewById(R.id.button31);
        logOut.setOnClickListener(this);
        tNombre=(TextView) findViewById(R.id.textView9);
        tIntentos=(TextView) findViewById(R.id.textView13);
        tCalle = (TextView)findViewById(R.id.textView11);
        tNombre.setText(getUserText());
        usdbh = new CustomSqlLite(getApplicationContext(), this.dbName, null, 2);
        new MyTaskPerfil().execute();



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
                        setImage(selectedImage.getPath());
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
