# 1. 编码算法

## 1.1. URL 编码

## 1.2. Base64 编码

# 2. 哈希(hash)算法/摘要(digest)算法

## 2.1. 算法说明

| 算法       | 输出长度（位） | 输出长度（字节） |
| :--------- | :------------- | :--------------- |
| MD5        | 128 bits       | 16 bytes         |
| SHA-1      | 160 bits       | 20 bytes         |
| RipeMD-160 | 160 bits       | 20 bytes         |
| SHA-256    | 256 bits       | 32 bytes         |
| SHA-512    | 512 bits       | 64 bytes         |

## 2.2. java 库-BouncyCastle

# 3. Hmac 算法

> ak/sk

# 4. 对称加密算法

## 4.1. 算法说明

| 算法 | 密钥长度    | 工作模式             | 填充模式                                |
| :--- | :---------- | :------------------- | :-------------------------------------- |
| DES  | 56/64       | ECB/CBC/PCBC/CTR/... | NoPadding/PKCS5Padding/...              |
| AES  | 128/192/256 | ECB/CBC/PCBC/CTR/... | NoPadding/PKCS5Padding/PKCS7Padding/... |
| IDEA | 128         | ECB                  | PKCS5Padding/PKCS7Padding/...           |

## 4.2. 示例

# 5. 口令加密算法

## 5.1. 说明

## 5.2. 示例

# 6. 密钥交换算法

## 6.1. 说明

## 6.2. 实现

# 7. 非对称加密算法

# 8. 签名算法

# 9. 数字证书

# 10. 应用与问题

# 11. mysql处理emoji

# 12. Https 的连接与中间人攻击

# 13. 参考资料

- [廖雪峰-加密与安全](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943717668160)
