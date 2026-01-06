package labuladong.linkedlist;

import java.util.PriorityQueue;

/**
 * 合并k个升序数组
 * 1、使用小顶堆。20分钟做完。有点小问题：最后一个节点需要断开与其他节点的连接，否则会产生循环链表
 * 2、labuladong思路：其实是一样的，使用到优先级队列，但是有所改进
 * 使用优先级队列，把所有链表的头节点放入一个最小堆中，每次获得k（链表个数）个节点中的最小节点
 * 拿出来最小节点以后，如果该节点还有下一个节点（p.next != null），则将下一个节点offer到小顶堆中
 * 复杂度分析：每次小顶堆排序都是基于最多k个元素排序，时间复杂度为logk，一个链表长度最大为N，
 * 则最终复杂度为 N*logk
 */
public class MergeKLists_23 {
    // labudadong解法
    public ListNode mergeKLists(ListNode[] lists) {
        // 创建小顶堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length,
                (o1, o2) -> o1.val - o2.val);
        // 将各链表头加入到小顶堆中
        for (ListNode head : lists) {
            if (head != null) {
                priorityQueue.offer(head);
            }
        }
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (!priorityQueue.isEmpty()) {
            ListNode polled = priorityQueue.poll();
            cur.next = polled;
            cur = cur.next;
            if (polled.next != null) {
                priorityQueue.offer(polled.next);
            }
        }
        return dummyHead.next;
    }

    // public ListNode mergeKLists(ListNode[] lists) {
    //     PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
    //     for (ListNode head : lists) {
    //         // 遍历链表
    //         traverseList(head, priorityQueue);
    //     }
    //     ListNode dummyHead = new ListNode();
    //     ListNode cur = dummyHead;
    //     ListNode tail = new ListNode();
    //     while (!priorityQueue.isEmpty()) {
    //         ListNode polled = priorityQueue.poll();
    //         tail = polled;
    //         cur.next = polled;
    //         cur = cur.next;
    //     }
    //     // 断开尾节点的链条
    //     tail.next = null;
    //     return dummyHead.next;
    // }
    //
    // private void traverseList(ListNode head, PriorityQueue<ListNode> priorityQueue) {
    //     while (head != null) {
    //         priorityQueue.add(head);
    //         head = head.next;
    //     }
    // }
}
