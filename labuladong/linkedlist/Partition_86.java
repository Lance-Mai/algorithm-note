package labuladong.linkedlist;

/**
 * 分隔链表。双指针，花了40分钟。
 * 需要注意一点：右链表在最后rightCur记得断链 rightCur.next = null，防止出现循环链表
 * <p>
 * 总的来说，如果我们需要把原链表的节点接到新链表上，而不是 new 新节点来组成新链表的话，
 * 那么断开节点和原链表之间的链接可能是必要的。那其实我们可以养成一个好习惯，但凡遇到这种情况，
 * 就把原链表的节点断开，这样就不会出错了
 */
public class Partition_86 {
    public ListNode partition(ListNode head, int x) {
        ListNode leftDummyHead = new ListNode();
        ListNode rightDummyHead = new ListNode();
        ListNode leftCur = leftDummyHead;
        ListNode rightCur = rightDummyHead;
        while (head != null) {
            if (head.val < x) {
                leftCur.next = head;
                leftCur = leftCur.next;
            } else {
                rightCur.next = head;
                rightCur = rightCur.next;
            }
            head = head.next;
        }
        // 右链表断链，防止出现循环链表
        rightCur.next = null;
        // 拼接左右链表
        leftCur.next = rightDummyHead.next;

        return leftDummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 5, 2};
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        for (int i : arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        ListNode partition = new Partition_86().partition(dummyHead.next, 3);
        System.out.println(partition);
    }
}
