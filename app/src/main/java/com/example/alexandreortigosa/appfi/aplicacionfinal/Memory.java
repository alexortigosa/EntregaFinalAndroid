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
        switch (v.getId()){
            case R.id.button14:
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                break;
            case R.id.button1:

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

        switch (gameTableDist[0][0]){
            case 1:
                //b0.setBackgroundResource();

        }
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


    public class MyTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

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


        }


    }

}
