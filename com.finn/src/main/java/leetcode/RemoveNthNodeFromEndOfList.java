package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-10
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode5 removeNthFromEnd(ListNode5 head, int n) {
        int len = 0;
        ListNode5 temp = new ListNode5(head, 0);
        while (head != null) {
            len++;
            head = head.next;
        }

        int delete = len - n + 1;
        ListNode5 cur = temp;
        for (int i = 1; i < delete; i++) {
            cur = cur.next;
        }

        // 内存地址引用
        cur.next = cur.next.next;
        return temp.next;
    }

    /**
     * 快慢指针
     * 画个图就明白了
     * @param head
     * @param n
     * @return
     */
    public static ListNode5 removeNthFromEndByDoublePointer(ListNode5 head, int n) {
        ListNode5 dummy = new ListNode5(head, 0);
        ListNode5 fast = head;
        ListNode5 slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Lists5 obj = new Lists5();
        ListNode5 node5 = new ListNode5(1);
        obj.addNode(node5, 2);
        obj.addNode(node5, 5);
        obj.addNode(node5, 6);
        obj.addNode(node5, 9);
        obj.addNode(node5, 12);

        ListNode5 result = removeNthFromEndByDoublePointer(node5, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}

class ListNode5 {
    int val;
    ListNode5 next;

    public ListNode5() {

    }

    public ListNode5(int val) {
        this.val = val;
    }

    public ListNode5(ListNode5 next, int val) {
        this.next = next;
        this.val = val;
    }
}

class Lists5 {
    public void addNode(ListNode5 node, int val) {
        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode5(val);
    }

    public void addNode(ListNode5 node, ListNode5 newNode) {
        while (node.next != null) {
            node = node.next;
        }

        node.next = newNode;
    }
}
