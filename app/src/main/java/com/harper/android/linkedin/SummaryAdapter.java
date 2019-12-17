package com.harper.android.linkedin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created: xuemaomao
 * Date: 2019-12-16
 * Package: com.harper.android.linkedin
 * File: SummaryAdapter
 * Description: TODO
 */
public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {
    private List<QuestionItem> mList;

    public SummaryAdapter(List<QuestionItem> mList) {
        this.mList = mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvQuestion;
        public TextView tvAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvQuestion =  itemView.findViewById(R.id.item_question);
            tvAnswer = itemView.findViewById(R.id.item_answer);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.activity_summary_item, parent,false);
        SummaryAdapter.ViewHolder viewHolder = new SummaryAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionItem item = mList.get(position);
        holder.tvQuestion.setText(item.getQuestion());
        holder.tvAnswer.setText(item.getAnswer());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
