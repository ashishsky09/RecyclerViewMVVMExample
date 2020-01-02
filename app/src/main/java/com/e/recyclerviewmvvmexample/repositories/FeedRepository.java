package com.e.recyclerviewmvvmexample.repositories;

import androidx.lifecycle.MutableLiveData;

import com.e.recyclerviewmvvmexample.model.JsonResponse;
import com.e.recyclerviewmvvmexample.networking.FeedApi;
import com.e.recyclerviewmvvmexample.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepository {

    private static FeedRepository feedRepository;
    private FeedApi feedApi;

    public FeedRepository() {
        feedApi = RetrofitService.createService(FeedApi.class);
    }

    public static FeedRepository getInstance() {
        if (feedRepository == null) {
            feedRepository = new FeedRepository();
        }
        return feedRepository;
    }

    public MutableLiveData<JsonResponse> getFeed(String user_id, String user_type, String status_type) {
        final MutableLiveData<JsonResponse> feedData = new MutableLiveData<>();
        feedApi.getFeedList(user_id, user_type, status_type).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call,
                                   Response<JsonResponse> response) {
                if (response.isSuccessful()) {
                    feedData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                feedData.setValue(null);
            }
        });
        return feedData;
    }
}
