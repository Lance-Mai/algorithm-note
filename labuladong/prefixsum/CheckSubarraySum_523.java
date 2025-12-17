package labuladong.prefixsum;

import java.util.HashMap;

/**
 * 连续的子数组和
 * 1、双指针 + 前缀和  ==》 行不行。想了20分钟，没有思路，不知道如何快速得到长度不确定的连续子数组的和。
 * 2、参考labuladong的解法：这道题和 525. 连续数组 非常相似，都是考察
 * 前缀和技巧 和哈希表的结合使用。
 * 本题让你寻找长度至少为 2 且和为 k 的倍数的子数组，翻译一下就是：
 * 寻找 i, j 使得 (preSum[i] - preSum[j]) % k == 0 且 i - j >= 2。
 * 另外，(preSum[i] - preSum[j]) % k == 0 其实就是 preSum[i] % k == preSum[j] % k。
 * 所以我们使用一个哈希表，记录 preSum[j] % k 的值以及对应的索引，就可以迅速判断 preSum[i] 是否符合条件了。
 * <p>
 * --- 不得不说，这太妙了。我怎么想不到  ==> 知道思路后，10分钟搞定
 * -- 内存消耗比较多，为什么？
 */
public class CheckSubarraySum_523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        // hash 记录prefix[i]%k的值及其索引
        HashMap<Integer, Integer> prefixModMapIndex = new HashMap<>();
        int prefix = 0;
        int index = 0;
        prefixModMapIndex.put(0, index);
        for (int i = 1; i <= n; i++) {
            prefix = prefix + nums[i - 1];
            int mod = prefix % k;
            Integer indexVal = prefixModMapIndex.get(mod);
            if (indexVal == null) {
                indexVal = i;
                prefixModMapIndex.put(mod, indexVal);
            } else if (i - indexVal >= 2) {
                return true;
            }
        }
        return false;
    }
}
