> 常用的加密算法，并非密码学理论

# 1. 编码算法

## 1.1. 编码

[跳转](../base/encoding.md)

## 1.2. URL 编码

- 作用：兼容非ASCII字符
  - URL编码是浏览器发送数据给服务器时使用的编码，它通常附加在URL的参数部分，例如：

    ```url
    https://www.baidu.com/s?wd=%E4%B8%AD%E6%96%87
    ```
  - 之所以需要URL编码，是因为出于兼容性考虑，
    - 很多服务器只识别ASCII字符。
    - 但如果URL中包含中文、日文这些非ASCII字符，就需要进行url编码

- url编码规则：
  - 如果字符是`A~Z`，`a~z`，`0~9`以及`-`、`_`、`.`、`*`，则保持不变；
  - 如果是其他字符，先转换为UTF-8编码，然后对每个字节以%XX表示。
  - 例如：字符中的UTF-8编码是0xe4b8ad，因此，它的URL编码是%E4%B8%AD。URL编码总是大写。

## 1.3. Base64 编码

### 1.3.1. 说明

- 作用：
  - 对二进制数据进行编码，表示成文本格式。
  - 但会降低传输效率，把原始数据的长度增加了1/3。

    ```
    如果把Base64的64个字符编码表换成32个、48个或者58个，
    就可以使用Base32编码，Base48编码和Base58编码。
    字符越少，编码的效率就会越低。
    ```

- 编码规则：
  - Base64编码可以把任意长度的二进制数据变为纯文本，
  - 且只包含`A~Z`、`a~z`、`0~9`、`+`、`/`、`=`这些字符。
  - 原理：
    - 把 **3字节(24bit)的二进制数据按6bit一组，用4个int整数表示**
    - 然后查表，把int整数用索引对应到字符，得到编码后的字符串。
    - 不是字节数不是3的整数时：
      - 待编码的输入刚好是24 bits，没有“=”填充。
      - 待编码的输入刚好是8 bits，首先用0填充使6 bits完整的一组，在填充2个“=”符号，最后刚好是24 bits。
      - 待编码的输入刚好是16 bits，首先用0填充使6 bits完整的一组，在填充1个“=”符号，最后刚好是24bits。
  - 编码表
    - 因为6位整数的范围总是0~63，所以，能用64个字符表示：
    - 字符A~Z对应索引0~25，
    - 字符a~z对应索引26~51，
    - 字符0~9对应索引52~61，
    - 最后两个索引62、63分别用字符+和/表示。

- 示例：

  ```
  ┌───────────────┬───────────────┬───────────────┐
  │      e4       │      b8       │      ad       │
  └───────────────┴───────────────┴───────────────┘
  ┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐─┬─┬─┬─┬─┬─
  │1│1│1│0│0│1│0│0│1│0│1│1│1│0│0│0│1│0│1│0│1│1│0│1│1│0│
  └─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘─┴─┴─┴─┴─┴─
  ┌───────────┬───────────┬───────────┬───────────┐
  │    39     │    0b     │    22     │    2d     │
  └───────────┴───────────┴───────────┴───────────┘
        5          L            i          t

  ```

- 针对URL的64位编码：
  - 因为标准的Base64编码会出现+、/和=，所以不适合把Base64编码后的字符串放到URL中。
  - 一种针对URL的Base64编码可以在URL中使用的Base64编码，它仅仅是 **把+变成-，/变成_**

# 2. 哈希(hash)算法/摘要(digest)算法

## 2.1. 说明

- 哈希算法（Hash）又称摘要算法（Digest）
  - 它的作用是：对任意一组输入数据进行计算，得到一个固定长度的输出摘要。
  - 哈希算法最重要的特点就是：
    - 相同的输入一定得到相同的输出；
    - 不同的输入大概率得到不同的输出。
  - 哈希算法的目的就是为了验证原始数据是否被篡改

- 哈希碰撞: 两个不同的输入得到了相同的输出

