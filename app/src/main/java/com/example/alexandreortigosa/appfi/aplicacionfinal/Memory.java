package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private View myFragmentView;
    private char[][] gameTableClick;
    private int[][] gameTableDist;
    private int[] prevClick;
    private int countIntent;


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
        gameTableClick = new char[3][4];
        gameTableDist = new int[3][4];
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
                CheckClick(0, 0, R.id.button14);
               // new MyTask().execute(R.id.button14);
                break;
            case R.id.button15:
                CheckClick(1, 0, R.id.button15);
                //new MyTask().execute(R.id.button15);
                break;
            case R.id.button16:
                CheckClick(2, 0, R.id.button16);
                //new MyTask().execute(R.id.button16);
                break;
            case R.id.button17:
                CheckClick(0, 1, R.id.button17);
                //new MyTask().execute(R.id.button17);
                break;
            case R.id.button18:
                CheckClick(1, 1, R.id.button18);
                //new MyTask().execute(R.id.button18);
                break;
            case R.id.button19:
                CheckClick(2, 1, R.id.button19);
                //new MyTask().execute(R.id.button19);
                break;
            case R.id.button20:
                CheckClick(0, 2, R.id.button20);
                //new MyTask().execute(R.id.button20);
                break;
            case R.id.button21:
                CheckClick(1, 2, R.id.button21);
                //new MyTask().execute(R.id.button21);
                break;
            case R.id.button22:
                CheckClick(2, 2, R.id.button22);
                //new MyTask().execute(R.id.button22);
                break;
            case R.id.button23:
                CheckClick(0, 3, R.id.button23);
                //new MyTask().execute(R.id.button23);
                break;
            case R.id.button24:
                CheckClick(1, 3, R.id.button24);
                //new MyTask().execute(R.id.button24);
                break;
            case R.id.button25:
                CheckClick(2, 3, R.id.button25);
                //new MyTask().execute(R.id.button25);
                break;

            default:
                break;
        }

    }

    private void generateRandomTable(){
        int[] cuentas = new int[7];
        for (int i=0;i<cuentas.length;i++){
            cuentas[i]=0;
        }

        for (int i=0;i<gameTableDist.length;i++){
            for (int a=0;a<gameTableDist[i].length;a++){
                int aux=randInt(1, 6);
                while (gameTableDist[i][a]==0) {
                    if(cuentas[aux]<2) {
                        gameTableDist[i][a] = aux;
                        cuentas[aux]++;
                    }
                    aux=randInt(1, 6);

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



    }

    private void CheckClick(int x, int y, int boton){
        if(prevClick[0]!=-1 && countIntent==2) {
            if (gameTableDist[prevClick[0]][prevClick[1]] == gameTableDist[x][y] && !getSameClick(x, y)) {
                gameTableClick[prevClick[0]][prevClick[1]] = 'S';
                gameTableClick[x][y] = 'S';
            }
            countIntent=0;
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
        prevClick[0]=x;
        prevClick[1]=y;
        new MyTask(boton).execute(boton);

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


    public class MyTask extends AsyncTask<Integer, Integer, Integer> {

        private int but;

        public MyTask(int b){
            this.but=b;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
                //Button baux =(Button) myFragmentView.findViewById(R.id.button14);
            try{
                sleep(1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

            return params[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            switch (but){
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
                default:
                    break;

            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);

            switch (aVoid){
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
                default:
                    break;

            }


        }


    }

}
