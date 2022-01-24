package designpatterns.commands;

public class Test {
    public static void main(String[] args) {
        LightOnCommand lightOnCommand = new LightOnCommand(new Light());
        LightOffCommand lightOffCommand = new LightOffCommand(new Light());
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        simpleRemoteControl.setCommand(lightOnCommand);
        simpleRemoteControl.buttonWasPressed();

        simpleRemoteControl.setCommand(lightOffCommand);
        simpleRemoteControl.buttonWasPressed();

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        System.out.println(remoteControl);
        remoteControl.onButtonWasPressed(0);
        remoteControl.offButtonWasPressed(0);
        remoteControl.undoButtonWasPressed();

        // 批量执行命令
        Command[] partyOnCommand = {lightOnCommand};
        MacroCommand macroCommand = new MacroCommand(partyOnCommand);
        remoteControl.setCommand(0, macroCommand, new NoCommand());
        System.out.println(remoteControl);
        remoteControl.onButtonWasPressed(0);
    }
}
