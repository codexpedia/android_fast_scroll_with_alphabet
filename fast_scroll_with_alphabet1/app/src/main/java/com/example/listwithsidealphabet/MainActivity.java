package com.example.listwithsidealphabet;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

// https://github.com/code-computerlove/FastScrollRecyclerView
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<String> myDataset = new ArrayList<String>();
        for(int i=0; i<26; i++) {
            for(int j=1; j<=10; j++) {
                myDataset.add(Character.toString((char)(65 + i)) + " " + j + " Row item");
            }
        }
        HashMap<String, Integer> mapIndex = calculateIndexesForName(myDataset);

        mAdapter = new MyAdapter(myDataset, mapIndex);
        mRecyclerView.setAdapter(mAdapter);
        FastScrollRecyclerViewItemDecoration decoration = new FastScrollRecyclerViewItemDecoration(this);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private HashMap<String, Integer> calculateIndexesForName(ArrayList<String> items){
        HashMap<String, Integer> mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i<items.size(); i++){
            String name = items.get(i);
            String index = name.substring(0,1);
            index = index.toUpperCase();

            if (!mapIndex.containsKey(index)) {
                mapIndex.put(index, i);
            }
        }
        return mapIndex;
    }

}