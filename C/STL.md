# 什么是 STL

> STL 提供了六大组件，彼此之间可以组合套用
> 这六大组件分别是容器、算法、迭代器、仿函数、适配器、空间配置器
> 其中，在算法竞赛中用到最多的为容器、算法与迭代器。

- 容器(Container)：
  - STL 容器为各种数据结构，如 vector、stack、queue、map、set 等，用来存放数据，
  - 从实现角度来看，STL 容器是一种 class template。
- 算法(Algorithm)
  - STL 的算法多数定义在`<algorithm>`头文件中，其中包括了各种常用的算法，如 sort、find、copy、reverse 等
  - 从实现角度来看，STL 算法是一种 function template。
- 迭代器(Iterator)
  - STL 迭代器扮演了容器与算法之间的胶合剂，共有五种类型
  - 从实现角度来看，迭代器是一种将 opetator\*、opetator->、operator++等指针相关操作予以重载的 class template。所有 STL 容器都附带有自己专属的迭代器，只有容器的设计者才知道如何遍历自己的元素。
- 仿函数(Functor)：
  - 行为类似函数，可作为算法的某种策略
  - 从实现角度来看，仿函数是一种重载了 operator()的 class 或者 class template。
- 适配器(Adaptor)
  - 一种用来修饰容器或仿函数或迭代器接口的东西。
- 空间配置器(Allocator)
  - 负责空间的配置与管理
  - 从实现角度来看，配置器是一个实现了动态空间配置、空间管理、空间释放的 class template。

# 容器

## vector

- vector

  - 又称变长数组，定义在`<vector>`头文件中
  - vector 容器是动态空间，随着元素的加入，它的内部机制会自动扩充空间以容纳新的元素
  - 因此 vector 的运用对于内存的合理利用与运用的灵活性有很大的帮助。

- vector 的定义方式

  ```cpp
  vector<int> v;//定义一个vector，其中的元素为int类型
  vector<int> v[N];//定义一个vector数组，其中有N个vector
  vector<int> v(len);//定义一个长度为len的vector
  vector<int> v(len, x);//定义一个长度为len的vector，初始化每个元素为x
  vector<int> v2(v1);//用v1给v2赋值，v1的类型为vector
  vector<int> v2(v1.begin(), v1.begin() + 3);//将v1中第0~2三个元素赋值给v2
  ```

- vector 的常用内置函数

  ```cpp
  //vector中的常用内置函数
  vector<int> v = { 1, 2, 3 };//初始化vector，v:{1, 2, 3}
  vector<int>::iterator it = v.begin();//定义vector的迭代器，指向begin()

  v.push_back(4);//在vector的尾部插入元素4，v:{1, 2, 3, 4}
  v.pop_back();//删除vector的最后一个元素，v:{1, 2, 3}
  //注意使用lower_bound()与upper_bound()函数时vector必须是有序的，upper_bound()在<algorithm>中
  lower_bound(v.begin(), v.end(), 2);//返回第一个大于等于2的元素的迭代器v.begin() + 1，若不存在则返回v.end()
  upper_bound(v.begin(), v.end(), 2);//返回第一个大于2的元素的迭代器v.begin() + 2，若不存在则返回v.end()
  v.size();//返回vector中元素的个数
  v.empty();//返回vector是否为空，若为空则返回true否则返回false
  v.front();//返回vector中的第一个元素
  v.back();//返回vector中的最后一个元素
  v.begin();//返回vector第一个元素的迭代器
  v.end();//返回vector最后一个元素后一个位置的迭代器
  v.clear();//清空vector
  v.erase(v.begin());//删除迭代器it所指向的元素，即删除第一个元素
  v.erase(v.begin(), v.begin() + 2);//删除区间[v.begin(), v.begin() + 2)的所有元素
  v.insert(v.begin(), 1);//在迭代器it所指向的位置前插入元素1，返回插入元素的迭代器
  ```

