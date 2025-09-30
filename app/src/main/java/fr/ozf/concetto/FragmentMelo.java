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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//***********************************************************************
// Class FragmentMelo()
//***********************************************************************
public class FragmentMelo extends Fragment {
    private int anaLevel = 7;
    private int anaTime = 3;
    private int anaColumn = 0;
    private String anaWord;
    private String anaGuess = "";
    private int anaScore = 0;
    private ArrayList<String> anaWords;
    private String TAG = "MELO";
    ArrayList<ImageView> anaWordLetters = new ArrayList<>();
    ArrayList<ImageView> anaGuessLetters = new ArrayList<>();
    boolean timerRunning = false;
    boolean timeOver = false;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 0;
    long MillisecondTime, TimeBuff, UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds;
    TextView textViewTimer;

    public FragmentMelo() {
        // Required empty public constructor
    }

    //***********************************************************************
    // startTimer()
    //***********************************************************************
    public void startTimer(long millisInFuture) {
        textViewTimer = getActivity().findViewById(R.id.txtGameTimer);
        timerRunning = true;
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {

            //***********************************************************************
            // onTick()
            //***********************************************************************
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                MillisecondTime = millisUntilFinished;
                UpdateTime = TimeBuff + MillisecondTime;
                Seconds = (int) (UpdateTime / 1000);
                Minutes = Seconds / 60;
                Seconds = Seconds % 60;
                if (textViewTimer != null)
                    textViewTimer.setText(String.format("%02d:%02d  ", Minutes, Seconds));
            }

            //***********************************************************************
            // onFinish()
            //***********************************************************************
            public void onFinish() {
                if (textViewTimer != null)
                    textViewTimer.setText(R.string.str_time_over_short);
                Toast.makeText(getContext(), R.string.str_time_over, Toast.LENGTH_SHORT).show();
                timeOver = true;
                timerRunning = false;
                if (getView() != null) {
                    TableRow rowWord = (TableRow) getView().findViewById(R.id.rowWord);
                    final TableRow rowGuess = (TableRow) getView().findViewById(R.id.rowGuess);
                    rowWord.removeAllViews();
                    rowGuess.removeAllViews();
                }
            }

        }.start();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            anaWord = savedInstanceState.getString("anaWord");
            anaWords = savedInstanceState.getStringArrayList("anaWords");
            anaScore = savedInstanceState.getInt("anaScore");
            timeLeftInMillis = savedInstanceState.getLong("timeLeftInMillis");
            timerRunning = savedInstanceState.getBoolean("timerRunning");
            anaGuess = savedInstanceState.getString("anaGuess");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("anaWord", anaWord);
        outState.putStringArrayList("anaWords", anaWords);
        outState.putInt("anaScore", anaScore);
        outState.putLong("timeLeftInMillis", timeLeftInMillis);
        outState.putBoolean("timerRunning", timerRunning);
        outState.putString("anaGuess", anaGuess);
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

        // Setup Listeners
        ImageView btnAnaClear = (ImageView) vw.findViewById(R.id.btnAnaClear);
        btnAnaClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                       // CLEAR Button
                StringBuilder txt = new StringBuilder();
                if (anaWords != null) {
                    for (int i = 0; i < anaWords.size(); i++) {
                        txt.append(anaWords.get(i)).append(" - ");
                    }
                    txt.setLength(txt.length() - 3);
                }
                TextView txtSolution = vw.findViewById(R.id.txtSolution);
                txtSolution.setText(txt.toString());
                anaNewWord(vw);
            }
        });

        ImageView btnAnaRefresh = (ImageView) vw.findViewById(R.id.btnAnaRefresh);
        btnAnaRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                       // REFRESH Button
                if (anaWord == null) {
                    anaNewWord(vw);
                } else {
                    displayCurrentWord(vw);
                }

                if (!timerRunning) {
                    Spinner spnTime = (Spinner) getActivity().findViewById(R.id.spnTime);
                    String x = spnTime.getSelectedItem().toString();
                    if (x.substring(0, 1).equals("Pa"))
                        anaTime = 0;
                    else
                        anaTime = Integer.parseInt(x.substring(0, 1));
                    anaTime = anaTime * 60 * 1000;
                    startTimer(anaTime);
                }
            }
        });


        // If anaWord is null, it means this is a fresh game. Start one.
        // Otherwise, the state was restored in onCreate, so just display the board.
        if (anaWord == null) {
            btnAnaRefresh.performClick();
        } else {
            displayCurrentWord(vw);
            TextView txtAnaScore = (TextView) vw.findViewById(R.id.txtAnaScore);
            txtAnaScore.setText(Integer.toString(anaScore));
        }

        return vw;
    }

    //***********************************************************************
    // dummyRow()
    //***********************************************************************
    private void displayCurrentWord(View vw) {
        TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
        final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
        rowWord.removeAllViews();
        rowGuess.removeAllViews();
        anaColumn = (anaGuess != null) ? anaGuess.length() : 0;

        // Create a mutable list of characters available to be picked.
        ArrayList<Character> availableLetters = new ArrayList<>();
        if (anaWord != null) {
            for (char c : anaWord.toCharArray()) {
                availableLetters.add(c);
            }
        }

        // Remove letters that are already part of the current guess.
        if (anaGuess != null) {
            for (char c : anaGuess.toCharArray()) {
                availableLetters.remove(Character.valueOf(c));
            }
        }

        // Build the top row of letters (the anagram).
        if (anaWord != null) {
            for (int i = 0; i < anaWord.length(); i++) {
                final ImageView letter = new ImageView(getContext());
                ImageView guess = new ImageView(getContext());
                final String l = anaWord.substring(i, i + 1);
                Log.i(TAG, "LETTER : " + l);

                // If an instance of the letter is still available, it's enabled.
                if (availableLetters.contains(l.charAt(0))) {
                    letter.setBackgroundResource(ODSLib.getIconIDFromLetter(l));
                    letter.setEnabled(true);
                    // Decrement the count of this available letter.
                    availableLetters.remove(Character.valueOf(l.charAt(0)));
                } else {
                    letter.setBackgroundResource(ODSLib.getIconIDFromLetter(l, false));
                    letter.setEnabled(false);
                }

                guess.setBackgroundResource(ODSLib.getIconIDFromLetter("?", false));
                anaWordLetters.add(letter);
                rowWord.addView(letter);
                rowGuess.addView(guess);

                letter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
        }

        // Redraw the guess row with the letters from the restored guess.
        if (anaGuess != null) {
            for (int i = 0; i < anaGuess.length(); i++) {
                ImageView guess = (ImageView) rowGuess.getVirtualChildAt(i);
                guess.setBackgroundResource(ODSLib.getIconIDFromLetter(anaGuess.substring(i, i + 1), true));
            }
        }
    }

    //***********************************************************************
    // anaNewWord()
    //***********************************************************************
    private void anaNewWord(View vw) {
        TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
        rowWord.removeAllViews();
        final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
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
        displayCurrentWord(vw);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (timerRunning && timeLeftInMillis > 0) {
            startTimer(timeLeftInMillis);
        }
    }
}