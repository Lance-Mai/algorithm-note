package labuladong.prefixsum;

/**
 * 寻找数据中心下标，其实就是使用前缀和
 * 错题笔记：16行的异常情况排除，注意条件
 * 注意：没有完全搞清楚 原数组和前缀和数组的下标关系，需要在纸上画一画
 * 这道题花了25分钟做完，调试花了1h，都是在前缀和下标的问题犯错
 * 关键点：不要一开始就考虑到特例，如n=1，先从普通情况开始考虑，再考虑边界值，不然容易把问题搞复杂
 */
public class PivotIndex_724 {
    public int pivotIndex(int[] nums) {
        int[] preSum = getPreSum(nums);
        for (int i = 0; i < nums.length; i++) {
            // compute left and right
            int leftX = 0;
            int leftY = Math.max(leftX, i - 1);
            int rightY = nums.length - 1;
            int rightX = Math.min(rightY, i + 1);
            int leftVal, rightVal;
            // judge if it's at end
            if (leftX == leftY) {
                leftVal = 0;
                rightVal = preSum[preSum.length - 1] - preSum[1];
                if (leftVal == rightVal) {
                    return i;
                }
            }
            if (rightX == rightY) {
                leftVal = preSum[preSum.length - 2];
                rightVal = nums[nums.length - 1];
                if (leftVal == rightVal) {
                    return i;
                }
            }
            // general condition
            leftVal = preSum[i];
            rightVal = preSum[preSum.length - 1] - preSum[i + 1];
            if (leftVal == rightVal) {
                return i;
            }
        }
        return -1;
    }

    private int[] getPreSum(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return preSum;
    }

    // labuladong解法
    public int pivotIndex_labudadong(int[] nums) {
        int n = nums.length;
        // 获取前缀和
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 根据前缀和判断左半边数组和右半边数组的元素和是否相同
        // 为什么i从1开始，而不是0开始：因为preSum的1就相当于nums的0下标
        for (int i = 1; i < preSum.length; i++) {
            int leftSum = preSum[i - 1] - preSum[0];
            int rightSum = preSum[n] - preSum[i];
            if (leftSum == rightSum) {
                // 相对nums数组，preSum数组有一位索引偏移（+1）
                return i - 1;
            }
        }
        return -1;
    }

    // 我对比labuladong解法和画图思考后第二遍做
    public int pivotIndex_secondTry(int[] nums) {
        int[] preSum = getPreSum(nums);
        int len = nums.length;
        for (int i = 1; i <= len; i++) {
            int leftSum = preSum[i - 1];
            int rightSum = preSum[len] - preSum[i];
            if (leftSum == rightSum) {
                return i - 1;
            }
        }
        return -1;
    }
}