- vector 遍历

  ```cpp
  //根据下标进行遍历
  for (int i = 0; i < v.size(); i++)
      cout << v[i] << ' ';
  //使用迭代器遍历
  for (vector<int>::iterator it = v.begin(); it != v.end(); it++)
      cout << *it << ' ';
  //for_each遍历(C++11)
  for (auto x : v)
      cout << x << ' ';
  ```

## stack

- stack

  - 又称栈，是一种后进先出(Last In First Out，LIFO)的数据结构，定义在`<stack>`头文件中
  - stack 容器允许新增元素、移除元素、取得栈顶元素
  - 但是除了最顶端以外，没有任何方法可以存取 stack 的其它元素
  - **换言之，stack 不允许有遍历行为**

- stack 的定义方式

  ```cpp
  stack<int> stk;//定义一个stack，其中元素的类型为int
  stack<int> stk[N];//定义一个stack数组，其中有N个stack
  ```

- stack 的常用内置函数

  ```cpp
  //stack中的常用内置函数
  stack<int> stk;
  stk.push(x);//在stack中插入元素x
  stk.pop();//弹出stack的栈顶元素
  stk.top();//返回stack的栈顶元素
  stk.size();//返回stack中元素的个数
  stk.empty();//返回stack是否为空，若为空则返回true否则返回false
  ```

## string

- string：

  - 又称字符串，定义在`<string>`头文件中
  - C 风格的字符串(以空字符结尾的字符数组)太过复杂难于掌握，因此 C++标准库定义了一种 string 类
  - string 和`vector<char>`在数据结构、内存管理等方面都是相同的
  - 但是，`vector<char>`只是单纯的一个“charchar 元素的容器”，而 string 不仅是一个“charchar 元素的容器”，它还扩展了一些针对字符串的操作
    - 例如 string 可以使用 c_str()函数转换为 C 风格的字符串
    - vector 中并未对输入输出流操作符进行重载，因此无法直接对`vector<char>`进行 cin 或者 cout 这样的操作，但是 string 可以
    - `vector<char>`并不能直接实现字符串的拼接，但是 string 可以，string 中重载了+,+=+,+=运算符。

- string 的定义方式
  ```cpp
  string str;//定义一个空的字符串
  string str[N];//定义一个string数组，其中有N个string
  string str(5, 'a');//使用5个字符'a'初始化
  string str("abc");//使用字符串初始化
  ```
- string 的常用内置函数

  ```cpp
  //string中的常用内置函数
  string str("abcabc");
  str.push_back('d');//在string尾部插入字符，"abcabcd"
  str.pop_back();//删除string尾部的字符，"abcabc"
  str.length();//返回string中字符的个数
  str.size();//作用与length()相同
  str.empty();//返回string是否为空，若为空返回true否则返回false
  str.substr(1);//返回string中从下标为1开始至末尾的子串，"bcabc"
  str.substr(0, 2);//返回string中从下标为0开始长度为2的子串，"ab"
  str.insert(1, 2, 'x');//在下标为1的字符前插入2个字符'x'，"axxbcabc"
  str.insert(1, "yy");//在下标为1的字符前插入字符串"yy"，"ayyxxbcabc"
  str.erase(1, 4);//删除从位置1开始的4个字符，"abcabc"
  str.find('b');//返回字符'b'在string中第一次出现的位置，返回1，若不存在则返回-1
  str.find('b', 2);//返回从位置2开始字符'b'在string中第一次出现的位置，返回4
  str.find("bc");//同上，返回字符串第一次出现的位置，返回1，若不存在则返回-1
  str.find("bc", 2);//返回4
  str.rfind('b');//反向查找，原理同上，返回4，若不存在则返回-1
  str.rfind('b', 3);//返回1
  str.rfind("bc");//返回4，若不存在则返回-1
  str.rfind("bc", 3);//返回1
  stoi(str);//返回str的整数形式
  to_string(value);//返回value的字符串形式，value为整型、浮点型等
  str[0];//用下标访问string中的字符
  cout << (str == str) << endl;//string可比较大小，按字典序
  ```

