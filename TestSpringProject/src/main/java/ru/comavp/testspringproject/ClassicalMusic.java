package ru.comavp.testspringproject;

public class ClassicalMusic implements Music {
    private ClassicalMusic() {}

    public static ClassicalMusic getClassicalMusic() {
        System.out.println("Fabric method was called");
        return new ClassicalMusic();
    }

    @Override
    public String getSong() {
        return "Classical music";
    }
}
