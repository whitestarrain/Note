/*
 * @lc app=leetcode.cn id=25 lang=cpp
 *
 * [25] K 个一组翻转链表
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (66.22%)
 * Likes:    1570
 * Dislikes: 0
 * Total Accepted:    308.9K
 * Total Submissions: 463.6K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 示例 3：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 *
 * 示例 4：
 *
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 列表中节点的数量在范围 sz 内
 * 1
 * 0
 * 1
 *
 *
 */

#include <stdio.h>
struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

// @lc code=start
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

#include <iostream>

using namespace ::std;

class Solution {
   public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        cout << head->val << " " << k << endl;
        if (k <= 0 || k == 1 || nullptr == head) {
            return head;
        }
        struct ListNode* curNode = head;
        struct ListNode* tempNode = nullptr;
        struct ListNode* tempPreNode = nullptr;
        int first = 1;
        struct ListNode* result = nullptr;
        while (curNode) {
            // cout << curNode->val << " ";
            struct ListNode* sectionHead = curNode;
            tempNode = curNode;
            int reverseFlag = 1;
            for (int i = 0; i < k; i++) {
                tempNode = tempNode->next;
                if (!tempNode->next) {
                    reverseFlag = 0;
                }
            }
            // cout << reverseFlag << " ";
            if (!reverseFlag) {
                break;
            }
            // cout << endl;
            for (int i = 0; i <= k; i++) {
                struct ListNode* temp = curNode->next;
                curNode->next = tempPreNode;
                tempPreNode = curNode;
                curNode = temp;
            }
            sectionHead->next = curNode->next;
            if (first) {
                first = 0;
                result = curNode;
            }
        }
        return result;
    }
};

int main(int argc, char* argv[]) {
    struct ListNode node1 = ListNode(1);
    struct ListNode node2 = ListNode(2);
    struct ListNode node3 = ListNode(3);
    struct ListNode node4 = ListNode(4);
    struct ListNode node5 = ListNode(5);
    struct ListNode node6 = ListNode(6);
    node1.next = &node2;
    node2.next = &node3;
    node3.next = &node4;
    node4.next = &node5;
    node5.next = &node6;

    struct ListNode* node = &node1;
    Solution s = Solution();
    s.reverseKGroup(node, 2);
    while (node) {
        std::cout << node->val << std::endl;
        node = node->next;
    }
}
// @lc code=end
