package com.example.ournyc.domain;

import com.example.ournyc.data.model.ProgramModel;

import java.util.List;

import io.reactivex.Observable;

public interface ProgramRepository {
    Observable<List<ProgramModel>> getPrograms();
}
