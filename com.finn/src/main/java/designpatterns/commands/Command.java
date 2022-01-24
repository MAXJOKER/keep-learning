package designpatterns.commands;

/**
 * 抽象命令角色
 */
public interface Command {
    public void execute();
    public void undo();
}