- string 的 erase()与 remove()函数的用法

  ```cpp
  //string中erase()与remove()的用法
  string str1, str2, str3, str4, str5;
  str1 = str2 = str3 = str4 = str5 = "I love AcWing! It's very funny!";
  str1.erase(15);//删除[15,end())的所有元素，"I love AcWing!"
  str2.erase(6, 11);//从第6个元素(包括)开始往后删除11个元素，"I love's very funny!"
  str3.erase(str3.begin() + 2);//删除迭代器所指的元素，"I ove AcWing! It's very funny!"
  str4.erase(str4.begin() + 7, str4.end() - 11);//删除[str4.begin()+7,str4.end()-11)的所有元素，"I love very funny!"
  str5.erase(remove(str5.begin(), str5.end(), 'n'), str5.end());//删除[str5.begin(),str5.end())中所有字符'n'，"I love AcWig! It's very fuy!"
  ```

## queue and priority_queue

- queue

  - 又称队列，是一种先进先出(First In First Out，FIFO)的数据结构，定义在`<queue>`头文件中
  - queue 容器允许从一端(称为队尾)新增元素(入队)，从另一端(称为队头)移除元素(出队)。

- priority_queue

  - 又称优先队列，同样定义在`<queue>`头文件中
  - 与 queue 不同的地方在于我们可以自定义其中数据的优先级，优先级高的排在队列前面，优先出队
  - priority_queue 具有 queue 的所有特性，包括基本操作，只是在这基础上添加了内部的一个排序
  - 它的本质是用堆实现的，因此可分为小根堆与大根堆，小根堆中较小的元素排在前面，大根堆中较大的元素排在前面
  - 注意：创建 priority_queue 时 **默认是大根堆**

- queue 的定义方式
  ```cpp
  queue<int> que;//定义一个queue，其中元素的类型为int
  queue<int> que[N];//定义一个queue数组，其中有N个queue
  priority_queue<int> bigHeap;//定义一个大根堆
  priority_queue<int, vector<int>, greater<int> > smallHeap;//定义一个小根堆
  ```
- queue 的常用内置函数
  ```cpp
  //queue中的常用内置函数
  queue<int> que;
  que.push(x);//在queue的队尾插入元素x
  que.pop();//出队queue的队头元素
  que.front();//返回queue的队头元素
  que.back();//返回queue的队尾元素
  que.size();//返回stack中元素的个数
  que.empty();//返回stack是否为空，若为空则返回true否则返回false
  ```

## deque

- deque

  - 又称双端队列，定义在`<deque>`头文件中
  - vector 容器是单向开口的连续内存空间，deque 则是一种双向开口的连续线性空间
  - 所谓的双向开口，意思是可以在头尾两端分别做元素的插入和删除操作
  - 当然，vector 也可以在头尾两端插入元素，但是在其头部进行插入操作效率奇差，无法被接受
  - deque 和 vector 最大的差异
    - 一是在于 deque 允许使用常数项时间在头部进行元素的插入和删除操作
    - 二是在于 deque 没有容量的概念，因为它是动态的以分段连续空间组合而成，随时可以增加一段新的空间并链接起来。

- deque 的定义方式

  ```cpp
  deque<int> deq;//定义一个deque，其中的元素为int类型
  deque<int> deq[N];//定义一个deque数组，其中有N个deque
  deque<int> deq(len);//定义一个长度为len的deque
  deque<int> deq(len, x);//定义一个长度为len的deque，初始化每个元素为x
  deque<int> deq2(deq1);//用deq1给v2赋值，deq2的类型为deque
  deque<int> deq2(deq1.begin(), deq1.begin() + 3);//将deq1中第0~2三个元素赋值给deq2
  ```

