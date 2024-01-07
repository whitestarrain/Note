# 1. 计算机视觉

## 1.1. 基本概念

## 1.2. 研究方向

- 语义感知
  - 分类：属性，物体分类等等
    - 卷积神经网络 CNN
  - 检测：人脸，物体等等。
    - 区域卷积神经网络 R-CNN
  - 识别：物体，车牌，人体等等。
  - 分割：实例分割
    - 全卷积神经网络 FCN
  - 检索：以文搜图，以图搜文，以图搜图
  - 语言：图片描述，图片问答
    - 循环神经网络 RNN
- 几何属性(VR)
  - 3D 建模
  - 双目视觉
  - 增强现实

## 1.3. 环境

- python
- pytorch
- opencv
  - opencv-python
  - opencv-contrib-python
- caffe

# 2. 计算机视觉基础

## 2.1. 图像预处理

### 2.1.1. 图像显示与存储原理

#### 2.1.1.1. 颜色空间

- RGB 颜色空间：加法混色，三个维度，最常用
  > ![shijue-1](./image/shijue-1.png)
- CMY(K)颜色空间：减法混色，四个维度，用于印刷
  > ![shijue-2](./image/shijue-2.png)
- HSV 颜色空间
  > ![shijue-3](./image/shijue-3.png)
- CIE-XYZ 颜色空间
  > ![shijue-4](./image/shijue-4.png)

#### 2.1.1.2. 存储原理

- RGB 与灰度化
  - ![shijue-5](./image/shijue-5.png)

### 2.1.2. 图像处理

#### 2.1.2.1. 概述

- 目标

  - 改善图像的视觉效果
  - 转换为更适合于人或机器分析处理的形式
  - 突出对人或机器分析有意义的信息
  - 抑制无用信息，提高图像的使用价值
  - 具体的说： 包括图像锐化，平滑、去噪，灰度调整（对比度增强）

- 方法
  - 空间域处理
    - 点运算：HE,CLAHE
    - 形态学运算:膨胀，腐蚀
    - 临域运算：卷积，金字塔
  - 频率域处理
    - 傅里叶变换
    - 小波变换

#### 2.1.2.2. 直方图均衡化

- 概述
  - 直方图均衡化是指：利用图像直方图对对比度进行调整的方法。
  - 直方图均衡化通常用来增加许多图像的局部对比度，尤其是当图像的有用数据的对比度 相当接近的时候。
  - 直方图均衡化以后，亮度可以更好地在直方图上分布。
    - 这样就可以用于增强局部的对比 度而不影响整体的对比度，
    - 直方图均衡化通过有效地扩展常用的亮度来实现这种功能。
- 方式
  - 经典算法
    > ![shijue-6](./image/shijue-6.png)
  - 自适应直方图均衡（AHE）
    > ![shijue-7](./image/shijue-7.png)
  - CLAHE
    > ![shijue-8](./image/shijue-8.png)>
    >
    > ![shijue-9](./image/shijue-9.png)
    >
    > ![shijue-10](./image/shijue-10.png)

#### 2.1.2.3. 形态学处理

- 膨胀：是图像中的高亮部分进行膨胀，类似于领域扩张。
- 腐蚀：是原图的高亮部分被腐蚀，类似于领域被蚕食。
  > ![shijue-11](./image/shijue-11.png)
- 开运算：先腐蚀再膨胀，可以去掉目标外的孤立点。
- 闭运算：先膨胀再腐蚀，可以去掉目标内的孔。

> 通常，当有噪声的图像用阀值二值化后，所得到的边界是很不平滑的，物体区域具有一些错判的孔洞，背景区域散布着一些小的噪声物体，连续的开和闭运算可以显著的改善这种情况。

#### 2.1.2.4. 空间域处理：卷积和池化

> 深度学习笔记中有详细说明。

#### 2.1.2.5. 卷积的应用（平滑、边缘检测、锐化等）

- 平均滤波
  > ![shijue-12](./image/shijue-12.png)
- 中值滤波
  > ![shijue-13](./image/shijue-13.png)
- 高斯滤波
  > ![shijue-14](./image/shijue-14.png)
  >
  > ![shijue-15](./image/shijue-15.png)
- Prewitt 滤波：检测水平和垂直边缘
  > ![shijue-16](./image/shijue-16.png)
