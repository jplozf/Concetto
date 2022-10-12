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
                        FragmentMelo nextFrag = FragmentMelo.newInstance(4);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("5 ")) {
                        FragmentMelo nextFrag = FragmentMelo.newInstance(5);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("6 ")) {
                        FragmentMelo nextFrag = FragmentMelo.newInstance(6);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("7 ")) {
                        FragmentMelo nextFrag = FragmentMelo.newInstance(7);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    } else if (item.toString().startsWith("Car")) {
                        FragmentCaro nextFrag = FragmentCaro.newInstance();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_game_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                    /*
                    Caro        : Grille 4 x 4, trouver tous les mots possibles
                    Figaro      : 7 lettres, trouver tous les mots possibles
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