package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutionException;


public class LoggActivity extends BaseActivity {

    private CustomSqlLite usdbh;
    private SQLiteDatabase db;
    private EditText eUser;
    private EditText ePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Loggin", "Punto 1");
        setContentView(R.layout.activity_logg);
        eUser = (EditText) findViewById(R.id.editTextUser);
        ePassword = (EditText) findViewById(R.id.editTextPassword);
        usdbh = new CustomSqlLite(getApplicationContext(), this.dbName, null, 1);
        if(isLogged()){
            goSucces();
        }
        Log.v("Loggin", "Punto 2");
        /*db = usdbh.getWritableDatabase();
        Log.v("Loggin", "Punto 3");

        //Si hemos abierto correctamente la base de datos
        if(db != null) {
            //Insertamos 5 usuarios de ejemplo
            for (int i = 1; i <= 5; i++) {
                //Generamos los datos
                int codigo = i;
                String nombre = "Alex" + i;
                String password = Integer.toString(i);

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre, password) " +
                        "VALUES (" + codigo + ", '" + nombre + "','" + password + "')");
            }

            //Cerramos la base de datos
            db.close();
        }
        Log.v("Loggin", "Punto 4");*/
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
        intent.putExtra(intentUserString, eUser.getText());
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
