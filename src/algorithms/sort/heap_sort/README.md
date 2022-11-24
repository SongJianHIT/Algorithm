# 堆与堆排序

## 1 简介

### 1.1 比较器

比较器的实质是 **重载比较运算符** 。比较器的应用：

- 特殊标准的排序
- 根据特殊标准排序的结构（如 有序表 `TreeMap` ）

我们可以通过比较器来定义通过实体类的某个属性大小进行排序。需要在该实体类中构建一个内部类，该内部类 **必须实现 `Comparator<T>` 接口** ，实现 `compare(T t1, T t2)` 接口方法。

在所有 `compare` 方法中，遵循一个统一的规范：

- 返回负数的时候，认为第一个参数排在前面
- 返回正数的时候，认为第二个参数排在前面
- 返回 0 时，认为无所谓谁前谁后

```java
public class Comparator {

	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	// 比较器
	public static class IdShengAgeJiangOrder implements Comparator<Student> {

		// 根据id从小到大，但是如果id一样，按照年龄从大到小
		@Override
		public int compare(Student o1, Student o2) {
			return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
		}
	}
}
```

### 1.2 堆

堆（heap）在使用层面上，称为 **优先级队列** 。

> **完全二叉树** ：如果一个二叉树是满二叉树，或者正在从左往右依次变满的过程的二叉树，称为完全二叉树。
>
> 这里使用 **数组** 的形式来表示完全二叉树，对于数组中 `i` 下标的元素：
>
> - 左孩子位置为 `2 * i + 1` 
> - 右孩子位置为 `2 * i + 2` 
> - 父结点位置为 `(i - 1) / 2` 
>
> 我们可以通过加入额外的标志 `size` 来表示现在数组中前多少个数被视为完全二叉树。

堆，是一个完全二叉树：

- 大根堆：**每棵子树** 的最大值都作为该子树的根结点
- 小根堆：**每棵子树** 的最小值都作为该子树的根结点

![IMG_E5BECF1617A5-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8g1lkeaskj315y0jogni.jpg)

> 在堆中，我们只关注两个操作： `heapInsert` 和 `heapify` ，这两个操作的时间复杂度为 $O(logN)$ 。

#### 堆中插入元素

插入过程，也称为 **上移过程** `heapInsert`，以大根堆为例：

![IMG_7B7182969917-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h8g1ysbjq3j31a80u0ag4.jpg)

#### 获取堆中最大值并删除

删除过程，会导致下沉过程，下沉称为 `heapify` 。

- 第一步，将堆中第一个元素（即堆顶，最大值）与 **堆中最后一个元素** 进行交换
- 第二步，逻辑删除堆中最后一个元素（即被交换过去的最大值）， `heapSize--`
- 第三步，重新调整堆结构，进行下沉

#### 修改堆中间的某个元素

修改堆中的元素，不知道改大还是改小，就按顺序写 `heapInsert()` 和 `heapify()` ，两个方法之可能执行一个。

#### 便捷地修改大根堆为小根堆

重新写比较器即可！比较器：

```java
public static class MyComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
    }
}
```

随后可使用 `Priority<T>` 即可创建 **大/小根堆** 。

```java
public static void main(String[] args) {
		// 小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
		heap.add(5);
		heap.add(5);
		heap.add(5);
		heap.add(3);
		// 5 , 3
		System.out.println(heap.peek());
}
```

### 1.3 堆排序

















