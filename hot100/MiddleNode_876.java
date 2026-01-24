package hot100;

/**
 * 链表中间节点
 */
public class MiddleNode_876 {
    public ListNode middleNode(ListNode head) {
        ListNode cur = head;
        int cnt = 0;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        int mid = (cnt + 2) / 2;
        cur = head;
        for (int i = 1; i < mid; i++) {
            cur = cur.next;
        }
        return cur;
    }
}
