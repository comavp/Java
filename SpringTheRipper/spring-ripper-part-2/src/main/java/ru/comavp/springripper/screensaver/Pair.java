package ru.comavp.springripper.screensaver;

public final class Pair<T, R> {

    private T first;
    private R second;

    public Pair(T first, R second) {
        this.first = first;
        this.second = second;
    }

    public T getKey() {
        return this.first;
    }

    public R getValue() {
        return this.second;
    }
}
