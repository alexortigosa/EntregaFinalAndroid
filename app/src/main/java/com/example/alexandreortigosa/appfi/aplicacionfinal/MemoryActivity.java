package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MemoryActivity extends ActionBarActivity implements RankingMem.OnFragmentInteractionListener,Memory.OnFragmentInteractionListener{
    private MemoryAdapter mAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.action_nuevojuego:
                nuevoJuego();
            case R.id.action_resetranking:
                resetRank();

            default:
                return true;

        }

       //return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        mAdapter=new MemoryAdapter(getSupportFragmentManager(),
                MemoryActivity.this);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(mAdapter);

        // Give the TabLayout the ViewPager (material)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabTextColors(Color.DKGRAY, Color.BLACK);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void nuevoJuego(){
        mAdapter.reiniciarMemory();
    }

    private void resetRank(){
        mAdapter.resetRank();
    }

    public void updateRank(){
        mAdapter.updateRank();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteractionMem(Uri uri) {

    }

    @Override
    public void onFragmentInteractionFinished() {
        updateRank();
    }
}
