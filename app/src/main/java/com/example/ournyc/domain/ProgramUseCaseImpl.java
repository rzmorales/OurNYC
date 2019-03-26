package com.example.ournyc.domain;

import com.example.ournyc.ProgramUsecase;
import com.example.ournyc.data.model.ProgramModel;

import java.util.List;

import io.reactivex.Observable;

public class ProgramUseCaseImpl implements ProgramUsecase {
    private final ProgramRepository programRepository;

    public ProgramUseCaseImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Observable<List<ProgramModel>> getProgramList() {
        return programRepository.getPrograms();
    }
}
