package labuladong.stackandqueue;

import java.util.LinkedList;

/**
 * 用栈实现队列。15分钟解决，分别用两个LinkedList来模拟， pushStack、popStack
 */
public class MyQueue_232 {
    LinkedList<Integer> pushStack, popStack;
    int size;

    public MyQueue_232() {
        pushStack = new LinkedList<>();
        popStack = new LinkedList<>();
        size = 0;
    }

    public void push(int x) {
        while (!popStack.isEmpty()) {
            pushStack.push(popStack.pop());
        }
        pushStack.push(x);
        size++;
    }

    public int pop() {
        this.peek();
        size--;
        return popStack.pop();
    }

    public int peek() {
        if (this.empty()) {
            return -1; // 表示元素不存在
        }
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
        return popStack.peek() == null ? -1 : popStack.peek();
    }

    public boolean empty() {
        return size == 0;
    }
}
