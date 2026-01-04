package labuladong.linkedlist;

/**
 * 合并两个有序链表
 * 独立做出来，用时15min。方法：双指针，分别有一个指针指向两个链表，判断当至少有一个链表还没走到头时的情况
 * 需要有一个虚拟头结点来连接开头的节点（当需要创造一条新链表时，使用虚拟头结点来简化边界情况的处理）
 */
public class MergeTwoLists_21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (p1 != null || p2 != null) {
            int val1 = p1 == null ? 101 : p1.val;
            int val2 = p2 == null ? 101 : p2.val;
            if (val1 <= val2) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