- Sobel 滤波：检测水平和垂直边缘
  > ![shijue-17](./image/shijue-17.png)
- Laplacian 滤波：团块检测和边缘检测
  > ![shijue-18](./image/shijue-18.png)
  >
  > ![shijue-19](./image/shijue-19.png)
- 其他滤波
  > ![shijue-20](./image/shijue-20.png)

#### 2.1.2.6. 频率域处理：傅里叶变换、小波变换

- 高斯金字塔

  > ![shijue-22](./image/shijue-22.png)
  >
  > ![shijue-23](./image/shijue-23.png)

- 拉普拉斯金字塔

  > ![shijue-24](./image/shijue-24.png)

- 快速傅里叶变换加快卷积
  > ![shijue-25](./image/shijue-25.png)
  >
  > ![shijue-21](./image/shijue-21.png)
  >
  > ![shijue-26](./image/shijue-26.png)
- 短时傅里叶变换
- 小波变换

## 2.2. 图像特征及描述

### 2.2.1. 颜色特征

#### 2.2.1.1. 量化颜色直方图

![shijue-27](./image/shijue-27.png)

![shijue-28](./image/shijue-28.png)

#### 2.2.1.2. 聚类颜色直方图

![shijue-29](./image/shijue-29.png)

![shijue-30](./image/shijue-30.png)

#### 2.2.1.3. 比较

- 二次式距离
  > ![shijue-31](./image/shijue-31.png)

### 2.2.2. 几何特征

#### 2.2.2.1. 边缘 edge

- 一阶导数取到极值的地方为边缘
  > ![shijue-32](./image/shijue-32.png)
- 有一些噪声点也能取到极值，因此首先要去噪
  > ![shijue-33](./image/shijue-33.png)
  >
  > ![shijue-34](./image/shijue-34.png)
- 斜边缘
  > ![shijue-35](./image/shijue-35.png)

#### 2.2.2.2. 关键点 corner

- 关键点
  > ![shijue-36](./image/shijue-36.png)
- 作用
  > ![shijue-37](./image/shijue-37.png)
- 种类
  - Harris 角点
    > ![shijue-38](./image/shijue-38.png)
    >
    > ![shijue-39](./image/shijue-39.png)
    >
    > ![shijue-40](./image/shijue-40.png)
    >
    > ![shijue-41](./image/shijue-41.png)
  - FAST 角点检测
    > ![shijue-42](./image/shijue-42.png)
    >
    > ![shijue-43](./image/shijue-43.png)

#### 2.2.2.3. 斑点 blob

![shijue-44](./image/shijue-44.png)

![shijue-45](./image/shijue-45.png)

![shijue-46](./image/shijue-46.png)

### 2.2.3. 基于关键点的特征描述子

#### 2.2.3.1. SIFT

- 特点：
  > ![shijue-47](./image/shijue-47.png)
- 计算步骤：
  > ![shijue-48](./image/shijue-48.png)
- 使用 DOG(高斯差分)代替 LOG

  > ![shijue-49](./image/shijue-49.png)
  >
  > ![shijue-50](./image/shijue-50.png)
  >
  > ![shijue-51](./image/shijue-51.png)

- 获取 DoG 空间极值点
  > ![shijue-52](./image/shijue-52.png)
  >
  > ![shijue-53](./image/shijue-53.png)>
- 关键点方向估计
  > ![shijue-54](./image/shijue-54.png)
  >
  > ![shijue-55](./image/shijue-55.png)

#### 2.2.3.2. SURF

- 预先知识
  > ![shijue-57](./image/shijue-57.png)
  >
  > ![shijue-58](./image/shijue-58.png)
- SURF 步骤
  > ![shijue-56](./image/shijue-56.png)
  >
  > ![shijue-59](./image/shijue-59.png)
  >
  > ![shijue-61](./image/shijue-61.png)
  >
  > ![shijue-62](./image/shijue-62.png)
- 总结
  > ![shijue-63](./image/shijue-63.png)
- 和 SIFT 区别
  > ![shijue-60](./image/shijue-60.png)

#### 2.2.3.3. ORB

- 说明
  > ![shijue-64](./image/shijue-64.png)
