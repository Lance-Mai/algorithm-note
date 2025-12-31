package labuladong.stackandqueue;

import java.util.LinkedList;

/**
 * 重排链表。
 * 1、根据原链表A创建一条方向相反的链表B
 * 2、A0 -> B0 -> A1 -> B1 ....
 * 花了50分钟，太慢了，只击败了3%。内存消耗也大。需要优化
 * 出了两个问题：1）正反两个数组，都装了一个null  2）在组装链表时，最后一个节点忘了断开，导致循环链表出现
 * 优化1：链表改为数组 => 性能没啥变化
 * 优化2：去掉HashSet，性能还是没有多大变化
 * 看labuladong解法：这道题难点
 */
public class ReorderList_143 {
    public void reorderList(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        LinkedList<ListNode> forwardList = new LinkedList<>();
        LinkedList<ListNode> backwardList = new LinkedList<>();

        ListNode cur = dummyHead;
        while (cur != null) {
            cur = cur.next;
            if (cur != null) {
                forwardList.addLast(cur);
                backwardList.addFirst(cur);
            }
        }
        int size = forwardList.size();
        dummyHead = new ListNode();
        cur = dummyHead;
        for (int i = 0; i < size; i++) {
            ListNode forwardNode = forwardList.get(i);
            ListNode backwardNode = backwardList.get(i);
            if (forwardNode == backwardNode) {
                cur.next = forwardNode;
                cur.next.next = null;
                break;
            } else if (forwardNode == cur) {
                cur.next = null;
                break;
            } else {
                cur.next = forwardNode;
                cur = cur.next;
                cur.next = backwardNode;
                cur = cur.next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        new ReorderList_143().reorderList(node1);
    }
}