- deque 的常用内置函数

  ```cpp
  //deque中的常用内置函数
  deque<int> deq = { 1, 2, 3 };//初始化vector，v:{1, 2, 3}
  deque<int>::iterator it = deq.begin();//定义vector的迭代器，指向begin()

  deq.push_back(4);//在deque的尾部插入元素4，v:{1, 2, 3, 4}
  deq.pop_back();//删除deque的尾部元素，v:{1, 2, 3}
  deq.push_front(4);//在deque的头部插入元素4，v:{4, 1, 2, 3}
  deq.pop_front();//删除deque的头部元素，v:{1, 2, 3}
  deq.size();//返回deque中元素的个数
  deq.empty();//返回deque是否为空，若为空则返回true否则返回false
  deq.front();//返回deque中的第一个元素
  deq.back();//返回deque中的最后一个元素
  deq.begin();//返回deque第一个元素的迭代器
  deq.end();//返回deque最后一个元素后一个位置的迭代器
  deq.clear();//清空deque
  deq.erase(deq.begin());//删除迭代器it所指向的元素，即删除第一个元素
  deq.erase(deq.begin(), deq.begin() + 2);//删除区间[v.begin(), v.begin() + 2)的所有元素
  deq.insert(deq.begin(), 1);//在迭代器it所指向的位置前插入元素1，返回插入元素的迭代器
  ```

- 遍历
  ```cpp
  //根据下标进行遍历
  for (int i = 0; i < deq.size(); i++)
  cout << deq[i] << ' ';
  //使用迭代器遍历
  for (deque<int>::iterator it = deq.begin(); it != deq.end(); it++)
  cout << *it << ' ';
  //for_each遍历(C++11)
  for (auto x : deq)
  cout << x << ' ';
  ```

## map/multimap

- map/multimap

  - 又称映射，定义在`<map>`头文件中，map 和 multimap 的底层实现机制都是红黑树
  - map 的功能是能够将任意类型的元素映射到另一个任意类型的元素上，并且所有的元素都 **会根据元素的键值自动排序**
  - map 所有的元素都是 pair，同时拥有实值和键值
    - pair 的第一元素被视为键值，第二元素被视为实值，map 不允许两个元素有相同的键值
  - multimap 和 map 的操作类似，唯一区别是 multimap 的键值允许重复。

- map/multimap 的定义方式

  ```cpp
  map<string, int> mp;//定义一个将string映射成int的map
  map<string, int> mp[N];//定义一个map数组，其中有N个map
  multimap<string, int> mulmp;//定义一个将string映射成int的multimap
  multimap<string, int> mulmp[N];//定义一个multimap数组，其中有N个multimap
  ```

- map/multimap 的常用内置函数

  ```cpp
  //map/multimap中的常用内置函数
  map<string, int> mp;
  mp["abc"] = 3;//将"abc"映射到3
  mp["ab"]++;//将"ab"所映射的整数++
  mp.insert(make_pair("cd", 2));//插入元素
  mp.insert({ "ef", 5 });//同上
  mp.size();//返回map中元素的个数
  mp.empty();//返回map是否为空，若为空返回true否则返回false
  //mp.clear();//清空map
  mp.erase("ef");//清除元素{"ef", 5}
  mp["abc"];//返回"abc"映射的值
  mp.begin();//返回map第一个元素的迭代器
  mp.end();//返回map最后一个元素后一个位置的迭代器
  mp.find("ab");//返回第一个键值为"ab"的迭代器，若不存在则返回mp.end()
  mp.find({ "abc", 3 });//返回元素{"abc", 3}的迭代器，若不存在则返回mp.end()
  mp.count("abc");//返回第一个键值为"abc"的元素数量1，由于map元素不能重复因此count返回值只有0或1
  mp.count({ "abc", 2 });//返回第一个键值为"abc"的元素数量1，注意和find不一样，count只判断第一个键值
  mp.lower_bound("abc");//返回第一个键值大于等于"abc"的元素的迭代器，{"abc", 3}
  mp.upper_bound("abc");//返回第一个键值大于"abc"的元素的迭代器，{"cd", 2}
  ```

