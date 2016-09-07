package com.kokabi.p.navigationsample.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kokabi.p.navigationsample.Objects.GeneralObj;
import com.kokabi.p.navigationsample.R;

import java.util.ArrayList;

public class SampleRVAdapter extends RecyclerView.Adapter<SampleRVAdapter.ViewHolder> {

    Context context;
    ArrayList<GeneralObj> generalList = new ArrayList<>();

    public SampleRVAdapter() {
    }

    public SampleRVAdapter(ArrayList<GeneralObj> dataInput) {
        generalList = dataInput;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            title_tv = (TextView) itemView.findViewById(R.id.title_tv);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GeneralObj generalObj = generalList.get(position);
    }

    @Override
    public int getItemCount() {
        return generalList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /*Clear Method*/
    public void clearHistory() {
        generalList.clear();
        notifyDataSetChanged();
    }
}
