# 算法说明

## 编码算法

### 字符集和字符编码

![encryption-1](./image/encryption-1.png)

- 字符集和编码
  - 字符集(Character Set):是一个系统支持的所有抽象字符的集合
    - 定义系统能处理哪些字符。
    - 字符是各种文字和符号的总称，包括各国家文字、标点符号、图形符号、数字等。
    - 常见字符集有：ASCII字符集、GB2312字符集、BIG5字符集、GB18030字符集、Unicode字符集等。
  - 编码(Encoding):则规定这些字符在计算机内部的表示方式。
    - 是一套法则，使用该法则能够对自然语言的字符的一个集合（如字母表或音节表），与其他东西的一个集合（如号码或电脉冲）进行配对。
    - 即在符号集合与数字系统之间建立对应关系，它是信息处理的一项基本技术。
    - 通常人们用符号集合（一般情况下就是文字）来表达信息。
    - 而以计算机为基础的信息处理系统则是利用元件（硬件）不同状态的组合来存储和处理信息的。
    - 元件不同状态的组合能代表数字系统的数字，因此字符编码就是将符号转换为计算机可以接受的数字系统的数，称为数字代码。
  - 关系
    - **每个字符集都有自己对应的字符编码**, ASCII字符集就有ASCII编码

      <details>
      <summary style="color:red;">ASCII编码表</summary>

      ![encryption-2](./image/encryption-2.png)
      </details>
    - 但是每个字符集不止有一种字符编码。如unicode字符集有unicode编码(3个字节)，utf-8(1-3个字节)等。


### URL 编码

### Base64 编码

## 哈希(hash)算法/摘要(digest)算法

### 算法说明

| 算法       | 输出长度（位） | 输出长度（字节） |
| :--------- | :------------- | :--------------- |
| MD5        | 128 bits       | 16 bytes         |
| SHA-1      | 160 bits       | 20 bytes         |
| RipeMD-160 | 160 bits       | 20 bytes         |
| SHA-256    | 256 bits       | 32 bytes         |
| SHA-512    | 512 bits       | 64 bytes         |

### java 库-BouncyCastle

## Hmac 算法

> ak/sk

## 对称加密算法

### 算法说明

| 算法 | 密钥长度    | 工作模式             | 填充模式                                |
| :--- | :---------- | :------------------- | :-------------------------------------- |
| DES  | 56/64       | ECB/CBC/PCBC/CTR/... | NoPadding/PKCS5Padding/...              |
| AES  | 128/192/256 | ECB/CBC/PCBC/CTR/... | NoPadding/PKCS5Padding/PKCS7Padding/... |
| IDEA | 128         | ECB                  | PKCS5Padding/PKCS7Padding/...           |

### 示例

## 口令加密算法

### 说明

### 示例

## 密钥交换算法

### 说明

### 实现

## 非对称加密算法

## 签名算法

## 数字证书

# 应用

## Https 的连接与中间人攻击

# 参考资料

- [廖雪峰-加密与安全](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943717668160)
- [字符集和字符编码（Charset & Encoding）](https://www.runoob.com/w3cnote/charset-encoding.html)
- [Unicode 和 UTF-8 的关系](https://blog.csdn.net/zhusongziye/article/details/84261211)
- [字符编码笔记：ASCII，Unicode 和 UTF-8](https://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html)
