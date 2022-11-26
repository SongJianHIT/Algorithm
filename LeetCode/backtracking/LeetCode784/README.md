# 784. 字母大小写全排列

## 题目描述

给定一个字符串 `s` ，通过将字符串 `s` 中的每个字母转变大小写，我们可以获得一个新的字符串。

返回 *所有可能得到的字符串集合* 。以 **任意顺序** 返回输出。

**示例 1：**

```
输入：s = "a1b2"
输出：["a1b2", "a1B2", "A1b2", "A1B2"]
```

**示例 2:**

```
输入: s = "3z4"
输出: ["3z4","3Z4"] 
```

**提示:**

- `1 <= s.length <= 12`
- `s` 由小写英文字母、大写英文字母和数字组成

## 分析与思路

数据范围为 12，同时要我们求所有具体方案，容易想到使用 `DFS` 进行**「爆搜」**。

我们可以从前往后考虑每个 s[i] ，根据 s[i] 是否为字母进行分情况讨论：

- 若 s[i] 不是字母，直接保留
- 若 s[i] 是字母，则有**「保留原字母」**或**「进行大小写转换」**两种决策

设计 DFS 函数为 `void dfs(int idx, int n, String cur)` ：其中 n 固定为具体方案的长度（即原字符串长度），而 idx 和 cur 分别为当前处理到哪一个 s[idx]，而 cur 则是当前具体方案。

根据上述分析可知，当 s[idx] 不为字母，将其直接追加到 cur 上，并决策下一个位置 idx+1；而当 s[idx] 为字母时，我们可以选择 **将 s[idx] 追加到 cur 上（保留原字母）** 或是 **将 s[idx] 进行翻转后再追加到 cur 上（进行大小写转换）** 。

最后当我们满足 `idx = n` 时，说明已经对原字符串的每一位置决策完成，将当前具体方案 `cur` 加入答案。

> 【小技巧】**可以通过与 `32` 异或来进行大小写转换！！**
>
> 1. 大写字母 A~Z 的 ASCII 码范围为 [65, 90]；
> 2. 小写字母 a~z 的 ASCII 码范围为 [97, 122]；
> 3. 65 + 32 = 01000001 + 00100000 = 01100001，即 97。 总结：
> 4. 大变小，小变大（ 大写变小写、小写变大写 ）: 字符 ^= 32
> 5. 大变小 （大写变小写、小写变小写 ）: 字符 |= 32
> 6. 小变大 （小写变大写、大写变大写 ）: 字符 &= -33
>
> 【小技巧】判断当前字符为 字母 的方法：`Character.isLetter(char ch)`

```java
class Solution {
    char[] cs;
    List<String> ans = new ArrayList<>();
    public List<String> letterCasePermutation(String s) {
        cs = s.toCharArray();
        dfs(0, s.length(), new char[s.length()]);
        return ans;
    }
    void dfs(int idx, int n, char[] cur) {
        if (idx == n) {
            ans.add(String.valueOf(cur));
            return ;
        }
        cur[idx] = cs[idx];
        dfs(idx + 1, n, cur);
        if (Character.isLetter(cs[idx])) {
            cur[idx] = (char) (cs[idx] ^ 32);
            dfs(idx + 1, n, cur);
        }
    }
}
```

**时间复杂度**：最坏情况下原串 s 的每一位均为字母（均有保留和转换两种决策），此时具体方案数量共 $2^n$ 种；同时每一种具体方案我们都需要用与原串长度相同的复杂度来进行构造。复杂度为 $O(n \times 2^n)$ 
**空间复杂度**：$O(n \times 2^n)$

