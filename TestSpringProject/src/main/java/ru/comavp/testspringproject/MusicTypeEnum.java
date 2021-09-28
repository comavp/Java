package ru.comavp.testspringproject;

public enum MusicTypeEnum {
    CLASSICAL_MUSIC(0), ROCK_MUSIC(1), RAP_MUSIC(2);

    private int typeIndex;

    MusicTypeEnum(final int typeInd) {
        this.typeIndex = typeInd;
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }
}
