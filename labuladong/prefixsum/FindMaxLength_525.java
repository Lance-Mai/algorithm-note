package labuladong.prefixsum;

import java.util.HashMap;

/**
 * 连续数组（前缀和与哈希表的结合）
 * 1、前缀和。看了20分钟，还是把问题想复杂了。。。
 * 2、参考labuladong的思路，将0替换为-1，花了1h，捉虫（key和value搞反了，key应该是prefix，value应该是indexList）
 * 但是内存超出限制了，该怎么怎么办呢？ 检查了一下，发现是我将数组的capacity设置为n/2，因此超出限制。将该阈值设置为默认(16)后问题解决。
 * 全部通过。但是该解法用时较长，为什么呢？我分析问题出在List的排序上。排序最快也是nlogn。
 * 如何快速从一个数组中得到其最大元素和最小元素的差值。
 * 我分析发现，其实map中存的value即list，天然就是一个有序数组，不用在排序啦。不排序后提速了，但还是不够。
 * 最终解决方案：凭借着indexList的天然升序的特点，我在遍历数组nums组装prefix过程中，就会进行maxLen的比较。最终性能打败了90%用户
 * 更新：prefix可以作为一个单int变量，而不用成为一个数组 ==> 优化内存消耗，从击败5%提升到击败41%
 * 更新：map中的value可以是一个变量，而不是数组，又可以优化内存消耗 ==> 很奇怪，内存消耗反而多了，不过性能更强了
 */
public class FindMaxLength_525 {
    public int findMaxLength(int[] nums) {
        // 为了方便计算，将0替换为-1
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        // 使用map记录相同前缀和的下标
        HashMap<Integer, Integer> prefixMapIndex = new HashMap<>();
        // int[] prefix = new int[n + 1];
        int prefix = 0;
        Integer index = 0;
        prefixMapIndex.put(prefix, index);
        int maxLen = 0;
        for (int i = 1; i <= n; i++) {
            prefix = prefix + nums[i - 1];
            Integer indexVal = prefixMapIndex.get(prefix);
            if (indexVal == null) {
                prefixMapIndex.put(prefix, i);
            } else {
                maxLen = Math.max(maxLen, i - indexVal);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        int maxLength = new FindMaxLength_525().findMaxLength(nums);
        System.out.println(maxLength);
    }
}
