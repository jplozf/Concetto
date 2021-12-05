package fr.ozf.concetto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public ODSLib odsLib;
    private TabHost myTabHost;
    private SharedPreferences prefs;
    private int prefODS;
    private int prefOnlineDico;

    @Override
    //**********************************************************************************************
    // onCreate()
    //**********************************************************************************************
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureViewPager();
        initApp();
    }

    //**********************************************************************************************
    // initApp()
    //**********************************************************************************************
    private void initApp() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefODS = Integer.parseInt(prefs.getString("prefODS", "8"));
        prefOnlineDico = Integer.parseInt(prefs.getString("prefOnlineDico", "0"));
        odsLib = new ODSLib(this, prefODS);
    }

    //**********************************************************************************************
    // configureViewPager()
    //**********************************************************************************************
    private void configureViewPager() {
        ViewPager pager = (ViewPager)findViewById(R.id.view_pager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        setupTabIcons(tabs);
    }

    //**********************************************************************************************
    // setupTabIcons()
    //**********************************************************************************************
    private void setupTabIcons(TabLayout tabs) {
        tabs.getTabAt(0).setIcon(R.drawable.ic_baseline_language_24);
        tabs.getTabAt(1).setIcon(R.drawable.ic_baseline_check_circle_outline_24);
        tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_av_timer_24);
        tabs.getTabAt(3).setIcon(R.drawable.ic_baseline_copyright_24);
        tabs.getTabAt(4).setIcon(R.drawable.ic_baseline_settings_24);
    }
}