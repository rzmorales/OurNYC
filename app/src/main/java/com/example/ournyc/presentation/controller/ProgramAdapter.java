package com.example.ournyc.presentation.controller;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ournyc.R;
import com.example.ournyc.callback.ProgramDiffCallback;
import com.example.ournyc.data.model.ProgramModel;
import com.example.ournyc.presentation.view.ProgramViewHolder;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramViewHolder> {

    List<ProgramModel> apiServiceList;

    public ProgramAdapter(List<ProgramModel> apiServiceList) {
        this.apiServiceList = apiServiceList;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new ProgramViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder programViewHolder, int i) {
        programViewHolder.onBind(apiServiceList.get(i));
    }

    @Override
    public int getItemCount() {
        return apiServiceList.size();
    }

    public void updateList(List<ProgramModel> apiService) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ProgramDiffCallback(apiServiceList, apiService));
        apiServiceList.clear();
        apiServiceList.addAll(apiService);
        diffResult.dispatchUpdatesTo(this);
    }
}