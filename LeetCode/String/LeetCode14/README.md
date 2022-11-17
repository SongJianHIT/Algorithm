# 14. 最长公共前缀

## 题目描述

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 `""`。

**示例 1：**

```
输入：strs = ["flower","flow","flight"]
输出："fl"
```

**示例 2：**

```
输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
```

**提示：**

- `1 <= strs.length <= 200`
- `0 <= strs[i].length <= 200`
- `strs[i]` 仅由小写英文字母组成

## 分析与解法

> 注意这里是“前缀”，第一次做时没有注意这个，导致一直在想求子串！

发现求的是公共前缀，那就很简单了。我们可以使用纵向扫描。

纵向扫描时，**从前往后遍历所有字符串的每一列**，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。

![image-20221117073945655](https://tva1.sinaimg.cn/large/008vxvgGgy1h87s51bke7j30jv0eajs6.jpg)

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

## 复杂度分析

- 时间复杂度： $O(MN)$，其中 $M$ 为字符串数组中字符串的平均长度， $N$ 为字符串数组中字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
- 空间复杂度：$O(1)$ 。使用的额外空间复杂度为常数。