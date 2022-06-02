package com.example.solotalk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {

    ArrayList<Talk> items;

    Context context;

    @NonNull
    @Override
    public TalkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.talk_item, viewGroup, false);

        context = viewGroup.getContext();

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = (TextView)itemView.findViewById(R.id.tv);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();

                    // 대화상자 생성
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("삭제하기");
                    builder.setMessage("선택한 메시지를 삭제하시겠습니까? ");
                    builder.setIcon(R.drawable.delete);
                    builder.setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteItem(position);
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
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
        notifyItemRemoved(position); // 특정 아이템 한 개를 삭제
        // 여러 개의 아이템을 변경
        notifyItemRangeChanged(position, items.size()); // (변경된 첫 번째 아이템의 위치, 변경된 아이템의 갯수)
    }

    public TalkAdapter(ArrayList<Talk> dataSet) {
        items = dataSet;
    }
}
