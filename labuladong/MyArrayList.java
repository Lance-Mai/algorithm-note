package labuladong;

import java.util.NoSuchElementException;

/**
 * 动态数组
 *
 * @param <E> 需要注意的点：
 *            1、增加元素之前没判断要插入的索引是否合法
 *            2、没考虑到快捷的addFirst方法、removeFirst方法
 *            3、扩容和缩容应该用同一个方法，通过入参大小来控制扩/缩，而我之前用的是分开的两个方法
 *            4、删除元素之前，需要先判断索引是否合法
 *            5、删除元素前，先进行缩容判断和操作。删除元素后，记得将删除位置的引用设为null，防止内存溢出
 *            6、注意判断扩缩容的逻辑 size == capacity / 4  ==> size = capacity / 2
 *            7、删除/修改时需要返回删除/修改的元素
 */

@SuppressWarnings("unchecked")
public class MyArrayList<E> {
    private E[] arr;
    private static final int INIT_CAP = 1;
    private int size;

    public MyArrayList(int initialCapacity) {
        this.arr = (E[]) new Object[initialCapacity];
        this.size = 0;
    }

    public MyArrayList() {
        this(INIT_CAP);
    }

    // 1.增
    // 1.1在尾部增加
    private void addLast(E e) {
        int capacity = arr.length;
        // 扩容
        if (size == capacity) {
            resize(2 * capacity);
        }
        arr[size] = e;
        size++;
    }

    // 1.2在中间增加
    private void add(int index, E e) {
        checkPositionIndex(index);

        int capacity = arr.length;
        if (size == capacity) {
            resize(2 * capacity);
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = e;
        size++;
    }

    private void addFirst(E e) {
        add(0, e);
    }

    // 2.删
    // 2.1 删除尾部
    private E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int capacity = arr.length;
        if (size == capacity / 4) {
            // 缩容
            resize(capacity / 2);
        }
        E removeEle = arr[size - 1];
        arr[size - 1] = null; // 防止内存泄露
        size--;

        return removeEle;
    }

    // 2.2 删除中间
    private E remove(int index) {
        checkPositionIndex(index);

        int capacity = arr.length;
        if (size == capacity / 4) {
            // 缩容
            resize(capacity / 2);
        }

        E removeEle = arr[index];

        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;

        return removeEle;
    }

    private void removeFirst() {
        remove(0);
    }

    // 改，返回修改之前的值
    private E set(int index, E e) {
        checkPositionIndex(index);
        E oldEle = arr[index];
        arr[index] = e;
        return oldEle;
    }

    // 查
    private E get(int index) {
        checkElementIndex(index);
        return arr[index];
    }

    // 检查插入索引是否合法
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    // 检查查询索引是否合法
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public static void main(String[] args) {
    }
}
