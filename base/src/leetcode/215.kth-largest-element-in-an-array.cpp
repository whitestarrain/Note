// 排序

/*
 * @lc app=leetcode.cn id=215 lang=cpp
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (64.69%)
 * Likes:    1579
 * Dislikes: 0
 * Total Accepted:    581.7K
 * Total Submissions: 899.7K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 *
 * 示例 2:
 *
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 提示：
 * 1
 * -10^4 
 */


// @lc code=start

#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    int findKthLargest(vector<int>& nums, int k)
    {
        // sort(nums.begin(), nums.end());
        quickSort(nums, 0, nums.size() - 1);
        return nums[nums.size() - k];
    }

    // NOTE: 十大排序算法
    // https://www.runoob.com/w3cnote/quick-sort-2.html
    void popSort1(vector<int>& nums)
    {
        // 2,1,3,4
        int size = nums.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) swap(nums, j, j + 1);
            }
        }
    }

    void popSort2(vector<int>& nums)
    {
        int size = nums.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                if (nums[j - 1] > nums[j]) swap(nums, j, j - 1);
            }
        }
    }

    void selectSort(vector<int>& nums) {}

    void insertSort(vector<int>& nums) {}

    void shellSort(vector<int>& nums) {}

    void mergeSort(vector<int>& nums) {}

    /**
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     */
    void quickSort(vector<int>& nums, int left, int right)
    {
        if (left >= right) return;
        cout << "left:" << left << endl;
        cout << "right:" << right << endl;
        int i = left;
        int j = right;
        while (i < j) {
            // 下面两个while的顺序不能反，因为left为哨兵，所以先j--
            // 否则以下情况会有问题： [3,2,1,4,5]
            // i 先右移的话，可能就直接指向4了，导致4和5交换
            while (nums[j] >= nums[left] && j > i) j--;
            while (nums[i] <= nums[left] && i < j) i++;
            if (nums[i] > nums[j]) swap(nums, i, j);
        }

        // 左哨兵和当前i位置处进行交换。(因为哨兵位于左侧，此时nums[left] >= nums[i])
        swap(nums, i, left);

        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    void heapSort(vector<int>& nums) {}

    void countingSort(vector<int>& nums) {}

    void bucketSort(vector<int>& nums) {}

    void radixSort(vector<int>& nums) {}


    /**
     * 位置交换
     */
    void swap(vector<int>& nums, int i, int j)
    {
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
};

// @lc code=end

int main(int argc, char* argv[])
{
    vector<int> testVector = {1, 2, 3, 1, 2, 4, 1, 2, 52, 4, 6, 10};   //初始化vector，v:{1, 2, 3}
    Solution    solution   = Solution();
    // solution.popSort1(testVector);
    solution.quickSort(testVector, 0, testVector.size() - 1);
    for (int i = 0; i < testVector.size(); i++) { cout << testVector[i] << " "; }

    return 0;
}
