package com.example.ournyc.callback;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.ournyc.data.model.ProgramModel;

import java.util.List;

public class ProgramDiffCallback extends DiffUtil.Callback {

    private List<ProgramModel> oldList;
    private List<ProgramModel> newList;

    public ProgramDiffCallback(List<ProgramModel> oldList, List<ProgramModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
//        return oldList != null ? oldList.size(): 0;
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).getProgram_name().equals(newList.get(newPosition).getProgram_name());
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        return newList.get(newPosition).compareTo(oldList.get(oldPosition)) == 1;
    }


    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
