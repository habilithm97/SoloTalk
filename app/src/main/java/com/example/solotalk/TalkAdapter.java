package com.example.solotalk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {

    ArrayList<Talk> items;

    @NonNull
    @Override
    public TalkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.talk_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TalkAdapter.ViewHolder holder, int position) {
        holder.tv.setText(items.get(position).getStr());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Talk item) {
        items.add(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }

    public TalkAdapter(ArrayList<Talk> dataSet) {
        items = dataSet;
    }
}