- 基本思路
  > ![shijue-65](./image/shijue-65.png)
  - BRIEF
    > ![shijue-66](./image/shijue-66.png)
    >
    > ![shijue-67](./image/shijue-67.png)
    >
    > ![shijue-68](./image/shijue-68.png)

#### 2.2.3.4. LBP

![shijue-69](./image/shijue-69.png)

![shijue-70](./image/shijue-70.png)

![shijue-71](./image/shijue-71.png)

![shijue-72](./image/shijue-72.png)

#### 2.2.3.5. Gabor

![shijue-73](./image/shijue-73.png)

![shijue-74](./image/shijue-74.png)

![shijue-75](./image/shijue-75.png)

### 2.2.4. 其他特征提取

#### 2.2.4.1. LBP

#### 2.2.4.2. Gabor

## 2.3. 未有深度学习之前

### 2.3.1. 图像分割

#### 2.3.1.1. 说明

- 所谓图像分割指的是根据灰度、颜色、纹理和形状等特征把图像划分成若干互不交迭的 区域，
- 并使这些特征在同一区域内呈现出相似性，而在不同区域间呈现出明显的差异性。
- 经典的数字图像分割算法一般是基于灰度值的两个基本特性之一：不连续性和相似性。

#### 2.3.1.2. 基于阀值

```
间值法的基本思想是基于图像的灰度特征来计
算一个或多个灰度阙值，并将图像中每个像素
的灰度值与國值相比较，最后将像素根据比较
结果分到合适的类别中。
```

![shijue-76](./image/shijue-76.png)

#### 2.3.1.3. 基于边缘

```
所谓边缘是指图像中两个不同区域的边界线上
连续的像素点的集合，是图像局部特征不连续
性的反映，体现了灰度、颜色、纹理等图像特
性的突变。
```

![shijue-77](./image/shijue-77.png)

#### 2.3.1.4. 基于区域

```
此类方法是将图像按照相似性准则分成不同的
区域，主要包括种子区域生长法、区域分裂合
并法和分水岭法等几种类型。
```

- 区域生长

  - 说明
    - 区域生长是根据一种事先定义的准则将像素 或者子区域聚合成更大区域的过程，
    - 并且要充分保证分割后的区域满足以下条件：内部 连通、互斥，且各子集的并集能构成全集。
    - 区域生长的条件实际上就是根据像素灰度间 的连续性而定义的一些相似性准则，而区域生长停止的条件则是一个终止规则。
  - 步骤
    > ![shijue-78](./image/shijue-78.png)
    - 算法定义了最大像素灰度值距离，
    - 当待加入像素点的灰度值和已经分割好的区域所有像素点的平均灰度值的差的绝对值不大于最大像素灰度值距离时，
    - 该像素点加入到已经分割到的区 域。相反，则区域生长算法停止。

- 分水岭算法
  - 说明
    - 分水岭算法是一种图像区域分割法，分水岭 算法可以将图像中的边缘转化成“山脉”，将 均匀区域转化为“山谷”，这样有助于分割目 标。
    - 在分割的过程中，它会把跟临近像素间的相 似性作为重要的参考依据，
    - 从而将在空间位置上相近并且灰度值相近（求梯度）的像素 点互相连接起来构成一个封闭的轮廓。
  - 步骤
    > ![shijue-79](./image/shijue-79.png)

#### 2.3.1.5. 基于图论

```
此类方法把图像分割问题与图的最小割（min
cut)问题相关联。首先将图像映射为带权无向
图，图中每个节点对应于图像中的每个像素，
每条边的权值表示了相邻像素之间在灰度、颜
色或纹理方面的非负相似度。
```

- Graph Cuts 分割
  > ![shijue-80](./image/shijue-80.png)
  >
  > ![shijue-81](./image/shijue-81.png)
  >
  > ![shijue-82](./image/shijue-82.png)
  >
  > ![shijue-83](./image/shijue-83.png)
- GrubCut 分割（基于 Graph Cuts）
  > ![shijue-84](./image/shijue-84.png)
  >
  > ![shijue-86](./image/shijue-86.png)
  >
  > ![shijue-85](./image/shijue-85.png)
  >
  > ![shijue-87](./image/shijue-87.png)
  >
  > ![shijue-88](./image/shijue-88.png)

### 2.3.2. 人脸检测

