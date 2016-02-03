package com.example.pablo.proyecto_registrollamadas;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pablo.proyecto_registrollamadas.Tabs.Tab1;
import com.example.pablo.proyecto_registrollamadas.Tabs.Tab2;
import com.example.pablo.proyecto_registrollamadas.Tabs.Tab3;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost tabHost;

    //Main contiene el tabhost de las tabs, las cuales son realmente las clases principales
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getString(R.string.entrantes)),
                Tab1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getString(R.string.salientes)),
                Tab2.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getString(R.string.perdidas)),
                Tab3.class, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
