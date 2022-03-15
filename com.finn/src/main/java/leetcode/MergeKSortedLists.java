package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-14 21:58
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 */
public class MergeKSortedLists {
    /**
     * 时间复杂度：O(k^2 * n) k 为数组长度，n为链表结点总数
     * 空间复杂度：O(1)
     * @param lists
     * @return
     */
    public static ListNode8 mergeKLists(ListNode8[] lists) {
        if (lists.length == 0) {
            return new ListNode8();
        }

        ListNode8 head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            head = mergeList(head, lists[i]);
        }

        return head;
    }

    private static ListNode8 mergeList(ListNode8 list1, ListNode8 list2) {
        ListNode8 dummyHead = new ListNode8(0);
        ListNode8 temp = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }

            temp = temp.next;
        }

        temp.next = list1 != null ? list1 : list2;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        Lists8 obj = new Lists8();
        ListNode8 node1 = new ListNode8(1);
        ListNode8 node2 = new ListNode8(1);
        ListNode8 node3 = new ListNode8(2);

        obj.addNode(4, node1);
        obj.addNode(5, node1);

        obj.addNode(3, node2);
        obj.addNode(4, node2);

        obj.addNode(6, node3);

        ListNode8[] list = new ListNode8[]{null, node1};

        ListNode8 result = mergeKLists(list);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}

class ListNode8 {
    int val;
    ListNode8 next;

    public ListNode8() {

    }

    public ListNode8(int val) {
        this.val = val;
    }

    public ListNode8(int val, ListNode8 next) {
        this.val = val;
        this.next = next;
    }
}

class Lists8 {
    public void addNode(int val, ListNode8 node) {
        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode8(val);
    }
}
