package hot100;

/**
 * 翻转链表
 * 链表操作注意三个关键：上一个是谁、我是谁、下一个是谁
 */
public class ReverseList_206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
