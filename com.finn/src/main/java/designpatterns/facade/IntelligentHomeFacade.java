package designpatterns.facade;

public class IntelligentHomeFacade {
    Light light;
    Music music;
    TV tv;

    public IntelligentHomeFacade(Light light, Music music, TV tv) {
        this.light = light;
        this.music = music;
        this.tv = tv;
    }

    public void comeHome(Light light, Music music, TV tv) {
        light.on();
        music.on();
        tv.on();
    }

    public void comeOffice(Light light, Music music, TV tv) {
        light.off();
        music.off();
        tv.off();
    }
}
