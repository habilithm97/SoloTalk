package com.example.solotalk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {

    ArrayList<Talk> items;
    Activity activity;

    Context context;

    long time;
    Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static int position;

    public TalkAdapter(ArrayList<Talk> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TalkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.talk_item, viewGroup, false);

        context = viewGroup.getContext();

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TalkAdapter.ViewHolder holder, int position) {
        holder.chatBubbleTv.setText(items.get(position).getStr());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Talk item) {
        items.add(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView chatBubbleTv, dateTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTv = (TextView)itemView.findViewById(R.id.dateTv);
            dateTv.setText(getTime());

            chatBubbleTv = (TextView)itemView.findViewById(R.id.chatBubbleTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();

                    String str = chatBubbleTv.getText().toString();

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("str", str);
                    context.startActivity(intent);
                    //((Activity)context).startActivityForResult(intent, 97);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();

                    // ???????????? ??????
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("????????????");
                    builder.setMessage("????????? ???????????? ????????????????????????? ");
                    builder.setIcon(R.drawable.delete);
                    builder.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteItem(position);
                        }
                    });
                    builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                    return true;
                }
            });
        }
    }

    public void deleteItem(int position) {
        items.remove(position);
        notifyItemRemoved(position); // ?????? ????????? ??? ?????? ??????
        // ?????? ?????? ???????????? ??????
        notifyItemRangeChanged(position, items.size()); // (????????? ??? ?????? ???????????? ??????, ????????? ???????????? ??????)
    }

    public void filterList(ArrayList<Talk> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }

    private String getTime() {
        time = System.currentTimeMillis();
        date = new Date(time);
        return dateFormat.format(date);
    }

    public TalkAdapter(ArrayList<Talk> dataSet) {
        items = dataSet;
    }
}
