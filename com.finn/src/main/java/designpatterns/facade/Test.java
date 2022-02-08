package designpatterns.facade;

public class Test {
    public static void main(String[] args) {
        Light light = new Light();
        Music music = new Music();
        TV tv = new TV();

        IntelligentHomeFacade homeFacade = new IntelligentHomeFacade(light, music, tv);
        homeFacade.comeHome(light, music, tv);
        homeFacade.comeOffice(light, music, tv);
    }
}
