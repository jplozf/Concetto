package fr.ozf.concetto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FragmentODSLookup extends Fragment {
    private ODSDraught ods;
    private ProgressDialog dialog;

    public FragmentODSLookup() {
        // Required empty public constructor
    }

    public static FragmentODSLookup newInstance() {
        return (new FragmentODSLookup());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_ods_lookup, container, false);
        dialog = new ProgressDialog(getActivity());

        //******************************************************************************************
        // TAB SEARCH : Map functions to events
        //******************************************************************************************
        // Set edit to uppercase
        EditText txtDraught = (EditText) vw.findViewById(R.id.txtDraught);
        txtDraught.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        // Find button
        ImageView btnFind = (ImageView) vw.findViewById(R.id.btnFind);
        btnFind.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    // Get the draught from input text field
                    String draught = txtDraught.getText().toString();
                    ((MainActivity)getActivity()).odsLib.setDraught(draught);
                    Log.d("DRAUGHT", draught);
                    if (!draught.isEmpty()) {
                        dialog.setMessage(getString(R.string.str_searching));
                        dialog.show();
                        // Hide the keyboard
                        ((InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
                        // Search for words
                        ods = new ODSDraught(draught);
                        new BackgroundODSSearchingTask(getActivity(), ods) {
                            @Override
                            public void doInBackground(ODSDraught o) {
                                Log.d("ODS", "in background !!!");
                                o.words = ((MainActivity)getActivity()).odsLib.findWords(o.draught);
                            }

                            @Override
                            public void onPostExecute(Activity a, ODSDraught o) {
                                TableLayout tbl = (TableLayout) (a.findViewById(R.id.tblWords));

                                while (tbl.getChildCount() > 1)
                                    tbl.removeView(tbl.getChildAt(tbl.getChildCount() - 1));

                                ArrayList<String> sortedList = new ArrayList<String>();
                                sortedList = ODSLib.sortWords(o.words, ODSLib.SORT_ALPHA);
                                addWordsToTable(vw, tbl, sortedList);

                                TextView tvStats = (TextView) vw.findViewById(R.id.txtWordsStats);
                                int nbWords = ODSLib.getWordsListSize(o.words);
                                Log.d("ODS", String.valueOf(nbWords));
                                if (nbWords > 0) {
                                    int maxLength = ODSLib.getWordsListBestLength(o.words);
                                    int maxValue = ODSLib.getWordsListBestValue(o.words);
                                    tvStats.setText(String.format("%s : %s / %s", Integer.toString(nbWords), Integer.toString(maxLength), Integer.toString(maxValue)));
                                }
                                else {
                                    tvStats.setText("0 : 0 / 0");
                                }

                                // Sort
                                while (tbl.getChildCount() > 1)
                                    tbl.removeView(tbl.getChildAt(tbl.getChildCount() - 1));
                                sortedList = new ArrayList<String>();
                                sortedList = ODSLib.sortWords(ods.words, ODSLib.SORT_ALPHA);
                                addWordsToTable(vw, tbl, sortedList);


                                if (dialog.isShowing())
                                {
                                    dialog.dismiss();
                                }
                            }
                        }.execute();

                        // WordsList = bt.w;
                    }
                }
            }
        );

        // New random draught button
        ImageView btnRefresh = (ImageView) vw.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    ((MainActivity)getActivity()).odsLib.setDraught(ODSLib.getRandomDraught(ODSLib.DRAUGHT_SCRABBLE_NO_JOKERS, 7));
                    TextView txtDraught = (TextView) vw.findViewById(R.id.txtDraught);
                    txtDraught.setText(((MainActivity)getActivity()).odsLib.draught);
                }
            }
        );

        // Alpha sort button
        ImageView btnWordsSortAlpha = (ImageView) vw.findViewById(R.id.btnWordsSortAlpha);
        btnWordsSortAlpha.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    TableLayout tbl = (TableLayout) vw.findViewById(R.id.tblWords);

                    while (tbl.getChildCount() > 1)
                        tbl.removeView(tbl.getChildAt(tbl.getChildCount() - 1));

                    ArrayList<String> sortedList = new ArrayList<String>();
                    sortedList = ODSLib.sortWords(ods.words, ODSLib.SORT_ALPHA);
                    addWordsToTable(vw, tbl, sortedList);
                }
            }
        );

        // Length sort button
        ImageView btnWordsSortLength = (ImageView) vw.findViewById(R.id.btnWordsSortLength);
        btnWordsSortLength.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    TableLayout tbl = (TableLayout) vw.findViewById(R.id.tblWords);

                    while (tbl.getChildCount() > 1)
                        tbl.removeView(tbl.getChildAt(tbl.getChildCount() - 1));

                    ArrayList<String> sortedList = new ArrayList<String>();
                    sortedList = ODSLib.sortWords(ods.words, ODSLib.SORT_LENGTH);
                    addWordsToTable(vw, tbl, sortedList);
                }
            }
        );

        // Value sort button
        ImageView btnWordsSortValue = (ImageView) vw.findViewById(R.id.btnWordsSortValue);
        btnWordsSortValue.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    TableLayout tbl = (TableLayout) vw.findViewById(R.id.tblWords);

                    while (tbl.getChildCount() > 1)
                        tbl.removeView(tbl.getChildAt(tbl.getChildCount() - 1));

                    ArrayList<String> sortedList = new ArrayList<String>();
                    sortedList = ODSLib.sortWords(ods.words, ODSLib.SORT_VALUE);
                    addWordsToTable(vw, tbl, sortedList);
                }
            }
        );

        return vw;
    }

