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
    String required_documents_summary;
    String url_of_online_application;
    String get_help_by_calling_311_please_provide_what_to_ask_for_when_calling_311;
    String get_help_by_calling_other_than_311;
    String language;
    String program_acronym_if_any;
    String plain_language_program_name;
    String brief_excerpt;
    String apply_online_call_to_action;
    String url_of_pdf_application_forms;
    String how_to_apply_or_enroll_in_person;
    String office_locations_url;

    public String getRequired_documents_summary() {
        return required_documents_summary;
    }

    public String getUrl_of_online_application() {
        return url_of_online_application;
    }

    public String getGet_help_by_calling_311_please_provide_what_to_ask_for_when_calling_311() {
        return get_help_by_calling_311_please_provide_what_to_ask_for_when_calling_311;
    }

    public String getGet_help_by_calling_other_than_311() {
        return get_help_by_calling_other_than_311;
    }

    public String getLanguage() {
        return language;
    }

    public String getProgram_acronym_if_any() {
        return program_acronym_if_any;
    }

    public String getPlain_language_program_name() {
        return plain_language_program_name;
    }

    public String getBrief_excerpt() {
        return brief_excerpt;
    }

    public String getApply_online_call_to_action() {
        return apply_online_call_to_action;
    }

    public String getUrl_of_pdf_application_forms() {
        return url_of_pdf_application_forms;
    }

    public String getHow_to_apply_or_enroll_in_person() {
        return how_to_apply_or_enroll_in_person;
    }

    public String getOffice_locations_url() {
        return office_locations_url;
    }

    public String getHow_to_apply_or_enroll_by_phone() {
        return how_to_apply_or_enroll_by_phone;
    }

    String how_to_apply_or_enroll_by_phone;


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
