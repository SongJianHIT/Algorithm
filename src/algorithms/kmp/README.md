# KMP算法

## 基本介绍

`Knuth-Morris-Pratt` 字符串查找算法，简称为 “KMP算法”，**常用于在一个文本串 S 内查找一个模式串 P 的出现位置**，这个算法由 `Donald Knuth、Vaughan Pratt、James H. Morris` 三人于 1977 年联合发表，故取这 3 人的姓氏命名此算法。

字符串匹配，暴力解决的时间复杂度为 $O(MN)$，而 KMP 算法可以做到 $O(N)$ 的时间复杂度。

首先定义 **信息** 的概念：

- 前缀串和后缀串不能取到前面的整体
- 前缀串和后缀串相同的最大长度
- 第一个字符的信息为 -1（因为前面没有字符）

![IMG_7588F6B416CF-1](https://p.ipic.vip/lr05h9.jpg)

因此，我们可以基于一个串，搞定它所有位置上的信息，组成的数组称为 `next` 数组：

![IMG_7C8D9FEC9987-1](https://p.ipic.vip/1efsqs.jpg)

在字符串匹配问题中，存在两个字符串，原字符串 `str1` 和模式串 `str2`，那么这个 `next` 数组是对谁求的呢？

- 是对 `str2` 求的，也就是 **在模式串上** 去求 `next` 数组

正是这个 `next` 数组，可以让匹配过程加速，过程如下：

![IMG_8EB4B6ED6639-1](https://p.ipic.vip/prs396.jpg)

举个具体的例子：

![IMG_34CBF2825101-1](https://p.ipic.vip/dzca53.jpg)

## 经典题目

