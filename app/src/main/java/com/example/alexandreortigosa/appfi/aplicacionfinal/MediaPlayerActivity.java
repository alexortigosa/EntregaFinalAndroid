package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener{

    PlayerMediaService mService;
    private boolean mBound;
    private ImageButton bPlay;
    private ImageButton bStop;
    private ImageButton bNext;
    private ImageButton bBack;
    private TextView tName, duration;;
    private SeekBar seekbar;
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            PlayerMediaService.LocalBinder binder = (PlayerMediaService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        bPlay = (ImageButton) findViewById(R.id.media_play);
        bPlay.setOnClickListener(this);
        bStop = (ImageButton) findViewById(R.id.media_pause);
        bStop.setOnClickListener(this);
        bNext = (ImageButton) findViewById(R.id.media_ff);
        bNext.setOnClickListener(this);
        bBack = (ImageButton) findViewById(R.id.media_rew);
        bBack.setOnClickListener(this);
        tName = (TextView) findViewById(R.id.songName);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setClickable(false);

        duration = (TextView) findViewById(R.id.songDuration);
        /*//Instanciamos el SharedPreferences
        SharedPreferences settings = getSharedPreferences(this.prefName, Context.MODE_PRIVATE);
        //Consultamos
        boolean silent = settings.getBoolean(this.playBool,false);*/
        //if(!silent) {
        Intent intent = new Intent(this, PlayerMediaService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        //}
        //tName.setText(mService.getSongName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media_player, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();


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

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        /*//Instanciamos el SharedPreferences
        settings = getSharedPreferences(this.prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putBoolean(this.playBool, true);
        //Guardamos los cambios
        editor.apply();*/
        super.onStop();

    }

    @Override
    protected void onDestroy() {

        /*settings = getSharedPreferences(this.prefName, 0);
        //Obtenemos el editor
        SharedPreferences.Editor editor = settings.edit();
        //Editamos
        editor.putBoolean(this.playBool, false);
        //Guardamos los cambios
        editor.apply();*/
        // Unbind from the service
        mService.stopSong();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        super.onDestroy();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.media_play:
                mService.startSong();
                seekbar.setMax((int) finalTime);
                finalTime = mService.getDur();
                timeElapsed = mService.getCurrentPos();
                seekbar.setProgress((int) timeElapsed);
                durationHandler.postDelayed(updateSeekBarTime, 100);

                break;
            case R.id.media_pause:
                mService.pauseSong();
                break;
            case R.id.media_ff:
                mService.stopSong();
                mService.nextSong();
                mService.startSong();
                //tName.setText(mService.getSongName());
                break;
            case R.id.media_rew:
                mService.stopSong();
                mService.prevSong();
                mService.startSong();
                //tName.setText(mService.getSongName());
                break;
            default:
                break;
        }
    }

    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            //get current position
            timeElapsed = mService.getCurrentPos();
            //set seekbar progress
            seekbar.setProgress((int) timeElapsed);
            //set time remaing
            double timeRemaining = finalTime - timeElapsed;
            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);
        }
    };
}
