package com.example.balabh.pollrise.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.balabh.pollrise.R;
import com.example.balabh.pollrise.model.Poll;

import java.util.ArrayList;

/**
 * Created by balabh on 6/23/2015.
 */
public class PollViewAdapter extends RecyclerView.Adapter<PollViewAdapter.ViewHolder> {
    private Poll[] mDataset;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mQuestion,
                mDate;
        long id;
        public ViewHolder(View itemView) {
            super(itemView);
            mQuestion = (TextView) itemView.findViewById(R.id.poll_label);
            mDate = (TextView) itemView.findViewById(R.id.poll_date);
        }
    }

    public PollViewAdapter(Poll[] mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
