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

    // 静态内部类，可以让ListNode与其外部类MyLinkedList解绑定，相互独立，即逻辑上有归属但是相互独立，想创建多少个ListNode实例都可以
    // 当static修饰方法或变量时，方法/变量则变成该类的所有实例共享
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
