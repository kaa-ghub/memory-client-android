package com.snowballs.gloryroad;

public class SoldierDto {

    private String name;
    private String birthDate;
    private String birthPlace;

    public SoldierDto(String name, String birthDate, String birthPlace) {
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
}
