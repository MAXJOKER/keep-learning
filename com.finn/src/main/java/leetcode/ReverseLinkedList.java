package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-08 00:30
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
    public static ListNode1 reverseList(ListNode1 head) {
        ListNode1 cur = head;
        ListNode1 prev = null;
        while(cur != null) {
            ListNode1 next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Lists1 list1 = new Lists1();
        ListNode1 listNode1 = new ListNode1(1);
        list1.addNode(listNode1, 3);
        list1.addNode(listNode1, 5);

        ListNode1 result = reverseList(listNode1);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1() {};
    ListNode1(int val) {
        this.val = val;
    }

    ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }
}

class Lists1 {
    public void addNode(ListNode1 node, int value) {
        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode1(value);
    }
}
