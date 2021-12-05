package fr.ozf.concetto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager mgr) {
        super(mgr);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentODSLookup.newInstance();
            case 1:
                return FragmentOnlineLookup.newInstance();
            case 2:
                return FragmentGame.newInstance();
            case 3:
                return FragmentAbout.newInstance();
            case 4:
                return FragmentPreferences.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return(5);
    }
}