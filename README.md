# 绿色交易 - 植物二手交易平台

一个专注于绿色植物二手交易的Android应用。

## 项目成员介绍
### 冯家诗
学号：233401050208
专业：软件工程
技术擅长：
参加过的竞赛或参与过的项目：
### 王玲玲
学号：233401050202
专业：软件工程
技术擅长：
参加过的竞赛或参与过的项目：
### 王俊一
学号：233401050207
专业：软件工程
技术擅长：
参加过的竞赛或参与过的项目：
### 胡雪灵
学号：233401050203
专业：软件工程
技术擅长：
参加过的竞赛或参与过的项目：

## 项目结构

```
app/
├── src/
│   └── main/
│       ├── java/com/greendrading/app/
│       │   ├── MainActivity.kt                    # 主活动
│       │   ├── ui/
│       │   │   ├── home/                         # 首页模块
│       │   │   ├── shop/                         # 购物模块
│       │   │   ├── publish/                      # 发布模块
│       │   │   │   ├── PublishFragment.kt       # 发布主页
│       │   │   │   ├── ShareGoodFindsFragment.kt # 晒好物
│       │   │   │   ├── QuickSellFragment.kt     # 快速出售
│       │   │   │   ├── ListingFragment.kt       # 自主保管
│       │   │   │   └── ConsignmentFragment.kt   # 寄卖服务
│       │   │   ├── message/                      # 消息模块
│       │   │   └── profile/                      # 个人中心模块
│       │   └── data/                             # 数据层
│       └── res/
│           ├── layout/                           # 布局文件
│           ├── drawable/                         # 图标资源
│           ├── navigation/                       # 导航配置
│           ├── values/                           # 资源文件
│           └── menu/                             # 菜单文件
```

## 版本历史

### v1.0.2 (2024-03-07)
- 重构发布模块，采用纯Fragment架构
- 将普通挂售功能重命名为"自主保管"
- 优化UI交互，添加返回按钮
- 统一按钮样式
- 修复资源文件命名规范
- 添加矢量图标资源

### v1.0.1 (2024-03-06)
- 更新包名为 com.greendrading.app
- 更新 Java 版本至 17
- 优化项目结构
- 修复包名相关的构建问题

### v1.0.0 (2024-03-05)
- 初始版本
- 实现底部导航栏，包含：首页、购物、发布、消息、我的五个主要功能
- 实现发布模块的四个子功能：晒好物、快速出售、普通挂售、寄卖服务
- 采用Fragment和Activity混合架构
- 使用Material Design设计规范
- 实现ViewBinding功能
- 添加基础布局和图标资源

### 技术栈
- Kotlin 1.9.22
- Android SDK 34
- AndroidX
- Material Design Components 1.11.0
- ViewBinding
- Navigation Component 2.7.7
- ViewModel & LiveData 2.7.0
- Coroutines 1.7.3
- Glide 4.16.0

### 环境要求
- Android Studio Hedgehog | 2023.1.1
- JDK 17
- Gradle 8.2.2
- 最低支持Android API 26 (Android 8.0)
- 目标Android API 34 (Android 14)

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
- 资源文件命名使用小写字母和下划线
- 使用矢量图标（Vector Drawable）代替位图

## 待办事项
- [ ] 实现用户认证系统
- [ ] 添加商品搜索功能
- [ ] 实现商品详情页
- [ ] 添加支付系统
- [ ] 实现消息推送
- [ ] 添加用户评价系统
- [ ] 优化UI/UX设计
- [ ] 添加单元测试和UI测试
- [ ] 优化Fragment间的导航
- [ ] 实现深色主题支持

## 贡献指南
1. Fork项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启Pull Request

## 许可证
本项目采用MIT许可证 - 查看 [LICENSE](LICENSE) 文件了解详情 
