package fr.ozf.concetto;

//**************************************************************************************************
//                                                         ______                      __  __
//                                                        / ____/___  ____  ________  / /_/ /_____
//                                                       / /   / __ \/ __ \/ ___/ _ \/ __/ __/ __ \
//                                                      / /___/ /_/ / / / / /__/  __/ /_/ /_/ /_/ /
//                                                      \____/\____/_/ /_/\___/\___/\__/\__/\____/
//
//                                                                      (C) JPL 1964
//
//**************************************************************************************************

import static android.app.PendingIntent.getActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public ODSLib odsLib;
    private TabHost myTabHost;
    private int prefOnlineDico;
    private int currentTab;
    private int currentGame;
    private ViewPager pager;
    boolean doubleBackToExitPressedOnce = false;

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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int prefODS = Integer.parseInt(prefs.getString("prefODS", "8"));
        prefOnlineDico = Integer.parseInt(prefs.getString("prefOnlineDico", "0"));
        odsLib = new ODSLib(this, prefODS);
    }

    //**********************************************************************************************
    // configureViewPager()
    //**********************************************************************************************
    private void configureViewPager() {
        pager = (ViewPager)findViewById(R.id.view_pager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        setupTabIcons(tabs);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentTab = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // don't care
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // don't care
            }
        });
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

    @Override
    //**********************************************************************************************
    // onSaveInstanceState()
    //**********************************************************************************************
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("currentTab", currentTab);
    }

    @Override
    //**********************************************************************************************
    // onRestoreInstanceState()
    //**********************************************************************************************
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        currentTab = savedInstanceState.getInt("currentTab");
        pager.setCurrentItem(currentTab);
    }

    @Override
    //**********************************************************************************************
    // onBackPressed()
    //**********************************************************************************************
    public void onBackPressed()
    {
        if (doubleBackToExitPressedOnce)
        {
            this.finish();
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.back_again_to_exit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}