- 遍历

  ```cpp
  //使用迭代器遍历
  for (map<string, int>::iterator it = mp.begin(); it != mp.end(); it++)
  cout << (*it).first << ' ' << (*it).second << endl;
  //for_each遍历(C++11)
  for (auto x : mp)
  cout << x.first << ' ' << x.second << endl;
  //扩展推断范围的for_each遍历(C++17)
  for (auto &[k, v] : mp)
  cout << k << ' ' << v << endl;
  ```

## set/multiset

- set/multiset：

  - 又称集合，定义在`<set>`头文件中
  - set 的特性是所有元素都会根据元素的键值自动被排序，set 的元素不像 map 那样可以同时拥有实值和键值
  - set 的元素既是键值又是实值，set 不允许两个元素有相同的键值
  - 因此总结来说就是 set 中的元素是有序且不重复的
  - multiset 的特性和用法和 set 完全相同，唯一的区别在于 multiset 允许有重复元素
  - set 和 multiset 的底层实现都是红黑树。

- set/multiset 的定义方式

  ```cpp
  set<int> st;//定义一个set，其中的元素类型为int
  set<int> st[N];//定义一个set数组，其中有N个set
  multiset<int> mulst;//定义一个multiset
  multiset<int> mulst[N];//定义一个multiset数组，其中有N个multiset
  ```

- set/multiset 的常用内置函数

  ```cpp
  //set/multiset中的常用内置函数
  set<int> st;
  st.insert(5);//插入元素5
  st.insert(6);//同上
  st.insert(7);//同上
  st.size();//返回set中元素的个数
  st.empty();//返回set是否为空，若为空返回true否则返回false
  st.erase(6);//清除元素6
  st.begin();//返回set第一个元素的迭代器
  st.end();//返回set最后一个元素后一个位置的迭代器
  st.clear();//清空set
  st.find(5);//返回元素5的迭代器，若不存在则返回st.end()
  st.count(5);//返回元素5的个数1，由于set元素不会重复，因此count返回值只有0或1
  st.lower_bound(5);//返回第一个键值大于等于5的元素的迭代器，返回元素5的迭代器
  st.upper_bound(5);//返回第一个键值大于5的元素的迭代器，返回元素7的迭代器
  ```

- 遍历

  ```cpp
  //使用迭代器遍历
  for (set<int>::iterator it = st.begin(); it != st.end(); it++)
      cout << (*it) << ' ';
  //for_each遍历(C++11)
  for (auto x : st)
      cout << x << ' ';
  ```

## unordered_map/unordered_set

- unordered_map/unordered_set

  - 分别定义在`<unordered_map>`与`<unordered_set>`头文件中
  - 内部采用的是 hash 表结构，拥有快速检索的功能
  - 与 map/set 相比最大的区别在于 unordered_map/unordered_set 中的元素是无序的
  - 增删改查的时间复杂度为 O(1)(map/set 增删改查的时间复杂度为 O(logn))
  - 但是不支持 lower_bound()/upper_bound()函数。

- unordered_map/unordered_set 的定义方式

  ```cpp
  unordered_set<int> st;//定义一个unordered_set，其中的元素类型为int
  unordered_set<int> st[N];//定义一个unordered_set数组，其中有N个unordered_set
  unordered_map<int, int> mp;//定义一个unordered_map
  unordered_map<int, int> mp[N];//定义一个unordered_map数组，其中有N个unordered_map
  ```

- unordered_map/unordered_set 的常用内置函数

  ```cpp
  //unordered_map/unordered_set中的常用内置函数
  unordered_set<int> st;
  unordered_map<int, int> mp;
  st.insert(5);//插入元素5
  st.insert(6);//同上
  st.insert(7);//同上
  st.size();//返回unordered_set中元素的个数
  st.empty();//返回unordered_set是否为空，若为空返回true否则返回false
  st.erase(6);//清除元素6
  st.begin();//返回unordered_set第一个元素的迭代器
  st.end();//返回unordered_set最后一个元素后一个位置的迭代器
  st.clear();//清空unordered_set
  mp.insert(make_pair(1, 2));//插入元素{1, 2}
  mp.insert({ 3, 4 });//同上
  mp.size();//返回unordered_map中元素的个数
  mp.empty();//返回unordered_map是否为空，若为空返回true否则返回false
  mp.erase(3);//清除元素{3, 4}
  mp.begin();//返回unordered_map第一个元素的迭代器
  mp.end();//返回unordered_map最后一个元素后一个位置的迭代器
  mp.clear();//清空unordered_map
  ```

