package com.example.ournyc.data.model;

public class ProgramModel implements Comparable<ProgramModel> {
    String program_name;
    String program_category;
    String population_served;
    String age_group;
    String program_description;
    String date;
    String get_help_in_person;
    String plain_language_eligibility;
    String get_help_by_email;
    String get_help_online;
    String get_help_summary;
    String government_agency;
    String heads_up;
    String how_to_apply_summary;
    String how_to_apply_or_enroll_online;


    public String getGet_help_in_person() {
        return get_help_in_person;
    }

    public String getPlain_language_eligibility() {
        return plain_language_eligibility;
    }

    public String getGet_help_by_email() {
        return get_help_by_email;
    }

    public String getGet_help_online() {
        return get_help_online;
    }

    public String getGet_help_summary() {
        return get_help_summary;
    }

    public String getGovernment_agency() {
        return government_agency;
    }

    public String getHeads_up() {
        return heads_up;
    }

    public String getHow_to_apply_summary() {
        return how_to_apply_summary;
    }

    public String getHow_to_apply_or_enroll_online() {
        return how_to_apply_or_enroll_online;
    }


    public String getProgram_help_in_person() {
        return get_help_in_person;
    }

    public String getProgram_date() {
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
