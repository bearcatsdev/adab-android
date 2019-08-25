package com.ambinusian.adab.recyclerview.discussionrecyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ambinusian.adab.R;

public class DiscussionHolder extends RecyclerView.ViewHolder {
    public TextView discussion_time, discussion_content, discussion_name, discussion_class;
    public CardView card_view_layout;

    public DiscussionHolder(@NonNull View itemView) {
        super(itemView);
        discussion_time = itemView.findViewById(R.id.tv_discussion_time);
        discussion_content = itemView.findViewById(R.id.tv_discussion_content);
        discussion_name = itemView.findViewById(R.id.tv_discussion_name);
        discussion_class = itemView.findViewById(R.id.tv_discussion_class);
        card_view_layout = itemView.findViewById(R.id.card_view_layout);

        card_view_layout.setBackgroundResource(R.drawable.chat_item_layout);
    }
}
