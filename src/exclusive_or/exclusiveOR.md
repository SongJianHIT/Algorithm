# exclusive OR

**异或运算（exclusive OR）**：二机制的运算方式，**相同为 0，不同为 1**，也可以直接记为 **无进位相加**。

但通常题目会考察异或的一些特殊性质。

## 异或的性质

异或的重要性质：

- $0\ {\wedge}\ N = N$
- $N\ {\wedge}\ N = 0$
- 满足交换律：$a\ {\wedge}\ b = b\ {\wedge}\ a$
- 满足结合律：$a\ {\wedge}\ (b\ {\wedge}\ c)= (a\ {\wedge}\ b)\ {\wedge}\ c $

## 异或的作用

### 不使用额外变量交换两个数

常见方式：

```java
int tmp = a;
a = b;
b = tmp;
```

但怎么不使用 `tmp` 就能实现交换呢？使用异或这个骚操作！

```java
a = a ^ b;
b = a ^ b;	// 相当于 b = a ^ b ^ b = a
a = a ^ b;	// 相当于 a = a ^ b ^ a = b
```















