package com.example.ournyc.presentation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ournyc.R;
import com.example.ournyc.SendToFragment;
import com.example.ournyc.data.model.ProgramModel;

public class ProgramViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "FINDME";
    SendToFragment sendToFragment;


    public ProgramViewHolder(@NonNull View itemView, SendToFragment sendToFragment) {
        super(itemView);
        this.sendToFragment = sendToFragment;

    }

    public void onBind(final ProgramModel programModel) {
        Log.d(TAG, "onBind: " + programModel.getProgram_name());
        ((TextView) itemView.findViewById(R.id.text_sample)).setText(programModel.getProgram_name());


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendToFragment.sendToFragment(programModel.getProgram_name(), programModel.getProgram_date());


            }
        });

    }


}

