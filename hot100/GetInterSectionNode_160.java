package hot100;


import java.util.ArrayDeque;

/**
 * 解法1：使用栈结构
 * 解法2（和解法1同为O(n)，但是效率更高一些）：
 * 从题目可知，两条链表从相交到尾巴的部分是一样的，元素相同、数量相同。
 * 因此可以构造两条长度相等的链表。第一条：listA->listB   第二条：listB->listA
 * 首尾相接，同时遍历第一条和第二条，总会有相同的节点的。
 * 如果到最后没有相同节点，都是null，也算是相同节点
 */
public class GetInterSectionNode_160 {
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();
        ListNode cur = headA;
        while (cur != null) {
            stack1.push(cur);
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            stack2.push(cur);
            cur = cur.next;
        }
        ListNode result = null;
        while (!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() == stack2.peek()) {
            result = stack1.pop();
            stack2.pop();
        }
        return result;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }
}
