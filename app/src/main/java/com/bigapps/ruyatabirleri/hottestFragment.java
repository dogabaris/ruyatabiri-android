package com.bigapps.ruyatabirleri;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadyfade on 22.09.2016.
 */
public class hottestFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private SuperRecyclerView mRecyclerView;
    private DreamsAdapter mAdapter;
    private List<pojoDream> dreamList = new ArrayList<>();
    private View view;
    private boolean rock = true;
    private String[] dreams;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hottest_fragment, container, false);

        mRecyclerView = (SuperRecyclerView) view.findViewById(R.id.hottestlist);

        mAdapter = new DreamsAdapter(dreamList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setRefreshListener(this);
        mRecyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(top);

        return view;
    }


    @Override
    public void onRefresh() {
        Toast.makeText(view.getContext(), "Refresh", Toast.LENGTH_LONG).show();
        dreamList.clear();
        dreamList.addAll(dreamList);
        rock = !rock;
    }

}