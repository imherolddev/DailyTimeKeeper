package com.imherolddev.dailytimekeeper.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.imherolddev.dailytimekeeper.R;
import com.imherolddev.dailytimekeeper.adapters.MainFragmentAdapter;
import com.imherolddev.dailytimekeeper.listeners.ListenerUtility;
import com.imherolddev.dailytimekeeper.models.ClockTime;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by imherolddev on 2/7/2015.
 */
public class MainFragment extends Fragment implements ListenerUtility {

    public static final String TAG = "MainFragment";

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.btn_fab)
    FloatingActionButton fab;

    private MainFragmentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private boolean isClocked = false;

    private SharedPreferences sharedPreferences;

    private ArrayList<ClockTime> clockTimeList = new ArrayList<>();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.inject(this, rootView);

        Activity activity = getActivity();

        initRecyclerView(activity);
        initFAB(activity);

        return rootView;

    }

    private void initRecyclerView(Activity activity) {

        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainFragmentAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void initFAB(final Activity activity) {

        fab.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJobPicker(activity, getJobNameArray());
            }
        });

    }

    @Override
    public ArrayList<ClockTime> getClockTimeList() {
        return clockTimeList;
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private void showJobPicker(Activity activity, String[] jobNameList) {

        new MaterialDialog.Builder(getActivity())
                .title(R.string.job_picker_title)
                .customView(getTextView(activity, jobNameList), false)
                .positiveText(R.string.btn_positive_text)
                .negativeText(R.string.btn_negative_text)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {

                        TextView textView = (TextView) dialog.getCustomView();
                        String jobName = textView.getText().toString();

                        clockTimeList.add(0, new ClockTime(jobName, System.currentTimeMillis(), 0));
                        adapter.notifyDataSetChanged();

                        toast("Clocked in to " + jobName);

                    }
                })
                .show();

    }

    private String[] getJobNameArray() {

        ArrayList<String> jobNames = new ArrayList<>();

        for (ClockTime clockTime : clockTimeList) {
            jobNames.add(clockTime.getJobName());
        }

        HashSet<String> returnList = new HashSet<>(jobNames);

        return returnList.toArray(new String[returnList.size()]);

    }

    private AutoCompleteTextView getTextView(Activity activity, String[] jobs) {

        AutoCompleteTextView textView = new AutoCompleteTextView(activity);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_list_item_1, jobs);

        textView.setAdapter(arrayAdapter);

        return textView;

    }

}
