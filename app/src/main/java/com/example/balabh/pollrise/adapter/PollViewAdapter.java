package com.example.balabh.pollrise.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.balabh.pollrise.R;
import com.example.balabh.pollrise.model.Poll;

import java.util.List;

/**
 * Created by balabh on 6/23/2015.
 */
public class PollViewAdapter extends RecyclerView.Adapter<PollViewAdapter.ViewHolder> {
    private List<Poll> mDataset;
    private static ClickListener clickListener;
    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView mQuestion,
                mDate;
        long id;
        public ViewHolder(View itemView) {
            super(itemView);
            mQuestion = (TextView) itemView.findViewById(R.id.poll_label);
            mDate = (TextView) itemView.findViewById(R.id.poll_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(getPosition(), view);
        }
    }

    public void setClickListener(ClickListener clickListener) {
        PollViewAdapter.clickListener = clickListener;
    }

    public PollViewAdapter(List<Poll> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.poll_view_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mDate.setText((CharSequence) mDataset.get(i).getDate().toString());
        viewHolder.mQuestion.setText(mDataset.get(i).getQuestion());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(Poll poll, int index) {
        mDataset.add(poll);
        notifyItemInserted(index);
    }

    public void delete(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    public interface ClickListener {
        public void onClick(int position, View v);
    }

}
