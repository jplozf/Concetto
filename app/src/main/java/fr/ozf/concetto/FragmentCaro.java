package fr.ozf.concetto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCaro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCaro extends Fragment {

    public FragmentCaro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentCarreau.
     */
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