- 因为碰撞概率的高低关系到哈希算法的安全性。一个安全的哈希算法必须满足：
  - 碰撞概率低；
  - 不能猜测输出。
    - 输入的任意一个bit的变化会造成输出完全不同，这样就很难从输出反推输入（只能依靠暴力穷举）

```
> 维基百科

- 算法特性
  - 正向计算hash很容易
  - 反向破解hash极其困难
  - hash值碰撞概率极小
```

## 2.2. 彩虹表与salt

- 彩虹表(Rainbow Table)：
  - 一种预先计算好的 "明文->哈希值" 映射表，攻击者通过查表即可反查出原始密码
  - 常见的弱密码（如123456、password）都能在彩虹表中找到对应的哈希值
  - 彩虹表体积可达数百GB，覆盖常见密码和短字符串的组合

- 防御手段：加盐(salt)
  - salt是一段随机生成的字符串，在计算哈希前拼接到原始密码上：`hash(salt + password)`
  - 每个用户使用不同的salt，即使两个用户密码相同，存储的哈希值也不同
  - salt必须随机生成且足够长（建议16字节以上），与哈希值一起存储在数据库中
  - 加盐后，攻击者无法使用预先计算的彩虹表，必须对每个salt单独暴力破解

- 示例（伪代码）：
  ```
  salt = random_bytes(16)
  hashed = SHA256(salt + "用户密码")
  # 数据库存储：salt + hashed
  ```

- 进一步加强：使用专门的密码哈希函数（如bcrypt、scrypt、Argon2），它们内置salt且计算速度慢，能有效抵抗暴力破解

## 2.3. 常见算法

| 算法       | 输出长度（位） | 输出长度（字节） |
| :--------- | :------------- | :--------------- |
| MD5        | 128 bits       | 16 bytes         |
| SHA-1      | 160 bits       | 20 bytes         |
| RipeMD-160 | 160 bits       | 20 bytes         |
| SHA-256    | 256 bits       | 32 bytes         |
| SHA-512    | 512 bits       | 64 bytes         |

## 2.4. 高级hash算法：

### 2.4.1. murmurhash

- 说明：MurmurHash 是一种非加密型哈希函数，由 Austin Appleby 于2008年发布
  - 适用于一般的哈希检索操作，而非用于加密安全场景
  - 目前广泛应用于分布式系统中的数据分片、布隆过滤器、一致性哈希等场景

- 特点：
  - 计算速度极快，是MD5等加密哈希算法的数倍
  - 碰撞率低，分布均匀性好
  - 常见版本：MurmurHash2（32/64位）、MurmurHash3（128位输出）
  - 不适合用于安全场景，因为可以人为构造碰撞

- 应用场景：
  - Redis Cluster的哈希槽计算使用CRC16，但许多客户端分片方案使用MurmurHash
  - Kafka的分区策略默认使用MurmurHash2
  - Google Guava库中的Hashing.murmur3_128()
  - Cassandra的分区器使用MurmurHash3

### 2.4.2. cityhash

- 说明：CityHash 是Google开发的字符串哈希算法系列
  - 由 Geoff Pike 和 Jyrki Alakuijala 于2011年发布
  - 针对短字符串（<64字节）做了特别优化

- 特点：
  - 在短字符串上比MurmurHash更快
  - 利用了现代CPU的特性（如CRC32指令集）
  - 提供 CityHash64、CityHash128 两种输出长度
  - 非加密型，不保证抗碰撞攻击

- 相关算法：
  - FarmHash：CityHash的后继者，同为Google开发，更加稳定
  - xxHash：另一种高性能非加密哈希，速度接近内存带宽极限
  - SipHash：Python/Rust等语言的默认字典哈希，兼顾性能与安全

## 2.5. java 库-BouncyCastle

