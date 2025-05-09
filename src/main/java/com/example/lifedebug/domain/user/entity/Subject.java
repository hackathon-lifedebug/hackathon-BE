package com.example.lifedebug.domain.user.entity;

import lombok.Getter;

@Getter
public enum Subject {
    LIVING_SUPPORT("생활적응"),
    COMMUNICATION("한국어/소통"),
    EMPLOYMENT("취업/노동"),
    MEDICAL_HEALTH("의료/건강"),
    LEGAL_VISA("법률/비자/행정"),
    CULTURE("문화/예절"),
    EDUCATION("교육"),
    FAMILY("가족/양육"),
    PUBLIC_SERVICE("공공서비스"),
    LOCAL_INFO("지역정보"),
    FRIEND("친구");

    private final String koreanName;

    Subject(String koreanName) {
        this.koreanName = koreanName;
    }

    // 한글 이름을 통해 주제 키워드 값을 찾는 메서드
    public static Subject fromKoreanName(String koreanName) {
        for (Subject subject : Subject.values()) {
            if (subject.getKoreanName().equals(koreanName)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주제입니다: " + koreanName);
    }
}
