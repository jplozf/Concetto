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
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

//***********************************************************************
// Class FragmentMelo()
//***********************************************************************
public class FragmentMelo extends Fragment {
    private int anaLevel = 7;
    private int anaTime = 3;
    private int anaColumn = 0;
    private String anaWord;
    private String anaGuess;
    private int anaScore = 0;
    private ArrayList<String> anaWords;
    private String TAG = "MELO";
    ArrayList<ImageView> anaWordLetters = new ArrayList<>();
    ArrayList<ImageView> anaGuessLetters = new ArrayList<>();
    boolean timerRunning = false;
    boolean timeOver = false;

    public FragmentMelo() {
        // Required empty public constructor
    }

    //***********************************************************************
    // FragmentMelo()
    //***********************************************************************
    public static FragmentMelo newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt("level", level);
        FragmentMelo fragment = new FragmentMelo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    //***********************************************************************
    // onCreateView()
    //***********************************************************************
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_melo, container, false);
        anaLevel = getArguments().getInt("level");
        showAnagrams(vw);
        return vw;
    }

    //***********************************************************************
    // showAnagrams()
    //***********************************************************************
    private void showAnagrams(View vw) {
        ImageView btnAnaRefresh = (ImageView) vw.findViewById(R.id.btnAnaRefresh);
        // Hide the keyboard
        // ((InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
        //
        anaNewWord(vw);

        ImageView btnAnaClear = (ImageView) vw.findViewById(R.id.btnAnaClear);
        btnAnaClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
                rowWord.removeAllViews();
                // dummyRow(rowWord);
                //
                final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
                rowGuess.removeAllViews();
                // dummyRow(rowGuess);
                //
                anaColumn = 0;
                anaWordLetters.clear();
                anaGuessLetters.clear();
                Log.i(TAG, anaWord);
                for (int i = 0; i < anaLevel; i++) {
                    final ImageView letter = new ImageView(getContext());
                    ImageView guess = new ImageView(getContext());
                    final String l = anaWord.substring(i, i + 1);
                    Log.i(TAG, "LETTER : " + l);
                    letter.setBackgroundResource(ODSLib.getIconIDFromLetter(l));
                    letter.setEnabled(true);
                    guess.setBackgroundResource(ODSLib.getIconIDFromLetter("?", false));
                    anaWordLetters.add(letter);
                    rowWord.addView(letter);
                    rowGuess.addView(guess);
                    //
                    letter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            letter.setBackgroundResource(ODSLib.getIconIDFromLetter(l, false));
                            ImageView guess = (ImageView) rowGuess.getVirtualChildAt(anaColumn);
                            guess.setBackgroundResource(ODSLib.getIconIDFromLetter(l, true));
                            letter.setEnabled(false);
                            anaColumn++;
                        }
                    });
                }
            }
        });
    }

    //***********************************************************************
    // dummyRow()
    //***********************************************************************
    private void dummyRow(TableRow row) {
        int rowLength = row.getChildCount();
        row.removeAllViews();
        for (int i = 0; i < rowLength; i++) {
            ImageView guess = new ImageView(getContext());
            guess.setBackgroundResource(ODSLib.getIconIDFromLetter("?", false));
            row.addView(guess);
        }
    }

    //***********************************************************************
    // anaNewWord()
    //***********************************************************************
    private void anaNewWord(View vw) {
        ImageView btnAnaRefresh = (ImageView) vw.findViewById(R.id.btnAnaRefresh);
        ImageView btnAnaClear = (ImageView) vw.findViewById(R.id.btnAnaClear);
        // Hide the keyboard
        // ((InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
        //
        TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
        rowWord.removeAllViews();
        // dummyRow(rowWord);
        //
        final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
        // dummyRow(rowGuess);
        rowGuess.removeAllViews();
        //
        TextView txtAnaScore = (TextView) vw.findViewById(R.id.txtAnaScore);
        txtAnaScore.setText(Integer.toString(anaScore));
        //
        anaColumn = 0;
        anaWord = ((MainActivity) getActivity()).odsLib.shuffleWord(((MainActivity) getActivity()).odsLib.getRandomWord(anaLevel));
        anaWords = ((MainActivity) getActivity()).odsLib.findAnagrams(anaWord);
        anaGuess = "";
        Log.i(TAG, anaWord);

        btnAnaClear.setOnClickListener(new View.OnClickListener() {
            @Override
            //***********************************************************************
            // onClick()
            //***********************************************************************
            public void onClick(View v) {                                       // CLEAR Button
                // Toast.makeText(vw.getContext(), anaWord, Toast.LENGTH_SHORT).show();
                StringBuilder txt = new StringBuilder();
                for (int i = 0; i < anaWords.size(); i++) {
                    txt.append(anaWords.get(i)).append(" - ");
                }
                txt.setLength(txt.length() - 3);
                TextView txtSolution = vw.findViewById(R.id.txtSolution);
                txtSolution.setText(txt.toString());
                // Toast.makeText(vw.getContext(), txt, Toast.LENGTH_SHORT).show();
                anaNewWord(vw);
            }
        });

        btnAnaRefresh.setOnClickListener(new View.OnClickListener() {

            long MillisecondTime, TimeBuff, UpdateTime = 0L;
            int Seconds, Minutes, MilliSeconds;
            TextView textViewTimer = getActivity().findViewById(R.id.txtGameTimer);

            //***********************************************************************
            // startTimer()
            //***********************************************************************
            public void startTimer() {
                timerRunning = true;
                Spinner spnTime = (Spinner) getActivity().findViewById(R.id.spnTime);
                String x = spnTime.getSelectedItem().toString();
                if (x.substring(0, 1).equals("Pa"))
                    anaTime = 0;
                else
                    anaTime = Integer.parseInt(x.substring(0, 1));
                anaTime = anaTime * 60 * 1000;
                new CountDownTimer(anaTime, 1000) {

                    //***********************************************************************
                    // onTick()
                    //***********************************************************************
                    public void onTick(long millisUntilFinished) {
                        MillisecondTime = millisUntilFinished;
                        UpdateTime = TimeBuff + MillisecondTime;
                        Seconds = (int) (UpdateTime / 1000);
                        Minutes = Seconds / 60;
                        Seconds = Seconds % 60;
                        textViewTimer.setText(String.format("%02d:%02d  ", Minutes, Seconds));
                    }

                    //***********************************************************************
                    // onFinish()
                    //***********************************************************************
                    public void onFinish() {
                        textViewTimer.setText(R.string.str_time_over_short);
                        Toast.makeText(getContext(), R.string.str_time_over, Toast.LENGTH_SHORT).show();
                        timeOver = true;
                        TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
                        final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
                        rowWord.removeAllViews();
                        rowGuess.removeAllViews();
                    }

                }.start();
            }

            @Override
            //***********************************************************************
            // onClick()
            //***********************************************************************
            public void onClick(View v) {                                       // REFRESH Button
                TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
                final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
                rowWord.removeAllViews();
                rowGuess.removeAllViews();
                anaColumn = 0;
                anaGuess = "";
                for (int i = 0; i < anaLevel; i++) {
                    final ImageView letter = new ImageView(getContext());
                    ImageView guess = new ImageView(getContext());
                    final String l = anaWord.substring(i, i + 1);
                    Log.i(TAG, "LETTER : " + l);
                    letter.setBackgroundResource(ODSLib.getIconIDFromLetter(l));
                    letter.setEnabled(true);
                    guess.setBackgroundResource(ODSLib.getIconIDFromLetter("?", false));
                    anaWordLetters.add(letter);
                    //
                    rowWord.addView(letter);
                    rowGuess.addView(guess);
                    //
                    letter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //***********************************************************************
                        // onClick()
                        //***********************************************************************
                        public void onClick(View v) {                           // LETTERS Buttons
                            TextView txtSolution = vw.findViewById(R.id.txtSolution);
                            txtSolution.setText("");

                            letter.setBackgroundResource(ODSLib.getIconIDFromLetter(l, false));
                            ImageView guess = (ImageView) rowGuess.getVirtualChildAt(anaColumn);
                            guess.setBackgroundResource(ODSLib.getIconIDFromLetter(l, true));
                            anaGuess = anaGuess + l;
                            Log.i(TAG, "GUESS : " + anaGuess);
                            letter.setEnabled(false);
                            anaColumn++;
                            if (anaColumn == anaLevel) {
                                Log.i(TAG, "GUESS! : " + anaGuess);
                                if (anaWords.contains(anaGuess)) {
                                    anaScore++;
                                    anaNewWord(vw);
                                } else {
                                    Toast.makeText(getContext(), R.string.str_missed, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
                if (!timerRunning)
                    startTimer();
            }
        });
        btnAnaRefresh.performClick();
    }
}