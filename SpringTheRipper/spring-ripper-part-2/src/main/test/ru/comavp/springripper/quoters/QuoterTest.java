package ru.comavp.springripper.quoters;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.comavp.springripper.PropertyFileApplicationContext;
import ru.comavp.springripper.screensaver.ColorFrame;
import ru.comavp.springripper.screensaver.Config;

public class QuoterTest {

    @Test
    public void testSayQuote() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Quoter.class).sayQuote();
    }

    @Test
    public void propertyFileApplicationContextTest() {
        ApplicationContext context = new PropertyFileApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuote();
    }

    @Test
    public void screensaverTest() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(100);
        }
    }
}
