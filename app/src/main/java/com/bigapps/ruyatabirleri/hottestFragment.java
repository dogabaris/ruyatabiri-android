package com.bigapps.ruyatabirleri;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shadyfade on 22.09.2016.
 */
public class hottestFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener , OnMoreListener {
    private SuperRecyclerView mRecyclerView;
    private DreamsAdapter mAdapter;
    private List<pojoDream> dreamList = new ArrayList<>();
    private View view;
    private boolean rock = true;
    private int pageid=0;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hottest_fragment, container, false);

        getHottestDreams();

        mRecyclerView = (SuperRecyclerView) view.findViewById(R.id.hottestlist);

        mAdapter = new DreamsAdapter(dreamList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setRefreshListener(this);

        /*swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.hottestSwipe);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(android.R.color.holo_green_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark);
        //mRecyclerView.addItemDecoration(top);*/

        return view;
    }

    public void getHottestDreams(){
        Global.getService().getHottestDreams(pageid,new Callback<List<pojoDream>>() {
            @Override
            public void success(List<pojoDream> pojoDreams, Response response) {
                mAdapter.insert(pojoDreams,0);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(view.getContext(), "Rüyalar çekilirken sorun çıktı!", Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void onRefresh() {
        //Toast.makeText(view.getContext(), "Refresh", Toast.LENGTH_LONG).show();
        dreamList.clear();
        getHottestDreams();
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

    }
}