- 遍历

  ```cpp
  //使用迭代器遍历
  for (unordered_set<int>::iterator it = st.begin(); it != st.end(); it++)
      cout << (*it) << ' ';
  //for_each遍历(C++11)
  for (auto x : st)
      cout << x << ' ';

  //使用迭代器遍历
  for (unordered_map<int, int>::iterator it = mp.begin(); it != mp.end(); it++)
      cout << (*it).first << ' ' << (*it).second << endl;
  //for_each遍历(C++11)
  for (auto x : mp)
      cout << x.first << ' ' << x.second << endl;
  //扩展推断范围的for_each遍历(C++17)
  for (auto &[k, v] : mp)
      cout << k << ' ' << v << endl;
  ```

# 算法

- 说明

  - C++标准库定义了一组泛型算法
  - 之所以称为泛型指的是它们可以操作在多种容器上
  - 不但可以作用于标准库类型，还可以用在内置数组类型甚至其它类型的序列上
  - 泛型算法定义在`<algorithm>`头文件中
  - 标准库还定义了一组泛化的算术算法(Generalized Numeric Algorithm)，定义在`<numeric>`头文件中。

- 示例

  ```cpp
  #include <iostream>
  #include <algorithm>
  #include <numeric>
  using namespace std;

  int main()
  {
      //使用STL容器时将数组地址改为迭代器即可

      int a[5] = { 1, 2, 3, 4, 5 };

      //排序算法
      sort(a, a + 5);//将区间[0, 5)内元素按字典序从小到大排序
      sort(a, a + 5, greater<int>());//将区间[0, 5)内元素按字典序从大到小排序
      reverse(a, a + 5);//将区间[0, 5)内元素翻转
      nth_element(a, a + 3, a + 5);//将区间[0, 5)中第a + 3个数归位，即将第3大的元素放到正确的位置上，该元素前后的元素不一定有序

      //查找与统计算法
      find(a, a + 5, 3);//在区间[0, 5)内查找等于3的元素，返回迭代器，若不存在则返回end()
      binary_search(a, a + 5, 2);//二分查找区间[0, 5)内是否存在元素2，若存在返回true否则返回false
      count(a, a + 5, 3);//返回区间[0, 5)内元素3的个数

      //可变序列算法
      copy(a, a + 2, a + 3);//将区间[0, 2)的元素复制到以a+3开始的区间，即[3, 5)
      replace(a, a + 5, 3, 4);//将区间[0, 5)内等于3的元素替换为4
      fill(a, a + 5, 1);//将1写入区间[0, 5)中(初始化数组函数)
      unique(a, a + 5);//将相邻元素间的重复元素全部移动至末端，返回去重之后数组最后一个元素之后的地址
      remove(a, a + 5, 3);//将区间[0, 5)中的元素3移至末端，返回新数组最后一个元素之后的地址

      //排列算法
      next_permutation(a, a + 5);//产生下一个排列{ 1, 2, 3, 5, 4 }
      prev_permutation(a, a + 5);//产生上一个排列{ 1, 2, 3, 4, 5 }

      //前缀和算法
      partial_sum(a, a + 5, a);//计算数组a在区间[0, 5)内的前缀和并将结果保存至数组a中

      // vector sort
      vector<int> v = { 1, 2, 3 };//初始化vector，v:{1, 2, 3}
      sort(vector.begin(),vector.end());

      return 0;
  }
  ```
