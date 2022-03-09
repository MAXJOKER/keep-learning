package leetcode;

import lombok.val;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maxjoker
 * @date 2022-03-09
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 142. 环形链表 II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 */
public class LinkedListCycle2 {

    /**
     * 迭代
     * 利用 hashset 唯一性
     * @param head
     * @return
     */
    public static ListNode4 detectCycle(ListNode4 head) {
        Set<ListNode4> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }

            head = head.next;
        }

        return null;
    }

    /**
     * 快慢指针
     * 题解：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     *
     * 概括一下：
     *
     * 根据：
     *
     * f=2s （快指针每次2步，路程刚好2倍）
     *
     * f = s + nb (相遇时，刚好多走了n圈）
     *
     * 推出：s = nb
     *
     * 从head结点走到入环点需要走 ： a + nb， 而slow已经走了nb，那么slow再走a步就是入环点了。
     *
     * 如何知道slow刚好走了a步？ 从head开始，和slow指针一起走，相遇时刚好就是a步
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode4 detectCycleByFloyd(ListNode4 head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode4 slow = head;
        ListNode4 fast = head;

        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static void main(String[] args) {
        Lists4 obj = new Lists4();
        ListNode4 node = new ListNode4(1);
        ListNode4 nodeRepeat = new ListNode4(6);

        obj.addNode(node, 3);
        obj.addNode(node, 5);
        obj.addNode(node, nodeRepeat);
        obj.addNode(node, 9);
        obj.addNode(node, 12);
        obj.addNode(node, nodeRepeat);

        ListNode4 result = detectCycleByFloyd(node);
        System.out.println(result.val);

    }
}

class ListNode4 {
    int val;
    ListNode4 next;

    public ListNode4() {

    }

    public ListNode4(int val) {
        this.val = val;
    }

    public ListNode4(ListNode4 node, int val) {
        this.next = node;
        this.val = val;
    }
}

class Lists4 {
    public void addNode(ListNode4 node4, int val) {
        while (node4.next != null) {
            node4 = node4.next;
        }

        node4.next = new ListNode4(val);
    }

    public void addNode(ListNode4 node4, ListNode4 node41) {
        while (node4.next != null) {
            node4 = node4.next;
        }

        node4.next = node41;
    }
}
