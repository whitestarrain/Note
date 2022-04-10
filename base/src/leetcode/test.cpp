#include <iostream>
#include <stddef.h>
#include <stdio.h>
#include <string>
#include <vector>

using namespace std;
struct ListNode
{
    int              val;
    struct ListNode* next;
};

void test1()
{
    int* i;
    if (i) { printf("野指针为true"); }
    else {
        printf("野指针为false");
    }
}

void test2()
{
    std::vector<int> v(256, -1);
    for (int i = 0; i < v.size(); i++) { printf("%d", v[i]); }
}

void test3()
{
    std::string s      = "sssss";
    int         size   = s.size();
    int         length = s.length();
    // int cap = s.capacity();
    printf("%d,%d", size, length);
}

void test4()
{
    int a[] = {1, 2, 3, 4, 5};
    // []的优先级比*高
    int(*pa)[5] = &a;

    char*  str[] = {"aaa", "bb"};
    char** ppa   = str;
}

void test5()
{
    // c++中的引用，类似于变量的别名
    // 可以对比一下java中的引用变量

    int  i  = 10;
    int& ir = i;
    cout << i << endl;
    cout << ir << endl;

    i = 11;

    cout << i << endl;
    cout << ir << endl;

    /* 10
    10
    11
    11 */
}

int main(int argc, char* argv[])
{
    // test1();
    // test2();
    // test3();
    // test5();
    int i = 7;
    int j1 = i << 1;
    int j2 = i >> 1;
    cout << j1 << " " << j2;
}
