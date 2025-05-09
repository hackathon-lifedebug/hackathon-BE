package com.example.lifedebug.domain.user.entity;

import lombok.Getter;

@Getter
public enum City {
    SEOUL("서울특별시"),
    BUSAN("부산광역시"),
    DAEGU("대구광역시"),
    INCHEON("인천광역시"),
    GWANGJU("광주광역시"),
    DAEJEON("대전광역시"),
    ULSAN("울산광역시"),
    SEJONG("세종특별자치시"),
    GYEONGGI("경기도"),
    GANGWON("강원특별자치도"),
    CHUNGBUK("충청북도"),
    CHUNGNAM("충청남도"),
    JEOLLABUK("전북특별자치도"),
    JEOLLANAM("전라남도"),
    GYEONGBUK("경상북도"),
    GYEONGNAM("경상남도"),
    JEJU("제주특별자치도");

    private final String koreanName;

    City(String koreanName) {
        this.koreanName = koreanName;
    }

    // 한글 이름을 통해 Region 값을 찾는 메서드
    public static City fromKoreanName(String koreanName) {
        for (City city : City.values()) {
            if (city.getKoreanName().equals(koreanName)) {
                return city;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 지역 이름입니다: " + koreanName);
    }
}