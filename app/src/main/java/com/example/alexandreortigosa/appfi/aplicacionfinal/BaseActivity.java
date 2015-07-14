package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseActivity extends ActionBarActivity {

    protected String dbName="DBUsuarios";
    protected String prefName="myCustomApp";
    protected String playBool="playerConnected";
    protected SharedPreferences settings;
    protected String userLoggin ="user_loggin";
    protected String Loggin = "bolLoggin";
    protected String intentUserString = "_USER_STRING_";
    protected String imagePerfil = "imgPerfil";

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAMERA_REQUEST = 100;


    protected void setLoggin(String userString){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putString(this.userLoggin, userString);
        editor.putBoolean(this.Loggin, true);

        //Guardamos los cambios
        editor.apply();
    }

    protected void setImage(String imageString){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putString(this.imagePerfil, imageString);



        //Guardamos los cambios
        editor.apply();
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    protected Uri getImage(){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        //Consultamos
         String pathImg=settings.getString(this.imagePerfil, "notFound");
          File file = new File(pathImg);
          Uri fileUri = Uri.fromFile(file);


        return fileUri;
        /*if (pathImg=="notFound") return BitmapFactory.decodeResource(getResources(),R.drawable.interrogante);
        else {
            try {
                File file = new File(pathImg);
                Uri fileUri = Uri.fromFile(file);
                InputStream imageStream = getContentResolver().openInputStream(fileUri);
                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                return BitmapFactory.decodeFile(fileUri.getPath());
            }

            catch (FileNotFoundException e){
                e.printStackTrace();
                return BitmapFactory.decodeResource(getResources(), R.drawable.interrogante);
            }

        }

        return pathImg;*/


    }

    protected String getUserText(){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        //Consultamos
        return settings.getString(this.userLoggin, "notFound");
    }
    protected boolean isLogged(){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        //Consultamos
        return settings.getBoolean(this.Loggin, false);
    }
    protected void logout(){
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences(prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putString(this.userLoggin, "");
        editor.putBoolean(this.Loggin, false);

        //Guardamos los cambios
        editor.apply();

    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    protected void goToLog(){
        Intent intent = new Intent(getApplicationContext(), LoggActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_exit:
                logout();
                goToLog();
                break;
            default:
                return true;

        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
