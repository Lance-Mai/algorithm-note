package labuladong.circulararray;

/**
 * 设计循环队列。注意先进先出，排队，先来后到
 * 注意队头队尾的定义。
 * 只花了 11分30秒
 */
public class MyCircularQueue {
    int size;
    int[] arr;
    int cnt;
    int start, end; // [start, end)

    public MyCircularQueue(int k) {
        arr = new int[k];
        size = k;
        cnt = 0;
        start = 0; // 队头
        end = 0; // 队尾
    }

    // 插入元素。从队尾插入
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        arr[end] = value;
        end = (end + 1) % size;
        cnt++;
        return true;
    }

    // 取出元素。从队头拿出
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        start = (start + 1) % size;
        cnt--;
        return true;
    }

    // 从队首取值
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return arr[start];
    }

    // 从队尾取值
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return arr[(end - 1 + size) % size];
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public boolean isFull() {
        return cnt == size;
    }
}
