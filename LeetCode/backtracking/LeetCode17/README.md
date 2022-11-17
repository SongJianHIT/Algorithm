# 17. 电话号码的字母组合

## 题目描述

给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按 **任意顺序** 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h88d3pbf6tj305k04i3yh.jpg)

**示例 1：**

```
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

**示例 2：**

```
输入：digits = ""
输出：[]
```

**示例 3：**

```
输入：digits = "2"
输出：["a","b","c"]
```

**提示：**

- `0 <= digits.length <= 4`
- `digits[i]` 是范围 `['2', '9']` 的一个数字。

## 思路与分析

本题的考点是 **回溯算法** ！

> 从示例上来说，输入"23"，最直接的想法就是两层 for 循环遍历了吧，正好把组合的情况都输出了。
>
> 如果输入 "233" 呢，那么就三层 for 循环，如果 "2333" 呢，就四层for循环.......
>
> 这 for 循环的层数如何写出来，此时又是 **回溯法** 登场的时候了。

![IMG_0DCCF9E4B668-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h88d4j4y7tj31jq0u0tf5.jpg)

### 回溯第一步：确定回溯函数参数

首先需要一个字符串 `s` 来收集叶子节点的结果，然后用一个字符串数组 `result` 保存起来，这两个变量可定义为全局。再来看参数，参数指定是有题目中给的 `String digits` ，然后还要有一个参数就是 `int` 型的 `index`。这个 `index` 是 **记录遍历第几个数字了**，就是用来遍历 `digits` 的（题目中给出数字字符串），同时 `index` 也表示树的深度。

```java
vector<string> result;
string s;
void backtracking(String digits, int index)
```

### 回溯第二步：确定终止条件

例如输入用例 "23" ，两个数字，那么根节点往下递归两层就可以了，**叶子节点就是要收集的结果集** 。

那么终止条件就是如果 `index` 等于输入的数字个数（ `digits.size` ）了（ `index` 本来就是用来遍历 `digits` 的）。然后收集结果，结束本层递归。

```java
if (index == digits.size()) {
    result.push_back(s);
    return;
}
```

### 回溯第三步：确定单层遍历逻辑

首先要取 `index` 指向的数字，并找到对应的字符集（手机键盘的字符集）。然后 `for` 循环来处理这个字符集，代码如下：

```java
// str 表示当前num所对应的字符集合
String str = letterMap[digits.charAt(index) - '0'];
// 遍历字符集合
for (int i = 0; i < str.length(); i++) {
  tmp.append(str.charAt(i));
  backtracking(digits, index + 1);
  // 删除 tmp 末尾继续尝试
  tmp.deleteCharAt(tmp.length() - 1);
}
```

**时间复杂度**： $O(3^M \times 4^N)$ ，其中 $M$ 是对应三个字母的数字个数，$N$ 是对应四个字母的数字个数。

**空间复杂度**： $O(3^M \times 4^N)$ 。一共要生成 $3^M \times 4^N$个结果。

## 代码

```java
class Solution {
    // 存储结果
    private List<String> res = new ArrayList<>();
        // 数字、字符表
    private String[] letterMap = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        // 回溯处理
        backtracking(digits, 0);
        return res;
    }

    // 每次迭代获取一个字符串，所以会设计大量的字符串拼接，
    // 所以这里选择更为高效的 StringBuild
    private StringBuilder tmp = new StringBuilder();
    // 回溯方法
    public void backtracking(String digits, int index) {
        // 终止递归条件
        if (index == digits.length()) {
            // 递归到底（叶子节点），就表明已经生成了一个子结果
            res.add(tmp.toString());
            return;
        }
        // str 表示当前num所对应的字符集合
        String str = letterMap[digits.charAt(index) - '0'];
        // 遍历字符集合
        for (int i = 0; i < str.length(); i++) {
            tmp.append(str.charAt(i));
            backtracking(digits, index + 1);
            // 删除 tmp 末尾继续尝试
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }
}
```

## REFERENCE

代码随想录：https://leetcode.cn/u/carlsun-2/





