package hot100;

import java.util.ArrayDeque;

/**
 * 回文链表
 * 解法1：栈（或数组）
 * 注意点：比较的是值，不是地址
 * 我这个解法 如果数量级为n，我的时间复杂度为 O(n)，空间复杂度 O(n)
 * 解法2：可以做到时间复杂度为 O(n)，空间复杂度 1
 * 注意：找到中间节点以后，为简单起见，反转右边的链表会更简单一些
 * 解法2总结：结合查找链表中间节点、反转链表（注意是反转右边链表简单一些），还有注意边界值的检查
 */
public class IsPalindrome_234 {
    // 解法1
    public boolean isPalindrome1(ListNode head) {
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 解法2 空间复杂度O(1)，在原数组上进行操作
    public boolean isPalindrome(ListNode head) {
        // 找到中间节点或者中间偏右边的节点
        ListNode midNode = getMiddleNode(head);

        // 翻转右边的链表
        ListNode rightHead = reverseList(midNode);

        // 以中间节点开头，遍历和比较
        ListNode curL = head;
        ListNode curR = rightHead;
        while (curR != null) {
            if (curR.val != curL.val) {
                return false;
            }
            if (curR == midNode) {
                return true;
            }
            curL = curL.next;
            curR = curR.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode midNode) {
        ListNode pre = null;
        ListNode cur = midNode;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private ListNode getMiddleNode(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int mid = (size + 2) / 2;
        cur = head;
        for (int i = 1; i < mid; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static void main(String[] args) {
        ListNode head = CommonUtils.generateList(new int[]{1, 2});
        System.out.println(new IsPalindrome_234().isPalindrome(head));
    }
}
