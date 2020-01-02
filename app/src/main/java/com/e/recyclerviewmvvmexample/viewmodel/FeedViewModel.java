package com.e.recyclerviewmvvmexample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.recyclerviewmvvmexample.model.JsonResponse;
import com.e.recyclerviewmvvmexample.repositories.FeedRepository;

public class FeedViewModel extends ViewModel {

    private MutableLiveData<JsonResponse> mutableLiveData;
    private FeedRepository feedRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        feedRepository = FeedRepository.getInstance();
        mutableLiveData = feedRepository.getFeed("24", "2", "0");

    }

    public LiveData<JsonResponse> getFeedRepository() {
        return mutableLiveData;
    }
}
