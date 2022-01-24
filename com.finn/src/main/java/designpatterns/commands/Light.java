package designpatterns.commands;

/**
 * 接收者角色
 */
public class Light {
    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}
