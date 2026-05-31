
# yolov5

## 基础模块

### Focus

Focus模块通过切片操作将图像信息从空间维度转移到通道维度，在不丢失信息的前提下降低分辨率。

### CONV

标准卷积模块，包含Conv2d + BatchNorm + SiLU激活函数的组合。

### Bottlenect

瓶颈结构，通过1x1卷积降维再升维，减少计算量的同时保持特征表达能力。

### C3

CSP Bottleneck模块，将特征图分为两部分分别处理后再合并，兼顾计算效率和梯度流。

### SPP

空间金字塔池化模块，通过多尺度池化操作融合不同感受野的特征信息。

## CBAM注意力机制

结合通道注意力和空间注意力的轻量级注意力模块，可即插即用地增强特征表达。

# 参考资料

- [yolov5 结构知识点解析](https://www.cnblogs.com/boligongzhu/p/15508249.html)
- [yolov5网络结构分析](https://www.cnblogs.com/xiaoheizi-12345/p/14287592.html)
- [YOLOV5代码解析](https://blog.csdn.net/Q1u1NG/article/details/107465061)


