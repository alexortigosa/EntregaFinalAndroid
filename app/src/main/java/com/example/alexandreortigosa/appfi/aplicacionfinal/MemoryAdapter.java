package com.example.alexandreortigosa.appfi.aplicacionfinal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alexandreortigosa on 7/7/15.
 */
public class MemoryAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Memory", "Ranking" };
    private Context context;
    Memory memory;
    RankingMem ranking;
    Fragment tab = null;

    //creadora
    public MemoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
            memory=new Memory();
            ranking=new RankingMem();
    }


    //crea las tabas, siempre tiene que retornar con el numero de tabs que queremos mostrar
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //Lanza el fragment asociado con el numero de tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                tab = memory;
                break;
            case 1:
                tab = ranking;
                break;
        }
        return tab;
    }

    //pone el nombre en cada tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public void reiniciarMemory() {
        // Generate title based on item position
        memory.reiniciar();
    }
}
