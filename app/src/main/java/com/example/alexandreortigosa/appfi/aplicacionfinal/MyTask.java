package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by alexandreortigosa on 6/7/15.
 */
public class MyTask extends AsyncTask <Void, Integer, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressBar.setMask(100);
        //progressBar.setProgress(0);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int total=100;
        int i=0;
        while(i<total){
            try{
                Thread.sleep(1000);

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}
