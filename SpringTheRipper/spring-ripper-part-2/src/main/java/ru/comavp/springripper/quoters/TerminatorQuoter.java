package ru.comavp.springripper.quoters;

import javax.annotation.PostConstruct;

@Profiling
@DeprecatedClass(T100.class)
public class TerminatorQuoter implements Quoter {

    private String message;
    @InjectRandomInt(min = 1, max = 7)
    private int repeat;

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
        System.out.println(repeat);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
