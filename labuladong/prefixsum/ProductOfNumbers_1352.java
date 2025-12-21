package labuladong.prefixsum;

import java.util.ArrayList;
import java.util.List;

/**
 * 最后K个数的乘积（思路经常复习）
 * 难点：每次add数字时，都要更新表格
 * 刚开始不追求一蹴而就，可以先用简单方法，理清思路，后面再做优化
 * 做了1h10min，没做明白！！！
 * 我一开始的思路是后缀乘积数组，把问题搞得很复杂（我先入为主了，一直纠结着后缀积，所以搞不出来）
 * labuladong的思路是，前缀积数组，同时注意处理0的情况
 */
public class ProductOfNumbers_1352 {
    // -------------- labuladong解法 ---------------------
    // 前缀积数组
    List<Integer> preProduct = new ArrayList<>();

    public ProductOfNumbers_1352() {
        // 初始化1，方便后续计算
        preProduct.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            // 如果添加一个0，那么前面的元素积都为零，也就废了，重来 ??? 怎么理解?
            // 来自豆包的解释：如果前缀积中出现0，后续的除法会涉及到除0错误。
            // 遇到0，重置前缀积：后续只要k超过0之后的元素数，乘积必为0
            preProduct.clear();
            preProduct.add(1);
            return;
        }
        int n = preProduct.size();
        // 前缀积数组中每个元素
        preProduct.add(preProduct.get(n - 1) * num);
    }

    public int getProduct(int k) {
        int len = preProduct.size();
        if (k > len - 1) {
            return 0;
        }
        return preProduct.get(len - 1) / preProduct.get(len - 1 - k);
    }

    // -------------- 这是我的解法，没解出来 ---------------------
    // List<Integer> numList;
    // List<Integer> suffixList;
    //
    // public ProductOfNumbers() {
    //     numList = new ArrayList<>();
    //     suffixList = new LinkedList<>();
    //     suffixList.add(1);
    // }
    //
    // public void add(int num) {
    //     suffixList.addFirst(1);
    //     numList.addLast(num);
    //     int len = suffixList.size();
    //     for (int i = len - 2; i >= 0; i--) {
    //         suffixList.set(i, suffixList.get(i) * numList.get(i));
    //     }
    // }
    //
    // public int getProduct(int k) {
    //     return suffixList.get(numList.size() - k);
    // }
    //
    // public static void main(String[] args) {
    //     ProductOfNumbers obj = new ProductOfNumbers();
    //     obj.add(3);
    //     obj.add(0);
    //     obj.add(2);
    //     obj.add(5);
    //     obj.add(4);
    //     int param_2 = obj.getProduct(2);
    //     int param_3 = obj.getProduct(3);
    //     int param_4 = obj.getProduct(4);
    //     obj.add(8);
    //     param_2 = obj.getProduct(2);
    // }
}
