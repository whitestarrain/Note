#
# @lc app=leetcode.cn id=952 lang=python
#
# [952] 按公因数计算最大组件大小
#
# https://leetcode-cn.com/problems/largest-component-size-by-common-factor/description/
#
# algorithms
# Hard (36.92%)
# Likes:    79
# Dislikes: 0
# Total Accepted:    4.7K
# Total Submissions: 11.7K
# Testcase Example:  '[4,6,15,35]'
#
# 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
# 
# 
# 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
# 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
# 
# 
# 返回 图中最大连通组件的大小 。
# 
# 
# 
# 
# 
# 
# 示例 1：
# 
# 
# 
# 
# 输入：nums = [4,6,15,35]
# 输出：4
# 
# 
# 示例 2：
# 
# 
# 
# 
# 输入：nums = [20,50,9,63]
# 输出：2
# 
# 
# 示例 3：
# 
# 
# 
# 
# 输入：nums = [2,3,6,7,4,12,21,39]
# 输出：8
# 
# 
# 
# 
# 提示：
# 
# 
# 1 <= nums.length <= 2 * 10^4
# 1 <= nums[i] <= 10^5
# nums 中所有值都 不同
# 
# 
#

# @lc code=start
class Solution(object):
    def largestComponentSize(self, nums:list):
        """
        :type nums: List[int]
        :rtype: int
        """
        for i in range(0,len(nums)):
            for j in range(i,len(nums)):
                print(i,j)

    def getCommonFactor(self, num1, num2):
        # 寻找公因数
        pass
# @lc code=end
