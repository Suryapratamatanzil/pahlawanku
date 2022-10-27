package com.mdp.pahlawanku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvpahlawan;
    private ArrayList<ModelPahlawan> data = new ArrayList<>();
    private int mView = 0; //0 = modecard, 1 = mode grid
    static final String STATE_MODE = "MODE_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvpahlawan = findViewById(R.id.rv_pahlawan);
        rvpahlawan.setHasFixedSize(true);

        data.addAll(DataPahlawan.ambildata());
        if(savedInstanceState != null)
        {
            mView = savedInstanceState.getInt(STATE_MODE);
            if(mView==0)
            {
                //card
                tampilcard();;
            }
            else
            {
                //grid
                tampilgrid();
            }
        }
        else
        {
            tampilcard();
        }
    }
    private void tampilcard(){
        mView = 0;
        rvpahlawan.setLayoutManager(new LinearLayoutManager(this));
        AdapterCard adapter = new AdapterCard(data);
        rvpahlawan.setAdapter(adapter);
    }
    private void tampilgrid(){
        mView = 1;
        rvpahlawan.setLayoutManager(new GridLayoutManager(this, 6));
        adaptergrid adapter = new adaptergrid(data);
        rvpahlawan.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tampilan,menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES)
        {
            menu.findItem(R.id.menu_night).setTitle("Mode Day");
        }
        else
        {
            menu.findItem(R.id.menu_night).setTitle("Mode N");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_card:
                setTitle("Mode Card");
                tampilcard();
                break;
            case R.id.menu_grid:
                setTitle("Mode Grid");
                tampilgrid();
                break;
            case R.id.menu_night:
                setTitle("Mode Malam");
                int nightmode = AppCompatDelegate.getDefaultNightMode();
                if(nightmode == AppCompatDelegate.MODE_NIGHT_YES)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                break;
                case R.id.menu_help:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel: 1500008"));
                            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_MODE, mView);
        super.onSaveInstanceState(outState);

    }
}