- 说明：BouncyCastle 是一个开源的Java/C#加密算法库
  - Java标准库的 `java.security` 包提供了基础的加密功能，但算法种类有限
  - BouncyCastle 提供了更多的算法实现，如SM2/SM3/SM4国密算法等
  - 作为第三方Provider注册后，可以通过标准API透明使用

- 使用方式：
  ```java
  // 注册BouncyCastle作为安全Provider
  Security.addProvider(new BouncyCastleProvider());
  
  // 之后就可以使用BC提供的额外算法
  MessageDigest md = MessageDigest.getInstance("RipeMD160");
  md.update("Hello".getBytes("UTF-8"));
  byte[] result = md.digest();
  ```

- 支持的额外算法（相对JDK标准库）：
  - 哈希：RipeMD160、Whirlpool、BLAKE2等
  - 对称加密：SM4、Camellia、Twofish等
  - 非对称加密：SM2、Ed25519等
  - 数字签名：Ed25519、SM2签名等

## 2.6. 变种

### 2.6.1. hashids

> [github 源码](https://github.com/davidaurelio/hashids-python)

- hashids满足第1和第3条
  - 正向计算hash很快
  - **hash值完全没有碰撞** ，保证了唯一性。
- hash值长度不固定，可指定最短长度。随着id增长，hash值会越来越长
- 如果知道salt值，还可以逆向通过hash值计算出原值。
  - 那第2条是否满足取决于你的salt秘钥有多容易被攻击者拿到。
  - 如果你的salt秘钥来自于常用字典单词，那攻击者可以通过彩虹字典快速将秘钥破解。

# 3. Hmac 算法

> ak/sk

- 说明：Hmac(Hash-based Message Authentication Code)，即基于密钥的哈希消息认证码
  - 本质是在哈希算法（如MD5/SHA256）基础上混入一个密钥(secret key)
  - 计算公式：`HMAC(K, M) = H((K' XOR opad) || H((K' XOR ipad) || M))`
  - 用途：验证数据的完整性和来源真实性，常用于API认证（AK/SK模式）

- 与普通哈希的区别：
  - 普通哈希：任何人都可以计算 `hash(data)` 并伪造
  - HMAC：必须持有密钥才能计算出正确的MAC值，防止篡改和伪造
  - HMAC可以防止长度扩展攻击（Length Extension Attack）

- Java示例：
  ```java
  import javax.crypto.Mac;
  import javax.crypto.spec.SecretKeySpec;

  SecretKeySpec key = new SecretKeySpec("my-secret-key".getBytes(), "HmacSHA256");
  Mac mac = Mac.getInstance("HmacSHA256");
  mac.init(key);
  byte[] result = mac.doFinal("message".getBytes());
  ```

- 常见算法：HmacMD5、HmacSHA1、HmacSHA256、HmacSHA512
- 应用场景：AWS API签名、JWT签名（HS256）、OAuth消息验证、Webhook签名验证

# 4. 对称加密算法

## 4.1. 算法说明

| 算法 | 密钥长度    | 工作模式             | 填充模式                                |
| :--- | :---------- | :------------------- | :-------------------------------------- |
| DES  | 56/64       | ECB/CBC/PCBC/CTR/... | NoPadding/PKCS5Padding/...              |
| AES  | 128/192/256 | ECB/CBC/PCBC/CTR/... | NoPadding/PKCS5Padding/PKCS7Padding/... |
| IDEA | 128         | ECB                  | PKCS5Padding/PKCS7Padding/...           |

## 4.2. 示例

- AES加密示例（Java，CBC模式）：
  ```java
  import javax.crypto.Cipher;
  import javax.crypto.spec.IvParameterSpec;
  import javax.crypto.spec.SecretKeySpec;
  import java.util.Base64;

  public class AESExample {
      // AES密钥长度必须为16/24/32字节
      private static final String KEY = "1234567890abcdef";
      // CBC模式需要16字节的IV向量
      private static final String IV = "abcdef1234567890";

      public static String encrypt(String plainText) throws Exception {
          SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
          IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
          byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
          return Base64.getEncoder().encodeToString(encrypted);
      }

      public static String decrypt(String cipherText) throws Exception {
          SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
          IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
          byte[] decoded = Base64.getDecoder().decode(cipherText);
          return new String(cipher.doFinal(decoded), "UTF-8");
      }
  }
  ```

- 注意事项：
  - ECB模式不安全（相同明文块产生相同密文块），生产环境应使用CBC或GCM模式
  - IV(初始化向量)每次加密应随机生成，并与密文一起传输
  - GCM模式提供认证加密（AEAD），既加密又防篡改，是当前推荐模式

# 5. 口令加密算法

## 5.1. 说明

- 口令加密算法(PBE, Password Based Encryption)：
  - 用户输入的口令（密码）通常不能直接作为AES等算法的密钥，因为长度和随机性不够
  - PBE算法通过口令+随机salt，经过多次迭代哈希运算，派生出符合要求的密钥
  - 常见算法：PBKDF2、bcrypt、scrypt、Argon2

- 工作原理：
  - 将用户口令和随机salt拼接
  - 经过数千到数十万次迭代哈希运算（故意增加计算耗时）
  - 输出固定长度的密钥，可用于后续的对称加密
  - 迭代次数越多，暴力破解越困难

- 与直接使用哈希的区别：
  - 直接 `SHA256(password)` 计算太快，GPU每秒可计算数十亿次
  - PBE故意设计为慢速计算，有效抵抗暴力破解和GPU加速攻击

## 5.2. 示例

- Java PBKDF2示例：
  ```java
  import javax.crypto.SecretKeyFactory;
  import javax.crypto.spec.PBEKeySpec;

  char[] password = "user-password".toCharArray();
  byte[] salt = SecureRandom.getInstanceStrong().generateSeed(16);
  // iterations=10000, keyLength=256
  PBEKeySpec spec = new PBEKeySpec(password, salt, 10000, 256);
  SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
  byte[] derivedKey = factory.generateSecret(spec).getEncoded();
  ```

- 应用场景：用户密码存储、文件加密密钥派生、钱包密码保护

# 6. 密钥交换算法

## 6.1. 说明

- 密钥交换算法(Key Exchange)解决的问题：
  - 对称加密要求双方持有相同的密钥，但在不安全的网络中如何安全地传递密钥？
  - 密钥交换算法允许双方在公开信道上协商出一个共享密钥，而窃听者无法得知

- DH(Diffie-Hellman)密钥交换算法：
  - 最经典的密钥交换算法，1976年由Diffie和Hellman提出
  - 基于离散对数问题的困难性
  - 流程：
    - 双方约定公开参数：大素数p和生成元g
    - Alice生成私钥a，计算 A = g^a mod p 发送给Bob
    - Bob生成私钥b，计算 B = g^b mod p 发送给Alice
    - Alice计算密钥：s = B^a mod p
    - Bob计算密钥：s = A^b mod p
    - 双方得到相同的 s，窃听者只知道 A、B、p、g，无法计算出 s

- 注意：DH算法本身不能防止中间人攻击，需要配合数字证书使用

## 6.2. 实现

- ECDH(椭圆曲线DH)：
  - 基于椭圆曲线的DH变体，使用更短的密钥达到同等安全强度
  - 256位ECDH的安全性约等于3072位经典DH
  - TLS 1.3中默认使用ECDHE（临时椭圆曲线DH）

- Java DH密钥交换示例：
  ```java
  import java.security.*;
  import javax.crypto.*;

  // Alice生成DH密钥对
  KeyPairGenerator aliceKpg = KeyPairGenerator.getInstance("DH");
  aliceKpg.initialize(2048);
  KeyPair aliceKp = aliceKpg.generateKeyPair();

  // Bob使用Alice的公钥参数生成自己的密钥对（略）
  // 双方通过KeyAgreement计算共享密钥
  KeyAgreement aliceKa = KeyAgreement.getInstance("DH");
  aliceKa.init(aliceKp.getPrivate());
  aliceKa.doPhase(bobPublicKey, true);
  byte[] sharedSecret = aliceKa.generateSecret();
  ```

# 7. 非对称加密算法

- 说明：非对称加密使用一对密钥：公钥(Public Key)和私钥(Private Key)
  - 公钥加密的数据只能用私钥解密，私钥加密的数据只能用公钥解密
  - 公钥可以公开分发，私钥必须严格保密
  - 解决了对称加密中密钥分发的难题

- 常见算法：
  | 算法    | 密钥长度     | 用途               | 安全性                |
  | :------ | :----------- | :----------------- | :-------------------- |
  | RSA     | 1024-4096    | 加密/签名          | 2048位以上安全        |
  | DSA     | 1024-3072    | 仅签名             | 逐步淘汰             |
  | ECC     | 256-521      | 加密/签名          | 256位约等于RSA 3072位 |
  | Ed25519 | 256          | 仅签名             | 高性能，推荐使用      |

- 与对称加密的配合使用：
  - 非对称加密速度比对称加密慢1000倍以上
  - 实际应用中常用"信封加密"：用非对称加密传递对称密钥，再用对称密钥加密数据
  - HTTPS的TLS握手就是这种模式：RSA/ECDH交换AES密钥，AES加密实际数据

- RSA加密Java示例：
  ```java
  KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
  kpg.initialize(2048);
  KeyPair kp = kpg.generateKeyPair();

  // 公钥加密
  Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
  cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
  byte[] encrypted = cipher.doFinal("Hello".getBytes());

  // 私钥解密
  cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
  byte[] decrypted = cipher.doFinal(encrypted);
  ```

# 8. 签名算法

- 说明：数字签名用于验证数据的完整性和发送者身份
  - 发送方用私钥对数据的哈希值进行加密，生成签名
  - 接收方用发送方的公钥解密签名，对比数据哈希值是否一致
  - 签名能证明：数据未被篡改 + 确实来自私钥持有者

- 签名流程：
  - 签名：`signature = encrypt(privateKey, hash(data))`
  - 验签：`hash(data) == decrypt(publicKey, signature)`

- 常见签名算法：
  - RSA签名：RSA-SHA256，广泛用于SSL证书和代码签名
  - DSA：仅用于签名，不能加密，逐渐被淘汰
  - ECDSA：基于椭圆曲线的签名算法，Bitcoin使用的签名方案
  - Ed25519：高性能椭圆曲线签名，SSH密钥和TLS中推荐使用

- Java签名示例：
  ```java
  // 用私钥签名
  Signature sig = Signature.getInstance("SHA256withRSA");
  sig.initSign(privateKey);
  sig.update("message".getBytes());
  byte[] signature = sig.sign();

  // 用公钥验签
  sig.initVerify(publicKey);
  sig.update("message".getBytes());
  boolean valid = sig.verify(signature);
  ```

- 应用场景：HTTPS证书验证、JWT签名、代码签名、区块链交易签名、电子合同

# 9. 数字证书

- Https 的连接与中间人攻击

- 问题背景：
  - 非对称加密和签名算法解决了加密和身份验证问题
  - 但如何确保你拿到的公钥确实属于目标服务器，而不是中间人伪造的？
  - 数字证书就是解决公钥信任问题的方案

- 证书体系（PKI）：
  - CA(Certificate Authority)：受信任的证书颁发机构，如DigiCert、Let's Encrypt
  - CA用自己的私钥对服务器的公钥+域名信息进行签名，生成数字证书
  - 浏览器/操作系统内置了受信任CA的根证书（公钥）
  - 验证链：根CA -> 中间CA -> 服务器证书（证书链）

- 证书包含的信息：
  - 颁发者（CA名称）
  - 有效期（起止时间）
  - 持有者信息（域名、组织等）
  - 持有者的公钥
  - CA的数字签名

- HTTPS连接流程：
  - 1.客户端发起连接，服务器返回数字证书
  - 2.客户端用CA公钥验证证书签名，确认证书合法且未过期
  - 3.客户端取出服务器公钥，通过密钥交换算法协商对称密钥
  - 4.后续通信使用对称密钥加密

- 中间人攻击(MITM)：
  - 攻击者拦截通信，向客户端出示伪造证书
  - 防御：客户端验证证书链，伪造证书无法通过CA签名验证
  - 如果用户手动信任了恶意CA根证书（如企业代理），则防御失效

# 10. uuid算法

## 10.1. uuid各版本

> 版本 1/2 适用于需要高度唯一性且无需重复的场景；
> 版本 3/5 适用于一定范围内唯一且需要或可能会重复生成UUID的环境下；
> 版本 4 适用于对唯一性要求不太严格且追求简单的场景。

### 10.1.1. 基于时间

- UUID Version 1：基于时间戳和MAC地址生成
  - 组成：当前时间戳（精确到100纳秒） + 时钟序列 + 节点ID（通常是MAC地址）
  - 优点：生成速度快，在分布式环境中无需协调即可保证唯一性
  - 缺点：包含MAC地址，可能泄露设备信息；时间回拨可能导致重复
  - 格式中时间戳被拆分到不同位置，不能直接按字典序排列

### 10.1.2. 分布式安全

- UUID Version 2：基于DCE安全的UUID
  - 在Version 1的基础上，将时钟序列替换为本地域标识符（如POSIX UID/GID）
  - 主要用于DCE(Distributed Computing Environment)安全体系
  - 实际使用非常少，很多UUID库并未实现此版本
  - 时间精度降低（约7分钟级别），同一节点短时间内可能重复

### 10.1.3. 基于namespace(MD5)

- UUID Version 3：基于命名空间和名称的MD5哈希
  - 输入：一个命名空间UUID + 一个名称字符串
  - 算法：对 "命名空间UUID + 名称" 计算MD5，取其中128位作为UUID
  - 特点：相同的命名空间+名称总是生成相同的UUID（确定性）
  - 适用于需要根据名称生成可重复UUID的场景，如根据URL生成唯一标识

### 10.1.4. 基于随机数

- UUID Version 4：基于随机数生成
  - 122位由随机数填充（128位减去4位版本号和2位变体标识）
  - 碰撞概率极低：生成约2.71 * 10^18个UUID后碰撞概率约50%
  - 优点：实现简单，无需网络和时钟，不泄露任何信息
  - 缺点：完全随机无序，不适合作为数据库主键（B+树索引性能差）
  - 是目前使用最广泛的UUID版本

### 10.1.5. 基于namespace(SHA1)

- UUID Version 5：基于命名空间和名称的SHA1哈希
  - 与Version 3原理相同，但使用SHA1代替MD5
  - SHA1比MD5更安全，碰撞概率更低
  - 同样是确定性的：相同输入总是产生相同UUID
  - 推荐在需要基于名称生成UUID的场景中使用V5替代V3

## 10.2. GUID

- GUID有两种解释：
  - 就是UUID
  - 特指微软对UUID标准的实现

# 11. 参考资料

<!-- TODO: 加密算法。有时间继续整理一下吧 -->

- [关于加密、证书的那些事](https://www.cnblogs.com/sewain/p/14250884.html)
- [廖雪峰-加密与安全](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943717668160)
- [hashids库说明](https://zhuanlan.zhihu.com/p/32671455)
  - [Cryptanalysis of hashids](http://carnage.github.io/2015/08/cryptanalysis-of-hashids)
- [HTTPS 证书和中间人攻击的原理](https://cloud.tencent.com/developer/article/1900287)
- [LEB128编码格式](http://gttiankai.github.io/2016/06/30/leb128%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F/)
