package hot100;

public class CommonUtils {
    public static ListNode generateList(int[] ints) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i : ints) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
