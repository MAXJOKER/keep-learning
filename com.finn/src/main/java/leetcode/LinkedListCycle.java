package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maxjoker
 * @date 2022-03-08 20:19
 *
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * 141. 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * 示例：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 */
public class LinkedListCycle {
    /**
     * set判断是否访问过结点
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param listNode3
     * @return
     */
    public static boolean hasCycle(ListNode3 listNode3) {
        if (listNode3 == null || listNode3.next == null) {
            return false;
        }
        Set<ListNode3> set = new HashSet<>();
        while (listNode3.next != null) {
            if (!set.add(listNode3)) {
                return true;
            }

            listNode3 = listNode3.next;
        }

        return false;
    }

    /**
     * 快慢指针
     * 快指针和慢指针遍历链表，如果是循环链表，则总会有相交的时候
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param node3
     * @return
     */
    public static boolean hasCycleByFloyd(ListNode3 node3) {
        if (node3 == null || node3.next == null) {
            return false;
        }

        ListNode3 slow = node3;
        ListNode3 fast = node3.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Lists3 lists3 = new Lists3();
        ListNode3 node3 = new ListNode3(1);
        ListNode3 node31 = new ListNode3(3);
        lists3.addNode(node3, node31);
        lists3.addNode(node3, 5);
        lists3.addNode(node3, 7);
        lists3.addNode(node3, node31);

        System.out.println(hasCycleByFloyd(node3));
    }
}

class ListNode3 {
    int val;
    ListNode3 next;

    public ListNode3() {

    }
    public ListNode3(int val) {
        this.val = val;
    }
    public ListNode3(int val, ListNode3 next) {
        this.val = val;
        this.next = next;
    }
}

class Lists3 {
    public void addNode(ListNode3 node3, int val) {
        while (node3.next != null) {
            node3 = node3.next;
        }
        node3.next = new ListNode3(val);
    }

    public void addNode(ListNode3 node3, ListNode3 node33) {
        while (node3.next != null) {
            node3 = node3.next;
        }

        node3.next = node33;
    }
}
