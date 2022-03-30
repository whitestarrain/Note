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

void test1(){
    int* i;
    if (i) { printf("野指针为true"); }
    else {
        printf("野指针为false");
    }

}

void test2(){
    std::vector<int> v(256,-1);
    for(int i = 0;i<v.size();i++){
        printf("%d",v[i]);
    }
}

void test3(){
    std::string s = "sssss";
    int size = s.size();
    int length = s.length();
    // int cap = s.capacity();
    printf("%d,%d",size,length);
}

int main(int argc, char* argv[])
{
    // test1();
    // test2();
    test3();
}
