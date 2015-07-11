package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerMediaService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private File sdCard;
    private File song;
    private List<File> files;
    private MediaPlayer mediaPlayer;
    private int iteratorSong;

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        PlayerMediaService getService() {
            // Return this instance of LocalService so clients can call public methods
            return PlayerMediaService.this;
        }
    }

    public PlayerMediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        iteratorSong=0;
        sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        files = getListFiles(sdCard);
        song = files.get(iteratorSong);

        try {
            mediaPlayer.setDataSource(song.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    setNextSong();
                }
            });
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");



        return mBinder;
    }

    private List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".mp3")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    public void setNextSong(){

        iteratorSong++;
        if(files.size()<=iteratorSong) iteratorSong=0;
        song = files.get(iteratorSong);

        try {
            mediaPlayer.setDataSource(song.getAbsolutePath());
            mediaPlayer.prepare();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void startSong(){
        mediaPlayer.start();
    }

    public void stopSong(){
        mediaPlayer.stop();
    }

    public void pauseSong(){
        mediaPlayer.pause();
    }
    public void releaseSong(){
        mediaPlayer.release();
    }
    public void nextSong(){
        mediaPlayer.reset();
        iteratorSong++;
        if(files.size()<=iteratorSong) iteratorSong=0;
        song = files.get(iteratorSong);

        try {
            mediaPlayer.setDataSource(song.getAbsolutePath());
            mediaPlayer.prepare();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void prevSong(){
        mediaPlayer.reset();
        iteratorSong--;
        if(iteratorSong<0) iteratorSong=files.size()-1;
        song = files.get(iteratorSong);

        try {
            mediaPlayer.setDataSource(song.getAbsolutePath());
            mediaPlayer.prepare();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getSongName(){
        return song.getName();
    }

    public int getCurrentPos(){
        return mediaPlayer.getCurrentPosition();
    }

    public int getDur(){
        return mediaPlayer.getDuration();
    }


}
