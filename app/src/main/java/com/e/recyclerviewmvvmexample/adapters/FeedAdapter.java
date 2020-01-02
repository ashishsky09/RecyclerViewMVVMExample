package com.e.recyclerviewmvvmexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.recyclerviewmvvmexample.R;
import com.e.recyclerviewmvvmexample.model.Record;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewModel> {

    Context context;
    List<Record> records;

    public FeedAdapter(Context context, List<Record> records) {
        this.context = context;
        this.records
                = records
        ;
    }

    @NonNull
    @Override
    public FeedAdapter.FeedViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false);
        return new FeedViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.FeedViewModel holder, int position) {
        holder.textName.setText(records
                .get(position).getOrderId());
        holder.textEmail.setText(records
                .get(position).getUsername());
        holder.textMobile.setText(records
                .get(position).getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return records
                .size();
    }

    public class FeedViewModel extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textEmail;
        TextView textMobile;

        public FeedViewModel(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.name);
            textEmail = itemView.findViewById(R.id.email);
            textMobile = itemView.findViewById(R.id.mobile);

        }
    }
}

