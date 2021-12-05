package fr.ozf.concetto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.preference.PreferenceFragmentCompat;


public class FragmentPreferences extends PreferenceFragmentCompat {
//***********************************************************************
// FragmentPreferences()
//***********************************************************************
    public FragmentPreferences() {
        // Required empty public constructor
    }

//***********************************************************************
// newInstance()
//***********************************************************************
    public static FragmentPreferences newInstance() {
        return (new FragmentPreferences());
    }

    @Override
//***********************************************************************
// onCreateView()
//***********************************************************************
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.Grullo));
        return view;
    }

    @Override
//***********************************************************************
// onCreatePreferences()
//***********************************************************************
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}