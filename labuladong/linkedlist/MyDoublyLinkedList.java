package labuladong.linkedlist;

/**
 * 双链表
 * 虚拟头尾节点
 * 要点：
 * 1、需要注意边界值检查，add 和 其他操作的边界值不一样，add边界+1
 * 2、遍历的时候，需要确定是遍历到该目标本身还是遍历到该目标前/后一个元素
 */
public class MyDoublyLinkedList {
    // 虚拟头尾节点引用不会改变，因此是final
    final private Node dummyHead, dummyTail;
    private int size;


    // 双链表节点
    private static class Node {
        int val;
        Node next;
        Node pre;

        Node(int val) {
            this.val = val;
        }
    }

    // 构造函数初始化虚拟头尾节点
    public MyDoublyLinkedList() {
        this.size = 0;
        this.dummyHead = new Node(-1);
        this.dummyTail = new Node(-1);
        // link
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }


    // ***** 增 *****
    public void addLast(int val) {
        Node newNode = new Node(val);
        newNode.next = dummyTail;
        newNode.pre = dummyTail.pre;
        // insert
        dummyTail.pre.next = newNode;
        dummyTail.pre = newNode;
        size++;
    }

    public void addFirst(int val) {
        Node newNode = new Node(val);
        newNode.next = dummyHead.next;
        newNode.pre = dummyHead;
        // insert
        dummyHead.next.pre = newNode;
        dummyHead.next = newNode;
        size++;
    }

    // 不考虑性能问题，一律从头数
    public void add(int index, int val) {
        // check
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        // new
        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node newNode = new Node(val);
        newNode.next = cur.next;
        newNode.pre = cur;

        // link
        cur.next.pre = newNode;
        cur.next = newNode;
        size++;

    }

    // ***** 删 *****
    public int removeFirst() {
        // check
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node deleteNode = dummyHead.next;
        deleteNode.next.pre = dummyHead;
        dummyHead.next = deleteNode.next;
        int deleteVal = deleteNode.val;
        deleteNode = null;
        size--;
        return deleteVal;
    }

    public int removeLast() {
        // check
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node deleteNode = dummyTail.pre;
        deleteNode.pre.next = dummyTail;
        dummyTail.pre = deleteNode.pre;
        int deleteVal = deleteNode.val;
        deleteNode = null;
        size--;
        return deleteVal;
    }

    // start from head
    public int remove(int index) {
        // check
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = dummyHead;
        // look for the node to remove
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        cur.next.pre = cur.pre;
        cur.pre.next = cur.next;
        Node deleteNode = cur;
        int deleteVal = deleteNode.val;
        deleteNode = null;
        size--;
        return deleteVal;
    }

    // ***** 查 *****
    // start from head
    public int get(int index) {
        // check
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = dummyHead;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public int getFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return dummyHead.next.val;
    }

    public int getLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return dummyTail.pre.val;
    }

    // ***** 改 *****
    public int set(int index, int val) {
        // check
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = dummyHead;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        int oldVal = cur.val;
        cur.val = val;
        return oldVal;
    }


    private Node getNode(int index) {
        // check
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = dummyHead;
        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        return cur;
    }


    private void display() {

    }

    public static void main(String[] args) {

    }
}
