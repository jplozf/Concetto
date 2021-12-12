package fr.ozf.concetto;

//**************************************************************************************************
//                                                            ___                     _   _
//                                                           / __\___  _ __   ___ ___| |_| |_ ___
//                                                          / /  / _ \| '_ \ / __/ _ \ __| __/ _ \
//                                                         / /__| (_) | | | | (_|  __/ |_| || (_) |
//                                                         \____/\___/|_| |_|\___\___|\__|\__\___/
//
//                                                                      (C) JPL 1964
//
//**************************************************************************************************

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentGame extends Fragment {

    public FragmentGame() {
        // Required empty public constructor
    }

    public static FragmentGame newInstance() {
        return (new FragmentGame());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_game, container, false);
        // get Spinner reference
        Spinner spnGames = (Spinner) vw.findViewById(R.id.spnLevel);
        spnGames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
                    if (item.toString().startsWith("4 ")) {
                        FragmentAnagrams nextFrag = FragmentAnagrams.newInstance(4);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("5 ")) {
                        FragmentAnagrams nextFrag = FragmentAnagrams.newInstance(5);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("6 ")) {
                        FragmentAnagrams nextFrag = FragmentAnagrams.newInstance(6);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("7 ")) {
                        FragmentAnagrams nextFrag = FragmentAnagrams.newInstance(7);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                    /*
                    Carreau     : Grille 4 x 4, trouver tous les mots possibles
                    Figaro      : 7 lettres, trouver tous les mots possible
                    Duetto      : 8 lettres, trouver le mot le plus long
                    Nabucco     : A partir d'un mot de 2 lettres, rajouter une lettre pour créer un nouveau mot et ainsi de suite
                    Rampono     : Jeu du pendu
                     */
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
        return vw;
    }
}