package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author maxjoker
 * @date 2022-03-10 21:26
 * https://leetcode-cn.com/problems/palindrome-linked-list
 *
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */
public class PalindromeLinkedList {

    /**
     * 栈
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode6 head) {
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode6 temp = head;

        while (temp != null) {
            deque.push(temp.val);
            temp = temp.next;
        }

        while (head != null) {
            if (deque.pop() != head.val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 他妈的我是废物吗? 我他妈才不是
     * @param head
     * @return
     */
    public static boolean isPalindromeByDoublePointer(ListNode6 head) {
        if (head == null) {
            return false;
        }

        ListNode6 firstHalfEnd = getFirstHalfEnd(head);
        ListNode6 secondHalfStart = reverseNode(firstHalfEnd.next);

        ListNode6 p1 = head;
        ListNode6 p2 = secondHalfStart;
        boolean result = true;

        // 头尾值比对
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        firstHalfEnd.next = reverseNode(secondHalfStart);

        return result;
    }

    /**
     * 翻转链表
     * @param firstHalfEnd
     * @return
     */
    private static ListNode6 reverseNode(ListNode6 firstHalfEnd) {
        ListNode6 cur = firstHalfEnd;
        ListNode6 prev = null;

        while (cur != null) {
            ListNode6 next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    /**
     * fast 走两步
     * slow 走一步
     * 当fast走到末尾的时候
     * slow走到中间
     * fast 的 步长是 slow 的2倍
     * @param head
     * @return
     */
    private static ListNode6 getFirstHalfEnd(ListNode6 head) {
        ListNode6 fast = head;
        ListNode6 slow = head;

        while (fast.next != null && fast.next.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        Lists6 obj = new Lists6();
        ListNode6 node = new ListNode6(1);
        obj.addNode(node, 2);
        obj.addNode(node, 2);
        obj.addNode(node, 1);

        System.out.println(isPalindromeByDoublePointer(node));
    }
}

class ListNode6 {
    int val;
    ListNode6 next;

    public ListNode6() {

    }

    public ListNode6(int val) {
        this.val =val;
    }

    public ListNode6(int val, ListNode6 node) {
        this.val =val;
        this.next = node;
    }
}

class Lists6 {
    public void addNode(ListNode6 node, int val) {
        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode6(val);
    }
}
