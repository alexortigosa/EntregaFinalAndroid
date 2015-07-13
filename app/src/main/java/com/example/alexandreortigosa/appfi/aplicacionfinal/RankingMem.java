package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RankingMem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RankingMem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingMem extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CustomSqlLite usdbh;
    private SQLiteDatabase db;
    private View myFragmentView;
    private List<Puntuacion> puntuaciones;
    TableLayout tabla;
    TableLayout cabecera;
    LinearLayout tabCust;
    LinearLayout.LayoutParams layoutFila;
    LinearLayout.LayoutParams layoutId;
    LinearLayout.LayoutParams layoutTexto;
    private int MAX_FILAS = 10;
    ListView listView ;
    CustomAdapter cadapter;

    Resources rs;

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
     * @return A new instance of fragment RankingMem.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingMem newInstance(String param1, String param2) {
        RankingMem fragment = new RankingMem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RankingMem() {
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
        myFragmentView=inflater.inflate(R.layout.fragment_ranking_mem, container, false);
        usdbh = new CustomSqlLite(getActivity().getApplicationContext(), "DBUsuarios", null, 2);
        puntuaciones = new ArrayList<Puntuacion>();
        updateRanking();
        rs = getActivity().getApplicationContext().getResources();
        //tabla = (TableLayout)myFragmentView.findViewById(R.id.tabla);
        //cabecera = (TableLayout)myFragmentView.findViewById(R.id.cabecera);
        layoutFila = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutId = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutTexto = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tabCust= (LinearLayout) myFragmentView.findViewById(R.id.CustomTable);
        cadapter = new CustomAdapter();
        //agregarCabecera();
        //agregarFilasTabla();
        //agregarFilasCustom();
        listView = (ListView) myFragmentView.findViewById(R.id.listView);
        listView.setAdapter(cadapter);
        return myFragmentView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    public void updateRanking(){
        db = usdbh.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM Ranking ORDER BY intentos", null);
        //Nos aseguramos de qu
        // e existe al menos un registro
        if (c.moveToFirst()){
            puntuaciones.add(new Puntuacion(c.getString(0),c.getInt(1)));
            while (c.moveToNext()){
                puntuaciones.add(new Puntuacion(c.getString(0),c.getInt(1)));
            }

        }

    }

    public void updateAdapterRank() {
        // Generate title based on item position
        updateRanking();
        cadapter.refreshData();
        cadapter.notifyDataSetChanged();

    }


    public List<Puntuacion> getPuntuaciones(){
        return puntuaciones;
    }

    public void clearRanking(){
        puntuaciones.clear();
        cadapter.cleanData();
        cadapter.notifyDataSetChanged();
        db = usdbh.getWritableDatabase();
        db.execSQL(" DELETE FROM Ranking");


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
        public void onFragmentInteraction(Uri uri);
    }

    public class Puntuacion{
        private String nombre;
        private int intentos;

        public Puntuacion(String name, int inten){
            this.nombre=name;
            this.intentos=inten;
        }

        public String getNombre(){
            return this.nombre;
        }
        public int getIntentos(){
            return this.intentos;
        }
    }

    public class CustomAdapter extends BaseAdapter {
        List<Puntuacion> punts = getPuntuaciones();
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return punts.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return punts.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            if(arg1==null)
            {
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.listitem, arg2,false);
            }

            TextView rankingName = (TextView)arg1.findViewById(R.id.rankingName);
            //TextView rankingInt = (TextView)arg1.findViewById(R.id.rankingIntentos);

            Puntuacion punt = punts.get(arg0);

            rankingName.setText(punt.getNombre()+"..............................."+punt.getIntentos());
            //rankingInt.setText(punt.getIntentos()+"");

            return arg1;
        }

        public void cleanData(){
            punts.clear();
        }
        public void refreshData(){
            punts= getPuntuaciones();
        }

    }

}
