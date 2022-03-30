/*
 * @lc app=leetcode.cn id=3 lang=cpp
 *
 * [3] 无重复字符的最长子串
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (38.57%)
 * Likes:    7245
 * Dislikes: 0
 * Total Accepted:    1.6M
 * Total Submissions: 4.2M
 * Testcase Example:  '"abcabcbb"'
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *
 * 示例 2:
 *
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *
 * 示例 3:
 *
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 *
 *
 */

// @lc code=start

#include <algorithm>
#include <string>
#include <unordered_set>

using namespace ::std;

class Solution
{
public:
    /**
     * 思路：滑动窗口
     * 每个字符为起点
     * 从左向右，计算一遍最长的非重复字符
     */
    int lengthOfLongestSubstring(string s)
    {
        unordered_set<char> set;
        int len = s.size();
        // right 为右指针
        int right = -1, maxLen = 0;
        // i 充当左指针
        for (int i = 0; i < len; ++i) {
            if (i != 0) {
                set.erase(s[i - 1]);
            }
            // 右指针向右移动
            while (right + 1 < len && !set.count(s[right + 1])) {
                set.insert(s[right + 1]);
                ++right;
            }
            maxLen = max(maxLen, right - i + 1);
        }
        return maxLen;
    }
};
// @lc code=end
