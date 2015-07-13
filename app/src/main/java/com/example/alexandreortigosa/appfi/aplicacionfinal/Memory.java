package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Thread.sleep;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Memory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Memory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Memory extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b13;
    private Button b14;
    private Button b15;
    private Button buttonPrev;
    private Button buttonPres;
    private View myFragmentView;
    private char[][] gameTableClick;
    private int[][] gameTableDist;
    private int[] prevClick;
    private int countIntent;
    private int globalIntents;
    private TextView intentos;
    private CustomSqlLite usdbh;
    private SQLiteDatabase db;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Memory.
     */
    // TODO: Rename and change types and number of parameters
    public static Memory newInstance(String param1, String param2) {
        Memory fragment = new Memory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Memory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myFragmentView=inflater.inflate(R.layout.fragment_memory, container, false);
        usdbh = new CustomSqlLite(getActivity().getApplicationContext(), "DBUsuarios", null, 2);
        b0=(Button) myFragmentView.findViewById(R.id.button14);
        b0.setOnClickListener(this);

        b1=(Button) myFragmentView.findViewById(R.id.button15);
        b1.setOnClickListener(this);

        b2=(Button) myFragmentView.findViewById(R.id.button16);
        b2.setOnClickListener(this);

        b3=(Button) myFragmentView.findViewById(R.id.button17);
        b3.setOnClickListener(this);

        b4=(Button) myFragmentView.findViewById(R.id.button18);
        b4.setOnClickListener(this);

        b5=(Button) myFragmentView.findViewById(R.id.button19);
        b5.setOnClickListener(this);

        b6=(Button) myFragmentView.findViewById(R.id.button20);
        b6.setOnClickListener(this);

        b7=(Button) myFragmentView.findViewById(R.id.button21);
        b7.setOnClickListener(this);

        b8=(Button) myFragmentView.findViewById(R.id.button22);
        b8.setOnClickListener(this);

        b9=(Button) myFragmentView.findViewById(R.id.button23);
        b9.setOnClickListener(this);

        b10=(Button) myFragmentView.findViewById(R.id.button24);
        b10.setOnClickListener(this);

        b11=(Button) myFragmentView.findViewById(R.id.button25);
        b11.setOnClickListener(this);

        b12=(Button) myFragmentView.findViewById(R.id.button26);
        b12.setOnClickListener(this);

        b13=(Button) myFragmentView.findViewById(R.id.button27);
        b13.setOnClickListener(this);

        b14=(Button) myFragmentView.findViewById(R.id.button28);
        b14.setOnClickListener(this);

        b15=(Button) myFragmentView.findViewById(R.id.button29);
        b15.setOnClickListener(this);
        intentos = (TextView) myFragmentView.findViewById((R.id.textView6));
        globalIntents=0;
        gameTableClick = new char[4][4];
        gameTableDist = new int[4][4];
        prevClick = new int[2];
        prevClick[0]=-1;
        prevClick[1]=-1;
        countIntent=0;
        generateRandomTable();
        initClickTable();
        initImages();
        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionMem(uri);
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onClick(View v) {

        countIntent++;
        switch (v.getId()){
            case R.id.button14:
                CheckClick(0, 0, b0);
               // new MyTask().execute(R.id.button14);
                break;
            case R.id.button15:
                CheckClick(1, 0, b1);
                //new MyTask().execute(R.id.button15);
                break;
            case R.id.button16:
                CheckClick(2, 0,b2);
                //new MyTask().execute(R.id.button16);
                break;
            case R.id.button17:
                CheckClick(0, 1, b3);
                //new MyTask().execute(R.id.button17);
                break;
            case R.id.button18:
                CheckClick(1, 1, b4);
                //new MyTask().execute(R.id.button18);
                break;
            case R.id.button19:
                CheckClick(2, 1, b5);
                //new MyTask().execute(R.id.button19);
                break;
            case R.id.button20:
                CheckClick(0, 2, b6);
                //new MyTask().execute(R.id.button20);
                break;
            case R.id.button21:
                CheckClick(1, 2, b7);
                //new MyTask().execute(R.id.button21);
                break;
            case R.id.button22:
                CheckClick(2, 2, b8);
                //new MyTask().execute(R.id.button22);
                break;
            case R.id.button23:
                CheckClick(0, 3, b9);
                //new MyTask().execute(R.id.button23);
                break;
            case R.id.button24:
                CheckClick(1, 3, b10);
                //new MyTask().execute(R.id.button24);
                break;
            case R.id.button25:
                CheckClick(2, 3, b11);
                //new MyTask().execute(R.id.button25);
                break;
            case R.id.button26:
                CheckClick(3, 0, b12);
                //new MyTask().execute(R.id.button25);
                break;
            case R.id.button27:
                CheckClick(3, 1, b13);
                //new MyTask().execute(R.id.button25);
                break;
            case R.id.button28:
                CheckClick(3, 2, b14);
                //new MyTask().execute(R.id.button25);
                break;
            case R.id.button29:
                CheckClick(3, 3, b15);
                //new MyTask().execute(R.id.button25);
                break;

            default:
                break;
        }

    }
    private void generateRandomTable(){
        int[] cuentas = new int[9];
        for (int i=0;i<cuentas.length;i++){
            cuentas[i]=0;
        }

        for (int i=0;i<gameTableDist.length;i++){
            for (int a=0;a<gameTableDist[i].length;a++){
                int aux=randInt(1, 8);
                while (gameTableDist[i][a]==0) {
                    if(cuentas[aux]<2) {
                        gameTableDist[i][a] = aux;
                        cuentas[aux]++;
                    }
                    aux=randInt(1, 8);

                }
            }
        }
    }
    private void initClickTable(){

        for (int i=0;i<gameTableClick.length;i++){
            for (int a=0;a<gameTableClick[i].length;a++){
                gameTableClick[i][a]= 'N';
            }
        }
    }
    private void initImages(){


                b0.setBackgroundResource(R.drawable.interrogantesmall);


                b3.setBackgroundResource(R.drawable.interrogantesmall);

                b6.setBackgroundResource(R.drawable.interrogantesmall);

                b9.setBackgroundResource(R.drawable.interrogantesmall);

                b1.setBackgroundResource(R.drawable.interrogantesmall);

                b4.setBackgroundResource(R.drawable.interrogantesmall);

                b7.setBackgroundResource(R.drawable.interrogantesmall);

                b10.setBackgroundResource(R.drawable.interrogantesmall);

                b2.setBackgroundResource(R.drawable.interrogantesmall);

                b5.setBackgroundResource(R.drawable.interrogantesmall);

                b8.setBackgroundResource(R.drawable.interrogantesmall);

                b11.setBackgroundResource(R.drawable.interrogantesmall);
                b12.setBackgroundResource(R.drawable.interrogantesmall);
                b13.setBackgroundResource(R.drawable.interrogantesmall);
                b14.setBackgroundResource(R.drawable.interrogantesmall);
                b15.setBackgroundResource(R.drawable.interrogantesmall);



    }
    private void CheckClick(int x, int y, Button boton){

        switch (boton.getId()){
            case R.id.button14:
                b0.setBackgroundResource(getDrawable(0,0));

                break;
            case R.id.button15:
                b1.setBackgroundResource(getDrawable(1,0));
                break;
            case R.id.button16:
                b2.setBackgroundResource(getDrawable(2,0));
                break;
            case R.id.button17:
                b3.setBackgroundResource(getDrawable(0,1));
                break;
            case R.id.button18:
                b4.setBackgroundResource(getDrawable(1,1));
                break;
            case R.id.button19:
                b5.setBackgroundResource(getDrawable(2,1));
                break;
            case R.id.button20:
                b6.setBackgroundResource(getDrawable(0,2));
                break;
            case R.id.button21:
                b7.setBackgroundResource(getDrawable(1,2));
                break;
            case R.id.button22:
                b8.setBackgroundResource(getDrawable(2,2));
                break;
            case R.id.button23:
                b9.setBackgroundResource(getDrawable(0,3));
                break;
            case R.id.button24:
                b10.setBackgroundResource(getDrawable(1,3));
                break;
            case R.id.button25:
                b11.setBackgroundResource(getDrawable(2,3));
                break;
            case R.id.button26:
                b12.setBackgroundResource(getDrawable(3,0));
                break;
            case R.id.button27:
                b13.setBackgroundResource(getDrawable(3,1));
                break;
            case R.id.button28:
                b14.setBackgroundResource(getDrawable(3,2));
                break;
            case R.id.button29:
                b15.setBackgroundResource(getDrawable(3,3));
                break;
            default:
                break;

        }
        //boton.setImage();
        if(prevClick[0]!=-1 && countIntent==2) {
            if (gameTableDist[prevClick[0]][prevClick[1]] == gameTableDist[x][y] && !getSameClick(x, y)) {
                gameTableClick[prevClick[0]][prevClick[1]] = 'S';
                gameTableClick[x][y] = 'S';
                CheckFinish();
            }
            else new MyTask(boton,buttonPrev).execute();

            countIntent=0;
            globalIntents++;
            intentos.setText(globalIntents+"");

        }


        switch (gameTableClick[x][y]) {
            case 'N':
                break;
            case 'S':
                launchToast("Imagen desbloqueada");
                break;
            default:
                break;
        }
        buttonPrev =boton;
        prevClick[0]=x;
        prevClick[1]=y;


    }
    private void CheckFinish(){
        int sum=0;
        for (int i=0;i<gameTableClick.length;i++){
            for (int a=0;a<gameTableClick[i].length;a++){
                if(gameTableClick[i][a]== 'N') sum++;
            }
        }
        if (sum==0) end();
    }
    private void end(){
        insertarPuntuacion();
        DialogFragment newDialog = new AlertaDialog();
        newDialog.show(getActivity().getSupportFragmentManager(), "Prueba");
    }
    private void insertarPuntuacion(){
        //Instanciamos el SharedPreferences
        SharedPreferences settings = getActivity().getSharedPreferences("myCustomApp", Context.MODE_PRIVATE);
        //Consultamos
        String nombre=settings.getString("user_loggin","notFound");
        db = usdbh.getWritableDatabase();
        if(db != null) {
                db.execSQL("INSERT INTO Ranking (nombre, intentos) " +
                        "VALUES ('" + nombre + "'," + globalIntents + ")");
            }

            //Cerramos la base de datos
            db.close();
    }
    public void reiniciar(){
        globalIntents=0;
        gameTableClick = new char[4][4];
        gameTableDist = new int[4][4];
        prevClick = new int[2];
        prevClick[0]=-1;
        prevClick[1]=-1;
        countIntent=0;
        intentos.setText("0");
        generateRandomTable();
        initClickTable();
        initImages();
    }
    private void launchToast(String text){
        CharSequence textAux = text;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getActivity().getApplicationContext(), textAux, duration);
        toast.show();
    }
    private int getDrawable(int x,int y){
        int ret;
        switch (gameTableDist[x][y]){
            case 1:
               ret=R.drawable.card1;
                break;
            case 2:
                ret=R.drawable.card2;
                break;
            case 3:
                ret=R.drawable.card3;
                break;
            case 4:
                ret=R.drawable.card4;
                break;
            case 5:
                ret=R.drawable.card5;
                break;
            case 6:
                ret=R.drawable.card6;
                break;
            case 7:
                ret=R.drawable.card7;
                break;
            case 8:
                ret=R.drawable.card8;
                break;
            default:
                ret=R.drawable.notfound;
                break;

        }

        return ret;
    }
    private boolean getSameClick(int x,int y) {
        if(prevClick[0]==x&&prevClick[1]==y) return true;
        return false;

    }
    private int randInt(int min, int max){
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteractionMem(Uri uri);

    }
    public class MyTask extends AsyncTask<Void, Integer, Void> {

        private Button but;
        private Button prevBut;

        public MyTask(Button act, Button prev){
            this.but=act;
            this.prevBut=prev;
        }

        @Override
        protected Void doInBackground(Void... params) {
                //Button baux =(Button) myFragmentView.findViewById(R.id.button14);
            try{
                sleep(2000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            switch (but.getId()){
                case R.id.button14:
                    if(gameTableClick[0][0]!='S')
                    b0.setBackgroundResource(R.drawable.interrogantesmall);

                    break;
                case R.id.button15:
                    if(gameTableClick[1][0]!='S')
                    b1.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button16:
                    if(gameTableClick[2][0]!='S')
                    b2.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button17:
                    if(gameTableClick[0][1]!='S')
                    b3.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button18:
                    if(gameTableClick[1][1]!='S')
                    b4.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button19:
                    if(gameTableClick[2][1]!='S')
                    b5.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button20:
                    if(gameTableClick[0][2]!='S')
                    b6.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button21:
                    if(gameTableClick[1][2]!='S')
                    b7.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button22:
                    if(gameTableClick[2][2]!='S')
                    b8.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button23:
                    if(gameTableClick[0][3]!='S')
                    b9.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button24:
                    if(gameTableClick[1][3]!='S')
                    b10.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button25:
                    if(gameTableClick[2][3]!='S')
                    b11.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button26:
                    if(gameTableClick[3][0]!='S')
                        b12.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button27:
                    if(gameTableClick[3][1]!='S')
                        b13.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button28:
                    if(gameTableClick[3][2]!='S')
                        b14.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button29:
                    if(gameTableClick[3][3]!='S')
                        b15.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                default:
                    break;

            }

            switch (prevBut.getId()){
                case R.id.button14:
                    if(gameTableClick[0][0]!='S')
                        b0.setBackgroundResource(R.drawable.interrogantesmall);

                    break;
                case R.id.button15:
                    if(gameTableClick[1][0]!='S')
                        b1.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button16:
                    if(gameTableClick[2][0]!='S')
                        b2.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button17:
                    if(gameTableClick[0][1]!='S')
                        b3.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button18:
                    if(gameTableClick[1][1]!='S')
                        b4.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button19:
                    if(gameTableClick[2][1]!='S')
                        b5.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button20:
                    if(gameTableClick[0][2]!='S')
                        b6.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button21:
                    if(gameTableClick[1][2]!='S')
                        b7.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button22:
                    if(gameTableClick[2][2]!='S')
                        b8.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button23:
                    if(gameTableClick[0][3]!='S')
                        b9.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button24:
                    if(gameTableClick[1][3]!='S')
                        b10.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button25:
                    if(gameTableClick[2][3]!='S')
                        b11.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button26:
                    if(gameTableClick[3][0]!='S')
                        b12.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button27:
                    if(gameTableClick[3][1]!='S')
                        b13.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button28:
                    if(gameTableClick[3][2]!='S')
                        b14.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                case R.id.button29:
                    if(gameTableClick[3][3]!='S')
                        b15.setBackgroundResource(R.drawable.interrogantesmall);
                    break;
                default:
                    break;

            }
            //this.prevBut.setImageInterrogante();
            //this.but.setImageInterrogante();


        }


    }
    public class CustomButton extends Button{
        private int x;
        private int y;


        public CustomButton(Context context) {
            super(context);
        }

        public CustomButton(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }



        public void setPosition(int auxX, int auxY){
            this.x=auxX;
            this.y=auxY;
        }

        public void setImage(){
            switch (gameTableDist[this.x][this.y]){
                case 1:
                    this.setBackgroundResource(R.drawable.card1);
                    break;
                case 2:
                    this.setBackgroundResource(R.drawable.card2);
                    break;
                case 3:
                    this.setBackgroundResource(R.drawable.card3);
                    break;
                case 4:
                    this.setBackgroundResource(R.drawable.card4);
                    break;
                case 5:
                    this.setBackgroundResource(R.drawable.card5);
                    break;
                case 6:
                    this.setBackgroundResource(R.drawable.card6);
                    break;
                default:
                    this.setBackgroundResource(R.drawable.notfound);
                    break;

            }
        }

        public void setImageInterrogante(){
            this.setBackgroundResource(R.drawable.interrogantesmall);
        }

    }
    public class AlertaDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Partida finalizada")
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    });
                    /*.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });*/
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }



}
