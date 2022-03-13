package leetcode;

import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-03-12 16:46
 *
 * https://leetcode-cn.com/problems/sort-list/
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 题解：https://leetcode-cn.com/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
 *
 */
public class SortList {

    // 方法一 start
    private static Random RANDOM = new Random();

    /**
     * 时间复杂度：O(nlogn) 快排
     * 空间复杂度:O(n) 额外使用了数组排序
     *
     * 不过很耗时。。。
     *
     * @param head
     * @return
     */
    public static ListNode7 sortList(ListNode7 head) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = getListLength(head);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = head.val;
            head = head.next;
        }

        sortArray(arr);

        ListNode7 newNode = new ListNode7(arr[0]);
        Lists7 obj = new Lists7();

        for (int j = 1; j < arr.length; j++) {
            obj.addNode(newNode, arr[j]);
        }

        while (newNode != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }

        return newNode;
    }

    private static void sortArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        sortArrayByQuickSort(arr, left, right);
    }

    private static void sortArrayByQuickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pIndex = getPartition(arr, left, right);
        sortArrayByQuickSort(arr, left, pIndex - 1);
        sortArrayByQuickSort(arr, pIndex + 1, right);
    }

    private static int getPartition(int[] arr, int left, int right) {
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(arr, left, randomIndex);

        int lt = left;
        int pivot = arr[left];

        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                lt++;
                swap(arr, i, lt);
            }
        }

        swap(arr, left, lt);
        return lt;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        if (idx1 == idx2) {
            return;
        }

        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private static int getListLength(ListNode7 head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }

        return len;
    }

    // 方法一 end

    // 方法二 start

    /**
     * 自顶而下归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn）使用了递归
     * @param head
     * @return
     */
    public static ListNode7 sortListFromUpToDown(ListNode7 head) {
        return sortListFromUpToDown(head, null);
    }

    /**
     * 手动debug下
     * @param head
     * @param tail
     * @return
     */
    private static ListNode7 sortListFromUpToDown(ListNode7 head, ListNode7 tail) {
        if (head == null) {
            return head;
        }

        if (head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode7 slow = head;
        ListNode7 fast = head;

        while (fast != tail) {
            slow = slow.next;
            // 写成 fast = fast.next.next 会抛空异常
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode7 mid = slow;
        // 通过递归将链表分解（先分解成一个个node，然后合并成有序的子链表)，然后合并
        ListNode7 l1 = sortListFromUpToDown(head, mid);
        ListNode7 l2 = sortListFromUpToDown(mid, tail);
        ListNode7 sorted = merge(l1, l2);
        return sorted;
    }

    private static ListNode7 merge(ListNode7 l1, ListNode7 l2) {
        ListNode7 dummyHead = new ListNode7(0);
        ListNode7 temp = dummyHead;
        ListNode7 temp1 = l1;
        ListNode7 temp2 = l2;

        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }

            temp = temp.next;
        }

        if (temp1 != null) {
            temp.next = temp1;

        } else if (temp2 != null) {
            temp.next = temp2;
        }

        return dummyHead.next;
    }
    // 方法二 end

    /**
     * 自底而上
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1）
     *
     * 按照subLength一步步拆分node，然后合并起来
     *
     * 一定要debug
     *
     * @param head
     * @return
     */
    public static ListNode7 sortListFromDownToUp(ListNode7 head) {
        if (head == null) {
            return head;
        }

        int len = getListLength(head);

        ListNode7 dummyHead = new ListNode7(0, head);
        // 每次将链表拆分成若干个长度为subLen的子链表 , 并按照每两个子链表一组进行合并
        for (int subLength = 1; subLength < len; subLength <<= 1) {
            ListNode7 prev = dummyHead;
            // curr用于记录拆分链表的位置
            ListNode7 curr = dummyHead.next;

            while (curr != null) {
                // 拆分subLen长度的链表1
                // 第一个链表的头 即 curr 初始的位置
                ListNode7 head1 = curr;
                /**
                 * i = 1，也就是单个node的时候，不用循环，单个node的时候就是有序的了
                 */
                // 拆分出长度为subLen的链表1
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 拆分subLen长度的链表2
                // 第二个链表的头  即 链表1 尾部的下一个位置
                ListNode7 head2 = curr.next;
                // 断开第一个链表和第二个链表的链接
                curr.next = null;
                // 第二个链表头 重新赋值给curr
                curr = head2;

                /**
                 * i = 1，也就是单个node的时候，不用循环，单个node的时候就是有序的了
                 */
                // 再拆分出长度为subLen的链表2
                for (int i = 1; i< subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 再次断开 第二个链表最后的next的链接
                ListNode7 next = null;
                if (curr != null) {
                    // next用于记录 拆分完两个链表的结束位置
                    next = curr.next;
                    // 断开连接 (自此 head1 head2 为独立的node，其next均为null)
                    curr.next = null;
                }

                // 合并两个subLen长度的有序链表
                ListNode7 merged = merge(head1, head2);

                // prev.next 指向排好序链表的头
                prev.next = merged;
                // 这个循环是为了改变dummyHead
                while (prev.next != null) {
                    prev = prev.next;
                }
                // next用于记录 拆分完两个链表的结束位置 (进行下一次排序)
                curr = next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        Lists7 obj = new Lists7();
        ListNode7 node7 = new ListNode7(-1);
        obj.addNode(node7, 5);
        obj.addNode(node7, 3);
        obj.addNode(node7, 4);
        obj.addNode(node7, 0);

        sortListFromDownToUp(node7);

        while (node7 != null) {
            System.out.println(node7.val);
            node7 = node7.next;
        }
    }
}

class ListNode7 {
    int val;
    ListNode7 next;

    public ListNode7() {

    }

    public ListNode7(int val) {
        this.val = val;
    }

    public ListNode7(int val, ListNode7 next) {
        this.val = val;
        this.next = next;
    }
}

class Lists7 {
    public void addNode(ListNode7 node, int val) {
        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode7(val);
    }
}
