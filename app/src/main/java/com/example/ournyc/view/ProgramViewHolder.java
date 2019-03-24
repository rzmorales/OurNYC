package com.example.ournyc.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ournyc.R;
import com.example.ournyc.model.ProgramModel;

public class ProgramViewHolder extends RecyclerView.ViewHolder {
    public ProgramViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(final ProgramModel programModel) {
        Log.d("FINDME", "onBind: " + programModel.getProgram_name());
        ((TextView) itemView.findViewById(R.id.text_sample)).setText(programModel.getProgram_name());
    }
}
