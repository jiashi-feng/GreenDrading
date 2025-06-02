# 绿色交易 - 植物二手交易平台

一个专注于绿色植物二手交易的Android应用。

## 项目结构

```
app/
├── src/
│   └── main/
│       ├── java/com/example/greendrading/
│       │   ├── MainActivity.kt              # 主活动
│       │   ├── fragments/                   # Fragment组件
│       │   │   ├── HomeFragment.kt         # 首页
│       │   │   ├── ShopFragment.kt         # 购物
│       │   │   ├── PublishFragment.kt      # 发布
│       │   │   ├── MessageFragment.kt      # 消息
│       │   │   └── ProfileFragment.kt      # 个人中心
│       │   └── activities/                  # Activity组件
│       │       ├── ShareGoodFindsActivity.kt  # 晒好物
│       │       ├── QuickSellActivity.kt     # 快速出售
│       │       ├── NormalSellActivity.kt    # 普通挂售
│       │       └── ConsignmentActivity.kt   # 寄卖服务
│       └── res/
│           ├── layout/                      # 布局文件
│           ├── drawable/                    # 图标资源
│           ├── values/                      # 资源文件
│           └── menu/                        # 菜单文件
```

## 版本历史

### v1.0.0 (2024-03-xx)
- 初始版本
- 实现底部导航栏，包含：首页、购物、发布、消息、我的五个主要功能
- 实现发布模块的四个子功能：晒好物、快速出售、普通挂售、寄卖服务
- 采用Fragment和Activity混合架构
- 使用Material Design设计规范
- 实现ViewBinding功能
- 添加基础布局和图标资源

### 技术栈
- Kotlin 1.9.x
- Android SDK 34
- AndroidX
- Material Design Components
- ViewBinding
- Navigation Component
- ViewModel & LiveData
- Coroutines
- Glide

### 环境要求
- Android Studio Hedgehog | 2023.1.1
- JDK 17
- Gradle 8.2
- 最低支持Android API 26 (Android 8.0)

## 开发指南

### 构建项目
```bash
./gradlew clean build
```

### 运行测试
```bash
./gradlew test
```

### 代码规范
- 遵循Kotlin官方编码规范
- 使用ViewBinding替代findViewById
- Fragment通信使用ViewModel和LiveData
- 异步操作使用Coroutines
- 图片加载使用Glide

## 待办事项
- [ ] 实现用户认证系统
- [ ] 添加商品搜索功能
- [ ] 实现商品详情页
- [ ] 添加支付系统
- [ ] 实现消息推送
- [ ] 添加用户评价系统
- [ ] 优化UI/UX设计
- [ ] 添加单元测试和UI测试

## 贡献指南
1. Fork项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启Pull Request

## 许可证
本项目采用MIT许可证 - 查看 [LICENSE](LICENSE) 文件了解详情 