//***********************************************************************
// addWordsToTable()
//***********************************************************************
    @SuppressLint({"SetTextI18n", "RtlHardcoded"})
    private void addWordsToTable(View vw, TableLayout tb, ArrayList<String> words) {
        int c;
        int i = 0;
        for (String word : words) {
            if (i % 2 == 0) {
                c = getResources().getColor(R.color.Rifle);
            } else {
                c = getResources().getColor(R.color.Nickel);
            }
            tb.setColumnShrinkable(1, true);
            TableRow row = new TableRow(vw.getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(lp);
            row.setPadding(0, 0, 0, 0);
            // Word
            TextView tvWord = new TextView(vw.getContext());
            tvWord.setPadding(200, 0, 50, 0);
            tvWord.setGravity(Gravity.RIGHT);
            tvWord.setBackgroundColor(c);
            tvWord.setTypeface(null, Typeface.BOLD);
            tvWord.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvWord.setText(word);
            // Length
            TextView tvLength = new TextView(vw.getContext());
            tvLength.setPadding(50, 0, 50, 0);
            tvLength.setGravity(Gravity.RIGHT);
            tvLength.setBackgroundColor(c);
            tvLength.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvLength.setText(Integer.toString(word.length()));
            // Value
            TextView tvValue = new TextView(vw.getContext());
            tvValue.setPadding(50, 0, 200, 0);
            tvValue.setGravity(Gravity.RIGHT);
            tvValue.setBackgroundColor(c);
            tvValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvValue.setText(Integer.toString(ODSLib.getWordValue(word)));
            // Add all these labels to row
            row.addView(tvWord);
            row.addView(tvLength);
            row.addView(tvValue);
            //
            row.setId(i); // Set an unique ID for current row
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = v.getId(); // Get the unique ID for the clicked row
                    String word = words.get(i);
                    TextView tv = (TextView) getActivity().findViewById(R.id.txtSearchWord);
                    tv.setText(word);
                    ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
                    viewPager.setCurrentItem(1); // Display the Online Lookup Fragment
                }
            });
            // Add row to inner table
            tb.addView(row);
            i++;
        }
    }
}

class ODSDraught {
    ArrayList <String> words;
    String draught;
    public ODSDraught(String d) {
        this.draught = d;
    }
}

abstract class BackgroundODSSearchingTask {

    private final Activity a;
    public ODSDraught ods;

    public BackgroundODSSearchingTask(Activity activity, ODSDraught ods) {
        this.a = activity;
        this.ods = ods;
    }

    private void startBackground() {
        new Thread(new Runnable() {
            public void run() {

                doInBackground(ods);
                a.runOnUiThread(new Runnable() {
                    public void run() {
                        onPostExecute(a, ods);
                    }
                });
            }
        }).start();
    }
    public BackgroundODSSearchingTask execute(){
        startBackground();
        return null;
    }

    public abstract void doInBackground(ODSDraught o);
    public abstract void onPostExecute(Activity a, ODSDraught o);
}
