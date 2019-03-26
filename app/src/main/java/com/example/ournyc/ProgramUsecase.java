package com.example.ournyc;

import com.example.ournyc.data.model.ProgramModel;

import java.util.List;

import io.reactivex.Observable;

public interface ProgramUsecase {

    Observable<List<ProgramModel>> getProgramList();
}
