package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivity extends BaseActivity implements View.OnClickListener {

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
    String sPanel;
    int oldVal;
    Boolean op;
    String operacion;

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
        panel=(TextView) findViewById(R.id.textView7);
        panel.setText('0');
        op=false;
        oldVal=0;
        operacion="";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
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


    private void updatePanel(){
        int b;
        panel.setText(sPanel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button0:

                if (oldVal==0 || op){
                    sPanel="0";
                    op=false;

                }
                else sPanel+= "0";
                updatePanel();
                break;
            case R.id.button1:
                if (oldVal==0 || op){
                    sPanel="1";
                    op=false;
                }
                else sPanel+= "1";
                updatePanel();
                break;
            case R.id.button2:
                if (oldVal==0 || op){
                    sPanel="2";
                    op=false;
                }
                else sPanel+= "2";
                updatePanel();
                break;
            case R.id.button3:
                if (oldVal==0 || op){
                    sPanel="3";
                    op=false;
                }
                else sPanel+= "3";
                updatePanel();
                break;
            case R.id.button4:
                if (oldVal==0 || op){
                    sPanel="4";
                    op=false;
                }
                else sPanel+= "4";
                updatePanel();
                break;
            case R.id.button5:
                if (oldVal==0 || op){
                    sPanel="5";
                    op=false;
                }
                else sPanel+= "5";
                updatePanel();
                break;
            case R.id.button6:
                if (oldVal==0 || op){
                    sPanel="6";
                    op=false;
                }
                else sPanel+= "6";
                updatePanel();
                break;
            case R.id.button7:
                if (oldVal==0 || op){
                    sPanel="7";
                    op=false;
                }
                else sPanel+= "7";
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
                else sPanel+= "9";
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
                oldVal=Integer.parseInt(sPanel);
                op=true;
                operacion="div";
                break;
            case R.id.buttonMul:
                oldVal=Integer.parseInt(sPanel);
                op=true;
                operacion="mul";
                break;
            case R.id.buttonDot://Resultado
                break;
            case R.id.buttonSub:
                oldVal=Integer.parseInt(sPanel);
                op=true;
                operacion="sub";
                break;
            case R.id.buttonSum:
                oldVal=Integer.parseInt(sPanel);
                op=true;
                operacion="add";
                break;

        }
    }
}
