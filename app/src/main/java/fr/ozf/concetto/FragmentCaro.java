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

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentCaro extends Fragment {

    public FragmentCaro() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentCaro newInstance() {
        FragmentCaro fragment = new FragmentCaro();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caro, container, false);
    }
}