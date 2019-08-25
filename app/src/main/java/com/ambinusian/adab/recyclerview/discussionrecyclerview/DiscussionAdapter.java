package com.ambinusian.adab.recyclerview.discussionrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ambinusian.adab.R;

import java.util.ArrayList;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionHolder> {
    public ArrayList<DiscussionModel> lists;
    public Context context;

    public DiscussionAdapter(Context context, ArrayList<DiscussionModel> lists){
        this.context = context;
        this.lists = lists;
    }

    @Override
    public DiscussionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_discussion,parent,false);
        return new DiscussionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionHolder holder, int position) {
        DiscussionModel item = lists.get(position);
        holder.discussion_time.setText(item.getTime());
        holder.discussion_content.setText(item.getContent());
        holder.discussion_name.setText(item.getName());
        holder.discussion_class.setText(item.getDiscussion_class());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
