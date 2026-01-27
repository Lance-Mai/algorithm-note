package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组
 * 解法1：暴力遍历 hash+排序。 m * nlogn。注意更新map
 * 解法2：将char转换成数组（仅包含小写字母，26个小写字母连续的特点），然后再将数组转换成char拼接在一起形成str
 */
public class GroupAnagrams_49 {
    // 解法2：string转换成数组
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> strMapList = new HashMap<>();
        for (String str : strs) {
            int[] charCnt = new int[26];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // 'a'为0，'b'为1，依此类推
                charCnt[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < charCnt.length; i++) {
                int cnt = charCnt[i];
                char curChar = (char) (i + 'a');
                sb.append(curChar * cnt);
            }
            String curStr = sb.toString();
            List<String> list = strMapList.getOrDefault(curStr, new ArrayList<>());
            list.add(str);
            strMapList.put(curStr, list);
        }
        return new ArrayList<>(strMapList.values());
    }

    // 解法1：hashmap+排序
    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, List<String>> strMapList = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            // StringBuilder sb = new StringBuilder();
            // for (char c : charArray) {
            //     sb.append(c);
            // }
            // String sortedStr = sb.toString();
            // 快速将char[] 转换为string
            String sortedStr = Arrays.toString(charArray);
            List<String> list = strMapList.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            strMapList.put(sortedStr, list);
        }
        return strMapList.values().stream().toList();
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new GroupAnagrams_49().groupAnagrams(strs));
    }
}
