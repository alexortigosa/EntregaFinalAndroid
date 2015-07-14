package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CalcActivity extends BaseActivity implements View.OnClickListener {
    String keyPanleUp="pnelSuperior";
    String keyPanel="panel";
    String keySPanel="sPanel";
    String keyOldVal="oldValDouble";
    String keyOpBool="opBoolean";
    String keyOperacion="operacion";
    String ketLastResul="lastResult";
    String keyCountOp="cuentaOp";
    String ketStateNot="stateNot";

    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button bmul;
    Button bdiv;
    Button bsub;
    Button badd;
    Button bClear;
    Button bResult;
    TextView panel;
    TextView upPanel;
    String sPanel;
    double oldVal;
    Boolean op;
    String operacion;
    int lastResult;
    int countOp;
    int stateNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        b0 = (Button) findViewById(R.id.button0);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        bmul=(Button) findViewById(R.id.buttonMul);
        bdiv=(Button) findViewById(R.id.buttonDiv);
        bsub=(Button) findViewById(R.id.buttonSub);
        badd=(Button) findViewById(R.id.buttonSum);
        bClear= (Button) findViewById(R.id.buttonClear);
        bResult=(Button) findViewById(R.id.buttonDot);
        panel=(TextView) findViewById(R.id.textView7);
        upPanel=(TextView) findViewById(R.id.textView4);
        sPanel="0";
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bmul.setOnClickListener(this);
        bdiv.setOnClickListener(this);
        bsub.setOnClickListener(this);
        badd.setOnClickListener(this);
        bClear.setOnClickListener(this);
        bResult.setOnClickListener(this);
        panel.setText(sPanel);
        upPanel.setText("");
        op=false;
        oldVal=0;
        operacion="";
        countOp=0;
        stateNot=getNotiMode();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(keyPanleUp,upPanel.getText()+"");

        outState.putString(keyPanel, panel.getText() + "");

        outState.putString(keySPanel, sPanel);

        outState.putDouble(keyOldVal, oldVal);

        outState.putBoolean(keyOpBool, op);

        outState.putString(keyOperacion,operacion);

        outState.putInt(ketLastResul,lastResult);

        outState.putInt(keyCountOp,countOp);

        outState.putInt(ketStateNot,stateNot);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        upPanel.setText(savedInstanceState.getString(keyPanleUp));
        panel.setText(savedInstanceState.getString(keyPanel));
        sPanel=savedInstanceState.getString(keySPanel);
        oldVal=savedInstanceState.getDouble(keyOldVal);
        op=savedInstanceState.getBoolean(keyOpBool);
        operacion=savedInstanceState.getString(keyOperacion);
        lastResult=savedInstanceState.getInt(ketLastResul);
        countOp=savedInstanceState.getInt(keyCountOp);
        stateNot=savedInstanceState.getInt(ketStateNot);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        switch (id){
            case R.id.action_toast:
                setNotificationMode(0);
                break;
            case R.id.action_estado:
                setNotificationMode(1);
                break;
            case R.id.action_llamar:
                llamar();
                break;
            case R.id.action_navegador:
                navegador();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNotificationMode(int i){
        setNotiMode(i);
        stateNot=i;
    }

    private void llamar(){
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+panel.getText()));
        startActivity(phoneIntent);
    }
    private void navegador(){
        String url = "http://www.alexortigosa.es";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    private void updatePanel(){
        int b;
        oldVal= Long.parseLong(sPanel);
        if(op){
            if(countOp>0) launchToast("No se pueden acumular operadores");
            else {
                upPanel.setText(panel.getText());
                sPanel = "0";
                op = false;
                oldVal = 0;
                countOp++;
            }

        }
        panel.setText(sPanel);
    }

    private void calcular(){
        if(op) {
            launchToast("Escriba otro valor para obtener el resultado");
        }
        else{
            switch (operacion){
                case "div":
                    panel.setText(Double.toString(Double.parseDouble((String)upPanel.getText())/Double.parseDouble((String)panel.getText())));
                    break;
                case "add":
                    panel.setText(Double.toString(Double.parseDouble((String)upPanel.getText())+Double.parseDouble((String)panel.getText())));
                    break;
                case "sub":
                    panel.setText(Double.toString(Double.parseDouble((String)upPanel.getText())-Double.parseDouble((String)panel.getText())));
                    break;
                case "mul":
                    panel.setText(Double.toString(Double.parseDouble((String)upPanel.getText())*Double.parseDouble((String)panel.getText())));
                    break;
                default:
                    break;
            }
            countOp=0;
        }
    }

    private void launchToast(String text){
        if(stateNot==0) {
            CharSequence textAux = text;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getApplicationContext(), textAux, duration);
            toast.show();
        }
        else if (stateNot==1){
            //Entero que nos permite identificar la notificación
            int mId = 1;
            //Instanciamos Notification Manager
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
            // un Builder/contructor.
            android.support.v4.app.NotificationCompat.Builder mBuilder =
                    new android.support.v4.app.NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Calculadora")
                            .setContentText(text);


            // Creamos un intent explicito, para abrir la app desde nuestra notificación
            Intent resultIntent = new Intent(getApplicationContext(), CalcActivity.class);

            //El objeto stack builder contiene una pila artificial para la Acitivty empezada.
            //De esta manera, aseguramos que al navegar hacia atrás
            //desde la Activity nos lleve a la home screen.

            //Desde donde la creamos
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            // Añade la pila para el Intent,pero no el intent en sí
            stackBuilder.addParentStack(CalcActivity.class);
            // Añadimos el intent que empieza la activity que está en el top de la pila
            stackBuilder.addNextIntent(resultIntent);

            //El pending intent será el que se ejecute cuando la notificación sea pulsada
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);

            // mId nos permite actualizar las notificaciones en un futuro
            // Notificamos
            mNotificationManager.notify(mId, mBuilder.build());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button0:

                if (oldVal==0 || op){
                    sPanel="0";
                    op=false;

                }
                else{
                    sPanel+= "0";

                }
                updatePanel();
                break;
            case R.id.button1:
                if (oldVal==0 || op){
                    sPanel="1";
                    op=false;
                }
                else {
                    sPanel += "1";

                }
                updatePanel();
                break;
            case R.id.button2:
                if (oldVal==0 || op){
                    sPanel="2";
                    op=false;
                }
                else{
                    sPanel+= "2";

                }
                updatePanel();
                break;
            case R.id.button3:
                if (oldVal==0 || op){
                    sPanel="3";
                    op=false;
                }
                else {
                    sPanel+= "3";

                }
                updatePanel();
                break;
            case R.id.button4:
                if (oldVal==0 || op){
                    sPanel="4";
                    op=false;
                }
                else {
                    sPanel+= "4";

                }
                updatePanel();
                break;
            case R.id.button5:
                if (oldVal==0 || op){
                    sPanel="5";
                    op=false;
                }
                else {
                    sPanel+= "5";

                }
                updatePanel();
                break;
            case R.id.button6:
                if (oldVal==0 || op){
                    sPanel="6";
                    op=false;
                }
                else {
                    sPanel+= "6";

                }
                updatePanel();
                break;
            case R.id.button7:
                if (oldVal==0 || op){
                    sPanel="7";
                    op=false;
                }
                else {
                    sPanel+= "7";

                }
                updatePanel();
                break;
            case R.id.button8:
                if (oldVal==0 || op){
                    sPanel="8";
                    op=false;
                }
                else sPanel+= "8";
                updatePanel();
                break;
            case R.id.button9:
                if (oldVal==0 || op){
                    sPanel="9";
                    op=false;
                }
                else {
                    sPanel+= "9";

                }
                updatePanel();
                break;
            case R.id.buttonClear:
                sPanel="0";
                op=false;
                oldVal=0;
                operacion="";
                updatePanel();
                break;
            case R.id.buttonDiv:
                if(op){
                    launchToast("No puedes acumular operaciones");
                }
                else {
                    op = true;
                    operacion = "div";
                    updatePanel();
                }
                break;
            case R.id.buttonMul:
                if(op){
                    launchToast("No puedes acumular operaciones");
                }
                else {
                    op = true;
                    operacion = "mul";
                    updatePanel();
                }
                break;
            case R.id.buttonDot://Resultado
                calcular();
                break;
            case R.id.buttonSub:
                if(op){
                    launchToast("No puedes acumular operaciones");
                }
                else {
                    op = true;
                    operacion = "sub";
                    updatePanel();
                }
                break;
            case R.id.buttonSum:
                if(op){
                    launchToast("No puedes acumular operaciones");
                }
                else {
                    op = true;
                    operacion = "add";
                    updatePanel();
                }
                break;

        }
    }
}
