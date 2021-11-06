package ru.comavp.springripper.quoters;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuoterTest {

    @Test
    public void testSayQuote() throws InterruptedException {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        //context.getBean(Quoter.class).sayQuote();
    }
}
