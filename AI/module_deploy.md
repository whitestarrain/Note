# 模型轻量化

## 稀疏

通过将模型权重中的部分参数置零来减少计算量和存储需求。

## 剪枝

移除对模型性能贡献较小的神经元或通道，降低模型复杂度。

## 蒸馏

用大模型（教师）的输出指导小模型（学生）训练，使小模型获得接近大模型的性能。

# 边缘部署

## libtorch

PyTorch的C++前端，用于在不依赖Python环境的情况下部署模型。

## tensorRT

NVIDIA推出的高性能推理优化器，通过层融合、量化等技术加速模型推理。

# 参考资料

- [ ] [wang-xinyu/tensorrtx](https://github.com/wang-xinyu/tensorrtx)
  > 各种网络的tensorRT实现
  - [yolov5加速教程](https://www.mdnice.com/writing/328959e2439045849a06933c6380e148)
- [ ] [yolov3-channel-and-layer-pruning](https://github.com/tanluren/yolov3-channel-and-layer-pruning)
- [ ] [yolov3稀疏，剪枝，蒸馏扩展到yolov5](https://github.com/ZJU-lishuang/yolov5_prune)


