package com.bigapps.ruyatabirleri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shadyfade on 22.09.2016.
 */
public class DreamsAdapter extends RecyclerView.Adapter<DreamsAdapter.MyViewHolder> {
    private List<pojoDream> dreamList;

    public DreamsAdapter(List<pojoDream> dreamList) {
        this.dreamList = dreamList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dream, sender, date, reply;

        public MyViewHolder(View view) {
            super(view);
            dream = (TextView) view.findViewById(R.id.dream);
            sender = (TextView) view.findViewById(R.id.sender);
            date = (TextView) view.findViewById(R.id.date);
            reply = (TextView) view.findViewById(R.id.reply);
        }
    }


    @Override
    public DreamsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dream_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DreamsAdapter.MyViewHolder holder, int position) {
        pojoDream dream = dreamList.get(position);
        holder.dream.setText(dream.getDescription());
        holder.sender.setText(dream.getUser().getEmail());
        holder.date.setText(dream.getCreateDate());
        holder.reply.setText(dream.getReply());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(List<pojoDream> dream) {
        insert(dream, dreamList.size());
    }

    public void insert(List<pojoDream> dream, int position) {
        dreamList.addAll(position, dream);

        notifyItemInserted(position);
    }

    public void remove(int position) {
        dreamList.remove(position);

        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return dreamList.size();
    }
}
