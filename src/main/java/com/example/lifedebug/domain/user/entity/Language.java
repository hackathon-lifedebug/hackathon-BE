package com.example.lifedebug.domain.user.entity;

import lombok.Getter;

@Getter
public enum Language {
    KOREAN("한국어"),
    ENGLISH("영어"),
    CHINESE("중국어"),
    VIETNAMESE("베트남어"),
    THAI("태국어"),
    RUSSIAN("러시아어"),
    JAPANESE("일본어"),
    INDONESIAN("인도네시아어"),
    FRENCH("프랑스어"),
    SPANISH("스페인어"),
    GERMAN("독일어");

    private final String koreanName;

    Language(String koreanName) {
        this.koreanName = koreanName;
    }

    // 한글 이름을 통해 언어 값을 찾는 메서드
    public static Language fromKoreanName(String koreanName) {
        for (Language language : Language.values()) {
            if (language.getKoreanName().equals(koreanName)) {
                return language;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 언어입니다: " + koreanName);
    }
}
