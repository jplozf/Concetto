package fr.ozf.concetto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentOnlineLookup extends Fragment {
    private OnlineSearch os;
    private ProgressDialog dialog;
    private String currentWord;

    public FragmentOnlineLookup() {
        // Required empty public constructor
    }

    public static FragmentOnlineLookup newInstance() {
        return (new FragmentOnlineLookup());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_online_lookup, container, false);
        dialog = new ProgressDialog(getActivity());

        // Set edit to uppercase
        EditText txtSearchWord = (EditText) vw.findViewById(R.id.txtSearchWord);
        txtSearchWord.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        // Find button
        ImageView btnSearchWord = (ImageView) vw.findViewById(R.id.btnSearchWord);
        btnSearchWord.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View vw) {
                        doSearch(txtSearchWord.getText().toString().trim());
                    }
                }
        );

        return vw;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // User is viewing the fragment,
            // or fragment is inside the screen
            EditText txtSearchWord = (EditText) getView().findViewById(R.id.txtSearchWord);
            String word = txtSearchWord.getText().toString().trim();
            Log.d("ONLINE_SEARCH", "onResume : " + word);
            if (!word.isEmpty() && !word.equals(currentWord))
                doSearch(word);
        }
    }

    public void doSearch(String w) {
        dialog.setMessage(getString(R.string.str_searching));
        dialog.show();
        currentWord = w;
        // Hide the keyboard
        ((InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
        // Get the draught from input text field
        // String word = ((TextView) vw.findViewById(R.id.txtSearchWord)).getText().toString().trim();
        Log.d("ONLINE_SEARCH", w);
        if (!w.isEmpty()) {
            Log.d("ONLINE_SEARCH", w);
            ((MainActivity) getActivity()).odsLib.setDraught(w);
            // Search for words
            os = new OnlineSearch(w);
            new BackgroundOnlineSearchingTask(getActivity(), os) {
                @Override
                public void doInBackground(OnlineSearch os) {
                    Log.d("ONLINE_SEARCH", "in background !!!");
                    String def = "<center><table>";
                    if (((MainActivity) getActivity()).odsLib.isValidWord(w)) {
                        def += "<tr><td><b>" + w + "</b></td><td bgcolor=\"#079153\" align=\"center\">Version " + ((MainActivity) getActivity()).odsLib.getODSVersion() + "</td></tr>";
                        def += "<tr><td colspan=\"2\" align=\"center\">Ce mot existe dans ce dictionnaire</td></tr>";
                        def += "<tr><td>Lettres</td><td align=\"right\"><b>" + Integer.toString(w.length()) + "</b></td></tr>";
                        def += "<tr><td>Points</td><td align=\"right\"><b>" + Integer.toString(((MainActivity) getActivity()).odsLib.getWordValue(w)) + "</b></td></tr>";
                        def += "</table></center><br><br>";
                        //
                        def += "<i>Définition fournie par <b>" + ((MainActivity) getActivity()).odsLib.getDicoVersion(0) + "</b> : </i><br><br>";
                        def += ((MainActivity) getActivity()).odsLib.getDefinition(0, w);
                        def += "<p><center><i>&copy; J.-P. Liguori 2021 - All rights reserved</i></center></p>";
                        //
                    } else {
                        def += "<tr><td><b>" + w + "</b></td><td bgcolor=\"#d3220e\" align=\"center\">Version " + ((MainActivity) getActivity()).odsLib.getODSVersion() + "</td></tr>";
                        def += "<tr><td colspan=\"2\" align=\"center\">Ce mot n'existe pas dans ce dictionnaire</td></tr>";
                        def += "</table></center><br><br>";
                    }
                    os.def = def;
                    Log.d("ONLINE_SEARCH", def);
                }

                @Override
                public void onPostExecute(Activity a, OnlineSearch os) {
                    WebView wv = (WebView) a.findViewById(R.id.txtWordDefinition);
                    wv.setBackgroundColor(getResources().getColor(R.color.Grullo));
                    wv.getSettings().setDomStorageEnabled(true);
                    wv.loadDataWithBaseURL(null, os.def, "text/html; charset=utf-8", "UTF-8", null);
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    view.scrollTo(0,0);

                                }
                            }, 250);
                        }
                    });
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }.execute();
        }
    }
}

class OnlineSearch {
    String word;
    String def;

    public OnlineSearch(String word) {
        this.word = word;
    }
}

abstract class BackgroundOnlineSearchingTask {

    private final Activity a;
    public OnlineSearch os;

    public BackgroundOnlineSearchingTask(Activity activity, OnlineSearch os) {
        this.a = activity;
        this.os = os;
    }

    private void startBackground() {
        new Thread(new Runnable() {
            public void run() {

                doInBackground(os);
                a.runOnUiThread(new Runnable() {
                    public void run() {
                        onPostExecute(a, os);
                    }
                });
            }
        }).start();
    }

    public BackgroundOnlineSearchingTask execute(){
        startBackground();
        return null;
    }

    public abstract void doInBackground(OnlineSearch os);
    public abstract void onPostExecute(Activity a, OnlineSearch os);
}
