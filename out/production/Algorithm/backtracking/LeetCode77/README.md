# 77. 组合

## 题目描述

给定两个整数 `n` 和 `k`，返回范围 `[1, n]` 中所有可能的 `k` 个数的组合。

你可以按 **任何顺序** 返回答案。

**示例 1：**

```
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

**示例 2：**

```
输入：n = 1, k = 1
输出：[[1]]
```

**提示：**

- `1 <= n <= 20`
- `1 <= k <= n`

## 分析与思路

本题考查的是 **回溯法** ！

### 第一步：递归函数的返回值与参数

首先创建两个全局变量，一个用于保存当前临时结果，另一个用于存储返回结果：

```java
List<List<Integer>> res = new ArrayList<List<Integer>>();
List<Integer> tmp = new ArrayList<>();
```

函数里一定有两个参数，既然是集合 n 里面取 k 的数，那么 n 和 k 是两个 int 型的参数。

然后还需要一个参数，为int型变量 startIndex，这个参数用来记录本层递归的中，集合从哪里开始遍历（集合就是`[1,...,n]`  ）。

为什么要有这个 startIndex 呢？ **每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠 startIndex**。

### 第二步：递归函数的终止条件

当 tmp 这个数组的大小如果达到 k，说明我们找到了一个子集大小为 k 的组合了，可以保存结果。

```java
if (k == tmp.size()) {
  List<Integer> temp = new ArrayList<>();
  for(int i = 0 ; i < k ;i++){
    temp.add(tmp.get(i));
  }
  res.add(temp);
  return;
}
```

### 第三步：单层的搜索过程

递归是负责深度搜索的（也就是结果长度增加的过程），而外面的 for 的单层循环，负责广度搜索，即相同长度上的有多少种可能！

```java
// 控制树的横向遍历
for (int i = startIndex; i <= n; i++) {
  // 处理节点
  tmp.add(i);
  // 递归：控制树的纵向遍历，注意下一层搜索要从 i+1 开始
  backtracking(n, k, i + 1);
  tmp.remove(tmp.size() - 1);
}
```

## 代码

```java
class Solution {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> tmp = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        if (n < 1 || k > n) return res;
        backtracking(n, k, 1);
        
        return res;
    }
    
    // 回溯方法
    public void backtracking(int n, int k, int startIndex) {
        if (k == tmp.size()) {
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ; i < k ;i++){
                temp.add(tmp.get(i));
            }
            res.add(temp);
            return;
        }
        // 控制树的横向遍历
        for (int i = startIndex; i <= n; i++) {
            // 处理节点
            tmp.add(i);
            // 递归：控制树的纵向遍历，注意下一层搜索要从 i+1 开始
            backtracking(n, k, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

