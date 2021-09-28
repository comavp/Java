package ru.comavp.testspringproject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Computer computer = context.getBean("computer", Computer.class);
        System.out.println(computer.playMusic(MusicTypeEnum.CLASSICAL_MUSIC));
        System.out.println(computer.playMusic(MusicTypeEnum.ROCK_MUSIC));
        System.out.println(computer.playMusic(MusicTypeEnum.RAP_MUSIC));

        Music classicalMusic = context.getBean("myClassicalMusicId", Music.class);
        System.out.println(classicalMusic.getSong(0));

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        System.out.println(player.getName());
        System.out.println(player.getVolume());

        context.close();
    }
}
