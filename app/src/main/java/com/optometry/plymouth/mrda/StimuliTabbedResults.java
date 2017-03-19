package com.optometry.plymouth.mrda;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Helpers.trialData;
import Utilities.RecyclerViewAdapter;

public class StimuliTabbedResults extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private PlaceholderFragment placeholder;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static final int NUM_TABS = 1;

    private Map<Integer, trialData> userHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimuli_tabbed_results);

        Intent intent = getIntent();
        userHistory = (Map<Integer, trialData>) intent.getSerializableExtra("userHistory");

        placeholder = new PlaceholderFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(NUM_TABS - 1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stimuli_tabbed_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }

        public PlaceholderFragment sectionZero(Map<Integer, Double> integerDoubleMap){
            PlaceholderFragment fragment = new PlaceholderFragment();

            Bundle args = new Bundle();
            args.putSerializable("position", (HashMap) integerDoubleMap);
            fragment.setArguments(args);
            return fragment;
        }

//        public PlaceholderFragment sectionOne(Map<Integer, Double> integerDoubleMap){
//            PlaceholderFragment fragment = new PlaceholderFragment();
//
//            Bundle args = new Bundle();
//            args.putSerializable("percentagesMap", (HashMap) integerDoubleMap);
//            fragment.setArguments(args);
//            return fragment;
//        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_stimuli_tabbed_results, container, false);
            TextView sectionOne = (TextView) rootView.findViewById(R.id.title);
            TextView sectionZero = (TextView) rootView.findViewById(R.id.average);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);

            //sectionZero.setText("This is text view " + getArguments().getInt("position"));
            //sectionOne.setText("This is text view "+ getArguments().getInt("position"));


            //Map<Integer, Double> mapz = (HashMap<Integer, Double>) getArguments().getSerializable("percentagesMap");
            Map<Integer, Double> mapz = (HashMap<Integer, Double>) getArguments().getSerializable("position");
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(mapz, getActivity());
            recyclerView.setAdapter(adapter);
            //sectionZero.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch(position){
                case 0: //section 1
                    return placeholder.sectionZero(calculateLevelPercentage());
//                case 1: //section 1
//                    return placeholder.sectionOne(calculateLevelPercentage());

//                case 2: //section 1
//                    return PlaceholderFragment.sectionTwo(position);
            }
            //return PlaceholderFragment.newInstance(position + 1);
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return NUM_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "General";
                case 1:
                    return "Optimal";
            }
            return null;
        }
    }


    public Map<Integer, Double> calculateLevelPercentage() {
        int hits = 0;
        int misses = 0;

        Map<Integer, Double> dictPercentage = new HashMap<>();

        for(int i = 0; i < userHistory.size(); i++) {
            int total = 0;
            int numCorrect = 0;
            for (Map.Entry<Integer, trialData> entry : userHistory.entrySet()) {

                if (entry.getValue().getLevel() == i) {
                    total++;

                    if(entry.getValue().isCorrect())
                    {
                        numCorrect++;
                    }
                }

            }

            double percentage = (double) numCorrect / (double) total;
            dictPercentage.put(i, percentage);
            //Do calc for each entry here
        }
        return dictPercentage;
    }


}
