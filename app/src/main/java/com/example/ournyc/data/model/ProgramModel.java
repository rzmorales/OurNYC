package com.example.ournyc.data.model;

public class ProgramModel implements Comparable<ProgramModel> {
    String program_name;
    String program_category;
    String population_served;
    String age_group;
    String program_description;
    String date;

    public String getDate() {
        return date;
    }

    public String getProgram_name() {
        return program_name;
    }

    public String getProgram_category() {
        return program_category;
    }

    public String getPopulation_served() {
        return population_served;
    }

    public String getAge_group() {
        return age_group;
    }

    public String getProgram_description() {
        return program_description;
    }

    @Override
    public int compareTo(ProgramModel o) {
        return this.getProgram_name().equals(o.getProgram_name()) ? 1 : 0;
    }
}
