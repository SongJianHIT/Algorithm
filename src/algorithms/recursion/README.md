# 递归

## 1 递归的概述与模版

> 任何递归代码，都可以改成非递归的形式。

递归：**将大问题拆解为一系列多个重复的子问题，然后分别处理子问题，汇总结果即可处理完大问题**。

递归本质：系统创建一系列栈，每次重复调用该函数就会将该待处理的程序压入栈。

![image-20221114213954231](https://tva1.sinaimg.cn/large/008vxvgGgy1h84zkahpeqj30q30eyabo.jpg)

### 递归的时间复杂度

Master 公式用来计算 **子问题规模确定（一致）**的递归函数的时间复杂度。

形如 $T(N) = a * T(N/b) + O(N^d)$ （其中的 $a、b、d$ 都是常数）的递归函数，可以直接通过 Master 公式来确定时间复杂度：

- 如果 $log(b,a) < d$ ，复杂度为 $O(N^d)$ 
- 如果 $log(b,a) > d$ ，复杂度为 $O(N^{log(b,a)})$ 
- 如果 $log(b,a) == d$ ，复杂度为 $O(N^d * logN)$

### 递归算法模版

使用递归时：

- 根据题目判断返回值类型

- 确定什么时候结束递归 —— base case

  > 终止条件的特性是 **不可分割，不可构造**。

- 写最后一层递归的条件代码

```java
public void recur(int level, int param) { 
  // 终止条件 
  if (level > MAX_LEVEL) { 
    // 处理结果
    return; 
  }
  // 处理当前逻辑
  process(level, param); 
  // 向下递归
  recur( level: level + 1, newParam); 
  // 恢复当前状态
```

