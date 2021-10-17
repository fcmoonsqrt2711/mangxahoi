package com.tav.common.util.bean;

public enum LocaleEnum {

    VIETNAM_VIETNAMESE("Vietnamese", "Vietnam", "vi_VN");

    private String localeId;
    private String country;
    private String language;

    LocaleEnum(String country, String language, String localeId) {
        this.country = country;
        this.language = language;
        this.localeId = localeId;
    }

    public String getLocaleId() {
        return localeId;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

}
