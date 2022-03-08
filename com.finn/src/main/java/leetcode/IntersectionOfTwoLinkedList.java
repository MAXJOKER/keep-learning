package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-08 19:32
 * 求出两个链表的相交点
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 方法一：哈希集合判断，先把A塞到hashmap中，然后contains判断
 *
 * 方法二：双指针
 *
 * 题解：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
 *
 */
public class IntersectionOfTwoLinkedList {

    public static ListNode2 getIntersectionNode(ListNode2 node1, ListNode2 node2) {
        if (node1 == null || node2 == null) {
            return null;
        }

        ListNode2 p1 = node1;
        ListNode2 p2 = node2;

        // 理论上得是这么判断
        // p1 p2 内存地址不一样
        // while (p1.val != p2.val) {
        while (p1 != p2) {
            p1 = p1 == null ? node2 : p1.next;
            p2 = p2 == null ? node1 : p2.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        ListNode2 node1 = new ListNode2(1);
        ListNode2 node2 = new ListNode2(2);
        Lists2 lists2 = new Lists2();

        lists2.addNode(node1, 3);
        lists2.addNode(node1, 5);
        lists2.addNode(node1, 7);
        lists2.addNode(node1, 9);

        lists2.addNode(node2, 4);
        lists2.addNode(node2, 6);
        lists2.addNode(node2, 7);
        lists2.addNode(node2, 9);

        ListNode2 result = getIntersectionNode(node1, node2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}

class ListNode2 {
    int val;
    ListNode2 next;

    public ListNode2() {

    }
    public ListNode2(int val) {
        this.val = val;
    }
    public ListNode2(int val, ListNode2 next) {
        this.val = val;
        this.next = next;
    }
}

class Lists2 {
    public void addNode(ListNode2 node, int val) {
        while (node.next != null) {
            node = node.next;
        }
        node.next = new ListNode2(val);
    }
}