#### 2.3.2.1. Haar 级联分类器

![shijue-89](./image/shijue-89.png)

![shijue-90](./image/shijue-90.png)

![shijue-91](./image/shijue-91.png)

![shijue-92](./image/shijue-92.png)

![shijue-93](./image/shijue-93.png)

![shijue-94](./image/shijue-94.png)

![shijue-95](./image/shijue-95.png)

![shijue-96](./image/shijue-96.png)

![shijue-97](./image/shijue-97.png)

#### 2.3.2.2. dlib

### 2.3.3. 行人检测

#### 2.3.3.1. HOG+SVM

![shijue-98](./image/shijue-98.png)

![shijue-99](./image/shijue-99.png)

![shijue-100](./image/shijue-100.png)

![shijue-101](./image/shijue-101.png)

- 总结
  > ![shijue-102](./image/shijue-102.png) 
  >
  > ![shijue-103](./image/shijue-103.png) 

![shijue-104](./image/shijue-104.png)

#### 2.3.3.2. DPM

![shijue-105](./image/shijue-105.png)

![shijue-106](./image/shijue-106.png)

![shijue-107](./image/shijue-107.png)

![shijue-108](./image/shijue-108.png)

![shijue-109](./image/shijue-109.png)

- 流程总结
  > ![shijue-110](./image/shijue-110.png) 
  >
  > ![shijue-111](./image/shijue-111.png) 

# 3. 深度学习基础

> 看深度学习笔记

# 4. 图像分类

# 5. 图像检测

## 5.1. 相似检索

- 颜色，纹理，形状
- 局部特征点
- 词包（Bag of Visual Word）


## 5.2. 索引加速

- KD-tree
- 局部敏感哈希（Locality Sensitive Hash）

## 5.3. 应用案例-CBIR 的应用

# 视觉识别

- 不同视觉识别任务的区别
  > ![shijue-112](./image/shijue-112.png) 
  - 语义分割中，不同的狗相同颜色
  - 实例分割中，不同的狗不同颜色

- 语义分割
  > ![shijue-115](./image/shijue-115.png) 
  - 如果一直保持图片大小，参数量过大，gpu现存直接爆掉
  - 因此有了上采样和下采样

- 上采样
  - 补0
  - ...
  - 转置卷积(一维例子)
    > ![shijue-113](./image/shijue-113.png) 
    >
    > ![shijue-114](./image/shijue-114.png) 

- 目标检测:单目标(分类+定位)
  > ![shijue-116](./image/shijue-116.png) 
  - 训练步骤
    - 前半部分拿imagenet预训练模型
    - 训练全连接层
    - 整体训练--微调
  - 缺陷：必须限定目标个数

- 目标检测：多目标
  - R-CNN
    > ![shijue-117](./image/shijue-117.png) 
    > ![shijue-118](./image/shijue-118.png) 
  - Fast R-CNN
    > ![shijue-119](./image/shijue-119.png) 
    > > Fast R-CNN 与R-CNN对比
    >
    > ![shijue-120](./image/shijue-120.png) 
    > > 如果讲候选区域投影到特征图上
    >
    > ![shijue-121](./image/shijue-121.png) 
    > > 改进，使用双线性插值
  - 问题:候选区域太过耗时
    > ![shijue-122](./image/shijue-122.png) 
  - Faster R-CNN:用区域建议网络找候选区域
    > ![shijue-123](./image/shijue-123.png) 
    > ![shijue-124](./image/shijue-124.png) 
    > ![shijue-125](./image/shijue-125.png) 
    > ![shijue-126](./image/shijue-126.png) 
    > ![shijue-127](./image/shijue-127.png) 


  - 去掉了selective search，速度支线提升
    > ![shijue-128](./image/shijue-128.png) 
  - 一阶段检查
    > ![shijue-129](./image/shijue-129.png) 
  - 影响精度的因素
    > ![shijue-130](./image/shijue-130.png) 

- 实例分割
  - Mask R-CNN
    > ![shijue-131](./image/shijue-131.png) 
    > ![shijue-132](./image/shijue-132.png) 

# YOLO

- archor_num_x*archor_num_y*(num_classes+4+1)
  - 1:是否有物体
  - 4:x,y,width,height

# DeepSort
