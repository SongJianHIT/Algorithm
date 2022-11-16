# LeeetCode12：整数转罗马数字

[TOC]

## 题目描述

> https://leetcode.cn/problems/integer-to-roman/description/

罗马数字包含以下七种字符： `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 `II` ，即为两个并列的 1。12 写做 `XII` ，即为 `X` + `II` 。 27 写做 `XXVII`, 即为 `XX` + `V` + `II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在 `V`  (5) 和 `X`  (10) 的左边，来表示 4 和 9。
- `X` 可以放在 `L`  (50) 和 `C`  (100) 的左边，来表示 40 和 90。 
- `C` 可以放在 `D`  (500) 和 `M`  (1000) 的左边，来表示 400 和 900。

给你一个整数，将其转为罗马数字。

**示例 1:**

```
输入: num = 3
输出: "III"
```

**示例 2:**

```
输入: num = 4
输出: "IV"
```

**示例 3:**

```
输入: num = 9
输出: "IX"
```

**示例 4:**

```
输入: num = 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
```

**示例 5:**

```
输入: num = 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

**提示：**

- `1 <= num <= 3999`

## 思路与分析

> 看到这个题，第一思路是直接 Map 上匹配，于是就上当了！Map 是无序的，就没有办法按大小顺序遍历。

我们需要先分析，如何表示一个数？

罗马数字的唯一表示法

让我们从一个例子入手。考虑 140140140 的罗马数字表示，下面哪一个是正确的？

![image-20221116101039251](https://tva1.sinaimg.cn/large/008vxvgGgy1h86qvp7cfij30pa0d4aaw.jpg)

我们用来确定罗马数字的规则是： **对于罗马数字从左到右的每一位，选择尽可能大的符号值** 。对于 140，最大可以选择的符号值为 C=100。接下来，对于剩余的数字 40，最大可以选择的符号值为 XL=40。因此，140 的对应的罗马数字为 C + XL = CXL。

> 所以，如果一开始就想从低位（个位）往高位求余，就错误了！

既然需要从高位开始，定义好整型数组和字符串数组，遍历即可。

```java
class Solution {

    private int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private String[] str = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", 
                            "D", "CM", "M"};
    
    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i =  values.length - 1; i >= 0; i--) {
            while (num >= values[i]) {
                sb.append(str[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }
}
```

复杂度分析：

- 时间复杂度： $O(N)$
- 空间复杂度： $O(1)$

## REFERENCE

1. https://leetcode.cn/problems/integer-to-roman/solutions/774611/zheng-shu-zhuan-luo-ma-shu-zi-by-leetcod-75rs/