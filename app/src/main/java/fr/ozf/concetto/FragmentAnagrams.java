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
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentAnagrams extends Fragment {
    private int anaLevel = 7;
    private int anaTime = 3;
    private int anaColumn = 0;
    private String anaWord;
    private String anaGuess;
    private int anaScore = 0;
    private ArrayList<String> anaWords;
    private String TAG = "ANAGRAMS";
    ArrayList<ImageView> anaWordLetters = new ArrayList<>();
    ArrayList<ImageView> anaGuessLetters = new ArrayList<>();
    boolean timerRunning = false;

    public FragmentAnagrams() {
        // Required empty public constructor
    }

    public static FragmentAnagrams newInstance(int level) {
        Bundle args = new Bundle();
        args.putInt("level", level);
        FragmentAnagrams fragment = new FragmentAnagrams();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_anagrams, container, false);
        anaLevel = getArguments().getInt("level");
        showAnagrams(vw);
        return vw;
    }

    //***********************************************************************
    // showAnagrams()
    //***********************************************************************
    private void showAnagrams(View vw)
    {
        ImageView btnAnaRefresh = (ImageView) vw.findViewById(R.id.btnAnaRefresh);
        // Hide the keyboard
        // ((InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
        //
        anaNewWord(vw);

        ImageView btnAnaClear = (ImageView) vw.findViewById(R.id.btnAnaClear);
        btnAnaClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
                for (int i = 0; i < anaLevel; i++)
                {
                    final ImageView letter = new ImageView(getContext());
                    ImageView guess = new ImageView(getContext());
                    final String l = anaWord.substring(i, i + 1);
                    Log.i(TAG, "LETTER : " + l);
                    letter.setBackgroundResource(getIconIDFromLetter(l));
                    letter.setEnabled(true);
                    guess.setBackgroundResource(getIconIDFromLetter("?", false));
                    anaWordLetters.add(letter);
                    rowWord.addView(letter);
                    rowGuess.addView(guess);
                    //
                    letter.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            letter.setBackgroundResource(getIconIDFromLetter(l, false));
                            ImageView guess = (ImageView) rowGuess.getVirtualChildAt(anaColumn);
                            guess.setBackgroundResource(getIconIDFromLetter(l, true));
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
        for (int i=0; i<rowLength;i++) {
            ImageView guess = new ImageView(getContext());
            guess.setBackgroundResource(getIconIDFromLetter("?", false));
            row.addView(guess);
        }
    }

    //***********************************************************************
    // anaNewWord()
    //***********************************************************************
    private void anaNewWord(View vw)
    {
        ImageView btnAnaRefresh = (ImageView) vw.findViewById(R.id.btnAnaRefresh);
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
        btnAnaRefresh.setOnClickListener(new View.OnClickListener()
        {

            long MillisecondTime, TimeBuff, UpdateTime = 0L;
            int Seconds, Minutes, MilliSeconds;
            TextView textViewTimer = getActivity().findViewById(R.id.txtGameTimer);

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
                    public void onTick(long millisUntilFinished) {
                        MillisecondTime = millisUntilFinished;
                        UpdateTime = TimeBuff + MillisecondTime;
                        Seconds = (int) (UpdateTime / 1000);
                        Minutes = Seconds / 60;
                        Seconds = Seconds % 60;
                        textViewTimer.setText(String.format("%02d:%02d  ", Minutes, Seconds));
                    }

                    public void onFinish() {
                        textViewTimer.setText("Done!  ");
                    }
                }.start();
            }

            @Override
            public void onClick(View v) {
                TableRow rowWord = (TableRow) vw.findViewById(R.id.rowWord);
                final TableRow rowGuess = (TableRow) vw.findViewById(R.id.rowGuess);
                rowWord.removeAllViews();
                rowGuess.removeAllViews();
                for (int i = 0; i < anaLevel; i++) {
                    final ImageView letter = new ImageView(getContext());
                    ImageView guess = new ImageView(getContext());
                    final String l = anaWord.substring(i, i + 1);
                    Log.i(TAG, "LETTER : " + l);
                    letter.setBackgroundResource(getIconIDFromLetter(l));
                    letter.setEnabled(true);
                    guess.setBackgroundResource(getIconIDFromLetter("?", false));
                    anaWordLetters.add(letter);
                    //
                    rowWord.addView(letter);
                    rowGuess.addView(guess);
                    //
                    letter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            letter.setBackgroundResource(getIconIDFromLetter(l, false));
                            ImageView guess = (ImageView) rowGuess.getVirtualChildAt(anaColumn);
                            guess.setBackgroundResource(getIconIDFromLetter(l, true));
                            anaGuess = anaGuess + l;
                            Log.i(TAG, "GUESS : " + anaGuess);
                            letter.setEnabled(false);
                            anaColumn++;
                            if (anaColumn == anaLevel) {
                                Log.i(TAG, "GUESS! : " + anaGuess);
                                if (anaWords.contains(anaGuess)) {
                                    anaScore++;
                                    anaNewWord(vw);
                                }
                                else {
                                    for (int i = 0; i < anaLevel; i++) {
                                        /*
                                        anaWordLetters.get(i) =
                                        final ImageView letter = new ImageView(getContext());
                                        ImageView guess = new ImageView(getContext());
                                        final String l = anaWord.substring(i, i + 1);
                                        Log.i(TAG, "LETTER : " + l);
                                        letter.setBackgroundResource(getIconIDFromLetter(l));
                                        letter.setEnabled(true);
                                        guess.setBackgroundResource(getIconIDFromLetter("?", false));
                                        anaWordLetters.add(letter);
                                        */
                                        //
                                        rowWord.addView(letter);
                                        rowGuess.addView(guess);
                                        //
                                    }
                                }
                            }
                        }
                    });
                }
                if (! timerRunning)
                    startTimer();
            }
        });
        btnAnaRefresh.performClick();
    }

    //***********************************************************************
    // getIconIDFromLetter()
    //***********************************************************************
    private int getIconIDFromLetter(String letter)
    {
        int ic = R.mipmap.ic_dixio_letter_question;
        char c = letter.charAt(0);

        switch (c)
        {
            case '!':
                ic = getIconIDFromLetter("?", false);
                break;
            case '?':
                ic = getIconIDFromLetter("?", true);
                break;
            default:
                if (Character.isLowerCase(c))
                {
                    ic = getIconIDFromLetter(letter, false);
                } else
                {
                    ic = getIconIDFromLetter(letter, true);
                }
                break;
        }
        return ic;
    }

    //***********************************************************************
    // getIconIDFromLetter()
    //***********************************************************************
    private int getIconIDFromLetter(String letter, boolean enabled)
    {
        int ic = R.mipmap.ic_dixio_letter_question;

        if (enabled == true)
        {
            switch (letter.toUpperCase())
            {
                case "A":
                    ic = R.mipmap.ic_dixio_letter_a;
                    break;
                case "B":
                    ic = R.mipmap.ic_dixio_letter_b;
                    break;
                case "C":
                    ic = R.mipmap.ic_dixio_letter_c;
                    break;
                case "D":
                    ic = R.mipmap.ic_dixio_letter_d;
                    break;
                case "E":
                    ic = R.mipmap.ic_dixio_letter_e;
                    break;
                case "F":
                    ic = R.mipmap.ic_dixio_letter_f;
                    break;
                case "G":
                    ic = R.mipmap.ic_dixio_letter_g;
                    break;
                case "H":
                    ic = R.mipmap.ic_dixio_letter_h;
                    break;
                case "I":
                    ic = R.mipmap.ic_dixio_letter_i;
                    break;
                case "J":
                    ic = R.mipmap.ic_dixio_letter_j;
                    break;
                case "K":
                    ic = R.mipmap.ic_dixio_letter_k;
                    break;
                case "L":
                    ic = R.mipmap.ic_dixio_letter_l;
                    break;
                case "M":
                    ic = R.mipmap.ic_dixio_letter_m;
                    break;
                case "N":
                    ic = R.mipmap.ic_dixio_letter_n;
                    break;
                case "O":
                    ic = R.mipmap.ic_dixio_letter_o;
                    break;
                case "P":
                    ic = R.mipmap.ic_dixio_letter_p;
                    break;
                case "Q":
                    ic = R.mipmap.ic_dixio_letter_q;
                    break;
                case "R":
                    ic = R.mipmap.ic_dixio_letter_r;
                    break;
                case "S":
                    ic = R.mipmap.ic_dixio_letter_s;
                    break;
                case "T":
                    ic = R.mipmap.ic_dixio_letter_t;
                    break;
                case "U":
                    ic = R.mipmap.ic_dixio_letter_u;
                    break;
                case "V":
                    ic = R.mipmap.ic_dixio_letter_v;
                    break;
                case "W":
                    ic = R.mipmap.ic_dixio_letter_w;
                    break;
                case "X":
                    ic = R.mipmap.ic_dixio_letter_x;
                    break;
                case "Y":
                    ic = R.mipmap.ic_dixio_letter_y;
                    break;
                case "Z":
                    ic = R.mipmap.ic_dixio_letter_z;
                    break;
                default:
                case "?":
                    ic = R.mipmap.ic_dixio_letter_question;
                    break;
            }
        } else
        {
            switch (letter.toUpperCase())
            {
                case "A":
                    ic = R.mipmap.ic_dixio_letter_disabled_a;
                    break;
                case "B":
                    ic = R.mipmap.ic_dixio_letter_disabled_b;
                    break;
                case "C":
                    ic = R.mipmap.ic_dixio_letter_disabled_c;
                    break;
                case "D":
                    ic = R.mipmap.ic_dixio_letter_disabled_d;
                    break;
                case "E":
                    ic = R.mipmap.ic_dixio_letter_disabled_e;
                    break;
                case "F":
                    ic = R.mipmap.ic_dixio_letter_disabled_f;
                    break;
                case "G":
                    ic = R.mipmap.ic_dixio_letter_disabled_g;
                    break;
                case "H":
                    ic = R.mipmap.ic_dixio_letter_disabled_h;
                    break;
                case "I":
                    ic = R.mipmap.ic_dixio_letter_disabled_i;
                    break;
                case "J":
                    ic = R.mipmap.ic_dixio_letter_disabled_j;
                    break;
                case "K":
                    ic = R.mipmap.ic_dixio_letter_disabled_k;
                    break;
                case "L":
                    ic = R.mipmap.ic_dixio_letter_disabled_l;
                    break;
                case "M":
                    ic = R.mipmap.ic_dixio_letter_disabled_m;
                    break;
                case "N":
                    ic = R.mipmap.ic_dixio_letter_disabled_n;
                    break;
                case "O":
                    ic = R.mipmap.ic_dixio_letter_disabled_o;
                    break;
                case "P":
                    ic = R.mipmap.ic_dixio_letter_disabled_p;
                    break;
                case "Q":
                    ic = R.mipmap.ic_dixio_letter_disabled_q;
                    break;
                case "R":
                    ic = R.mipmap.ic_dixio_letter_disabled_r;
                    break;
                case "S":
                    ic = R.mipmap.ic_dixio_letter_disabled_s;
                    break;
                case "T":
                    ic = R.mipmap.ic_dixio_letter_disabled_t;
                    break;
                case "U":
                    ic = R.mipmap.ic_dixio_letter_disabled_u;
                    break;
                case "V":
                    ic = R.mipmap.ic_dixio_letter_disabled_v;
                    break;
                case "W":
                    ic = R.mipmap.ic_dixio_letter_disabled_w;
                    break;
                case "X":
                    ic = R.mipmap.ic_dixio_letter_disabled_x;
                    break;
                case "Y":
                    ic = R.mipmap.ic_dixio_letter_disabled_y;
                    break;
                case "Z":
                    ic = R.mipmap.ic_dixio_letter_disabled_z;
                    break;
                default:
                case "?":
                    ic = R.mipmap.ic_dixio_letter_disabled_question;
                    break;
            }
        }

        return ic;
    }


}