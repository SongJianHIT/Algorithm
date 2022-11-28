/**
 * @projectName Algorithm
 * @package algorithms.sort.heap_sort
 * @className algorithms.sort.heap_sort.HeapGreater
 */
package algorithms.sort.heap_sort;

import algorithms.sort.SortUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * HeapGreater
 *
 * @author SongJian
 * @description 加强堆
 * @date 2022/11/28 09:16
 */
public class HeapGreater<T> {

    /**
     * 堆结构
     */
    private ArrayList<T> heap;

    /**
     * 反向索引表
     * [a, b, c]
     * a -> 0
     * b -> 1
     * c -> 2
     */
    private HashMap<T, Integer> indexMap;

    /**
     * 堆大小
     */
    private int heapSize;

    /**
     * 比较器
     * <? super E> :可以接受 E 类型的或者 E 类型的父类
     */
    private Comparator<? super T> comp;


    /**
     * @throws
     * @title HeapGreater
     * @author SongJian
     * @param: c 比较器
     * @updateTime 2022/11/28 09:25
     * @description 加强堆构造器
     */
    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    /**
     * @title isEmpty
     * @author SongJian
     * @updateTime 2022/11/28 09:26
     * @return: boolean
     * @throws
     * @description 判断加强堆是否为空
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * @title size
     * @author SongJian
     * @updateTime 2022/11/28 09:27
     * @return: int
     * @throws
     * @description 获取加强堆大小
     */
    public int size() {
        return heapSize;
    }

    /**
     * @title contains
     * @author SongJian
     * @param: obj
     * @updateTime 2022/11/28 09:27
     * @return: boolean
     * @throws
     * @description 判断加强堆是否包含某个元素
     */
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    /**
     * @title peek
     * @author SongJian
     * @updateTime 2022/11/28 09:28
     * @return: T
     * @throws
     * @description 获取堆顶元素
     */
    public T peek() {
        return heap.get(0);
    }

    /**
     * @title push
     * @author SongJian
     * @param: obj
     * @updateTime 2022/11/28 10:08
     * @throws
     * @description 向堆中加入元素
     */
    public void push(T obj) {
        heap.add(obj);
        // 记录反向索引表
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * @title pop
     * @author SongJian
     * @updateTime 2022/11/28 10:36
     * @return: T
     * @throws
     * @description 弹出堆顶元素
     */

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    /**
     * @title resign
     * @author SongJian
     * @param: obj
     * @updateTime 2022/11/28 10:21
     * @throws
     * @description 【加强堆的加强方法】如果堆中某个对象的属性发生变化，可以在 O(logN) 的时间复杂度内重排
     */
    public void resign(T obj) {
        // 下面两个方法只会执行一个，因为要么变大，要么变小
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }
    
    /**
     * @title remove
     * @author SongJian 
     * @updateTime 2022/11/28 10:25 
     * @return: void
     * @throws
     * @description 【加强堆的加强方法】要删除堆中的某个元素，可以在 O(logN) 的时间复杂度内重排
     */
    public void remove(T obj) {
        // 使用堆最后一个元素代替删除元素
        T replace = heap.get(heapSize - 1);
        // 要删除的元素的位置
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        // 需要排除一种情况：需要删的元素就是最后一个元素
        if (obj != replace) {
            // 删除元素不是最后一个元素
            // 把最后一个元素放到被删除的元素位置
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }


    /**
     * @title heapInsert
     * @author SongJian
     * @param: index
     * @updateTime 2022/11/28 10:12
     * @throws
     * @description 堆方法，上升
     */
    public void heapInsert(int index) {
        // index 当前索引，(index - 1) / 2 父索引
        // 通过定义的比较器进行大小比较
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            // 小根堆，若当前小于父，则上移
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * @title heapify
     * @author SongJian
     * @param: index
     * @updateTime 2022/11/28 10:19
     * @throws
     * @description 堆方法，下沉
     */
    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(index, best);
            index = best;
            left = index * 2 + 1;
        }
    }

    /**
     * @title swap
     * @author SongJian
     * @param: i
     * @param: j
     * @updateTime 2022/11/28 10:09
     * @throws
     * @description 堆上的 i 位置 和 j 位置进行交换，需要同时修改反向索引表
     */
    public void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }


}

