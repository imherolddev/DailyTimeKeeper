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

import com.imherolddev.dailytimekeeper.R;
import com.imherolddev.dailytimekeeper.adapters.MainFragmentAdapter;
import com.imherolddev.dailytimekeeper.listeners.ListenerUtility;
import com.imherolddev.dailytimekeeper.models.ClockTime;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

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

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainFragmentAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void initFAB(Activity activity) {

        fab.attachToRecyclerView(recyclerView);

    }

    @Override
    public ArrayList<ClockTime> getClockTimeList() {
        return clockTimeList;
    }
}
