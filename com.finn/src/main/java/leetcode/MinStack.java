package leetcode;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * https://leetcode-cn.com/problems/min-stack/
 */

/**
 * 思路分析：
 * 在代码实现的时候有两种方式：
 *
 * 1、辅助栈和数据栈同步
 *
 * 特点：编码简单，不用考虑一些边界情况，就有一点不好：辅助栈可能会存一些“不必要”的元素。
 *
 * 2、辅助栈和数据栈不同步
 *
 * 特点：由“辅助栈和数据栈同步”的思想，我们知道，当数据栈进来的数越来越大的时候，我们要在辅助栈顶放置和当前辅助栈顶一样的元素，这样做有点“浪费”。基于这一点，我们做一些“优化”，但是在编码上就要注意一些边界条件。
 *
 * （1）辅助栈为空的时候，必须放入新进来的数；
 *
 * （2）新来的数小于或者等于辅助栈栈顶元素的时候，才放入，特别注意这里“等于”要考虑进去，因为出栈的时候，连续的、相等的并且是最小值的元素要同步出栈；
 *
 * （3）出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈。
 *
 * 总结一下：出栈时，最小值出栈才同步；入栈时，最小值入栈才同步。(最小值在辅助栈和数据栈中的位置是一样的)
 *
 * 对比：个人觉得“同步栈”的方式更好一些，因为思路清楚，因为所有操作都同步进行，所以调试代码、定位问题也简单。“不同步栈”，虽然减少了一些空间，但是在“出栈”、“入栈”的时候还要做判断，也有性能上的消耗。
 *
 * 时间复杂度：O(1)
 * 空间复杂度：O(n)
 */
public class MinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> helperStack;

    public MinStack() {
        dataStack = new Stack<>();
        helperStack = new Stack<>();
    }

    public void push(int val) {
        dataStack.add(val);
        if (helperStack.isEmpty() || helperStack.peek() >= val) {
            helperStack.add(val);
        } else {
            helperStack.add(helperStack.peek());
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
            helperStack.pop();
        }
    }

    public int top() {
        if (!dataStack.isEmpty()) {
            return dataStack.peek();
        }

        throw new RuntimeException("error");
    }

    public int getMin() {
        if (!helperStack.isEmpty()) {
            return helperStack.peek();
        }

        throw new RuntimeException("error");
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(2);
        stack.push(-3);
        stack.push(-6);

        System.out.println(stack.getMin());
        System.out.println(stack.top());
        stack.pop();

    }
}

/**
 * 数据栈 和 辅助栈 不同步
 * 时间复杂度：O(1)
 * 空间复杂度：O(n)
 */
class MinStack2 {
    private Stack<Integer> dataStack;
    private Stack<Integer> helperStack;

    public MinStack2() {
        dataStack = new Stack<>();
        helperStack = new Stack<>();
    }

    // 思路 2：辅助栈和数据栈不同步
    // 关键 1：辅助栈的元素空的时候，必须放入新进来的数
    // 关键 2：新来的数小于或者等于辅助栈栈顶元素的时候，才放入（特别注意这里等于要考虑进去）
    // 关键 3：出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈，即"出栈保持同步"就可以了
    public void push(int val) {
        dataStack.add(val);
        if (helperStack.isEmpty() || helperStack.peek() >= val) {
            helperStack.add(val);
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            int dataTop = dataStack.pop();
            // 因为helperStack中保存的数据 和 dataStack中的数据 数量上不一定是一样的，需要判断是否相等才能pop出来
            if (dataTop == helperStack.peek()) {
                helperStack.pop();
            }
        }
    }

    public int top() {
        if (!dataStack.isEmpty()) {
            return dataStack.peek();
        }

        throw new RuntimeException("error");
    }

    public int getMin() {
        if (!helperStack.isEmpty()) {
            return helperStack.peek();
        }

        throw new RuntimeException("error");
    }

    public static void main(String[] args) {
        MinStack2 stack = new MinStack2();
        stack.push(2);
        stack.push(2);
        stack.push(-3);
        stack.push(-6);

        System.out.println(stack.getMin());
        System.out.println(stack.top());
        stack.pop();

    }
}
