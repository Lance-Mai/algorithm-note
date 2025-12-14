package labuladong;


/**
 * 单链表
 * 1、设置虚拟头结点
 */
public class MyLinkedList {
    ListNode dummyHead;
    int size;

    public MyLinkedList() {
        this.dummyHead = new ListNode(-1);
        this.size = 0;
    }

    public int get(int index) {
        // check
        if (size == 0 || index < 0 || index >= size) {
            return -1; // invalid index
        }
        ListNode cur = dummyHead;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        // traverse
        ListNode cur = dummyHead;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        // check
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode newNode = new ListNode(val);
        ListNode cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        // check
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode deleteNode = cur.next;
        cur.next = cur.next.next;
        deleteNode = null;
        size--;

    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
