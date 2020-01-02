package com.e.recyclerviewmvvmexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.recyclerviewmvvmexample.adapters.FeedAdapter;
import com.e.recyclerviewmvvmexample.model.Record;
import com.e.recyclerviewmvvmexample.viewmodel.FeedViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Response";
    int i = 0;
    private List<Record> recordArrayList = new ArrayList<>();
    private List<Record> records = new ArrayList();
    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;
    private FeedViewModel feedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedRecyclerView = findViewById(R.id.recycler_view);

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        feedViewModel.init();

        feedViewModel.getFeedRepository().observe(this, jsonResponse -> {

            records = jsonResponse.getRecord();
            recordArrayList.addAll(records);
            feedAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (feedAdapter == null) {
            feedAdapter = new FeedAdapter(MainActivity.this, recordArrayList);
            feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            feedRecyclerView.setAdapter(feedAdapter);
            feedRecyclerView.setItemAnimator(new DefaultItemAnimator());
            feedRecyclerView.setNestedScrollingEnabled(true);
        } else {
            feedAdapter.notifyDataSetChanged();
        }
    }
}
