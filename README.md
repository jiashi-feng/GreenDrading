# GreenDrading Android 应用

GreenDrading 是一个绿色交易平台的 Android 客户端应用。

## 技术栈

### 基础环境
- 编译 SDK: 34
- 最小 SDK: 26
- 目标 SDK: 34
- Java 版本: 17
- Kotlin 版本: 1.9.22
- Gradle 版本: 8.2.2

### 主要依赖
- AndroidX Core KTX: 1.12.0
- AppCompat: 1.6.1
- Material Design: 1.11.0
- ConstraintLayout: 2.1.4

### 架构组件
- Navigation Component: 2.7.7
- ViewModel & LiveData: 2.7.0
- Hilt (依赖注入): 2.48
- Kotlin Coroutines: 1.7.3

### 图片加载
- Glide: 4.16.0

### 测试框架
- JUnit: 4.13.2
- AndroidX Test: 1.1.5
- Espresso: 3.5.1

## 主要功能
- 晒好物
- 拍图自己卖
- 挂售
- 寄售

## 开发环境要求
- Android Studio Hedgehog | 2023.1.1 或更高版本
- JDK 17
- Android SDK 34
- Kotlin 1.9.22

## 构建说明
1. 确保已安装 JDK 17
2. 在 Android Studio 中打开项目
3. 等待 Gradle 同步完成
4. 点击 "Run" 按钮运行应用

## 版本历史
- v1.0.0
  - 初始版本
  - 完成基础功能框架搭建
  - 实现发布功能模块

## 功能特点

### 1. 多样化的发布功能
- **晒好物**: 分享植物养护心得和日常
  - 支持多图片/视频上传（最多9张）
  - 文字描述和标签功能
  - 隐私设置（公开/仅自己可见）
  - 草稿保存功能

- **拍图自己卖**: 独立销售模式
  - 商品信息管理
  - 价格设置
  - 多图片上传（最多6张）
  - 物流方式选择
  - 服务费说明

- **挂售模式**: 平台托管交易
  - 交易流程展示
  - 核心优势说明
  - 服务费率说明
  - 商品信息管理

- **寄售模式**: 分类选择
  - 植物分类浏览
  - 搜索功能
  - 佣金比例显示
  - 详细信息展示

## 技术架构

### 架构模式
- MVVM (Model-View-ViewModel)架构
- Android Jetpack组件
- Material Design界面设计

### 核心依赖
```gradle
// Navigation Component
implementation "androidx.navigation:navigation-fragment-ktx:2.7.7"
implementation "androidx.navigation:navigation-ui-ktx:2.7.7"

// ViewModel & LiveData
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.7.0"

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'

// Glide for image loading
implementation 'com.github.bumptech.glide:glide:4.16.0'
```

### 项目结构
```
app/src/main/
├── java/com/greendrading/app/
│   └── ui/publish/
│       ├── PublishActivity.kt              # 主发布页面
│       ├── PublishTabsAdapter.kt           # 标签页适配器
│       ├── ShareGoodFindsFragment.kt       # 晒好物页面
│       ├── ShareGoodFindsViewModel.kt      # 晒好物ViewModel
│       ├── SellOwnItemFragment.kt          # 拍图自己卖页面
│       ├── SellOwnItemViewModel.kt         # 拍图自己卖ViewModel
│       ├── DirectSaleInfoFragment.kt       # 挂售详情页面
│       ├── DirectSaleInfoViewModel.kt      # 挂售详情ViewModel
│       ├── ConsignmentSelectionFragment.kt # 寄售选择页面
│       ├── ConsignmentSelectionViewModel.kt# 寄售选择ViewModel
│       ├── MediaPreviewAdapter.kt          # 媒体预览适配器
│       └── PlantsAdapter.kt                # 植物列表适配器
└── res/
    ├── layout/
    │   ├── activity_publish.xml            # 主发布页面布局
    │   ├── fragment_share_good_finds.xml   # 晒好物页面布局
    │   ├── fragment_sell_own_item.xml      # 拍图自己卖页面布局
    │   ├── fragment_direct_sale_info.xml   # 挂售详情页面布局
    │   ├── fragment_consignment_selection.xml # 寄售选择页面布局
    │   ├── item_media_preview.xml          # 媒体预览项布局
    │   └── item_plant.xml                  # 植物列表项布局
    ├── navigation/
    │   └── nav_publish.xml                 # 导航图配置
    └── values/
        └── colors.xml                      # 颜色资源

```

## 运行项目

### 环境要求
- Android Studio Hedgehog | 2023.1.1 或更高版本
- JDK 8 或更高版本
- Android SDK 34
- Gradle 8.0 或更高版本

### 运行步骤
1. 克隆项目到本地：
```bash
git clone https://github.com/jiashi-feng/GreenDrading.git
```

2. 在Android Studio中打开项目

3. 等待Gradle同步完成

4. 连接Android设备或启动模拟器

5. 点击"运行"按钮（▶）运行应用

### 注意事项
- 确保设备/模拟器API级别≥24（Android 7.0）
- 需要相机和存储权限
- 首次运行时需要网络连接以下载依赖

## TODO列表
- [ ] 实现图片选择和上传功能
- [ ] 实现草稿保存功能（使用Room数据库）
- [ ] 实现与服务器的数据交互
- [ ] 添加加载状态和错误处理
- [ ] 实现实际的植物数据API调用
- [ ] 完善导航逻辑

## 贡献指南
欢迎提交Issue和Pull Request。在提交PR之前，请确保：
1. 代码符合项目的编码规范
2. 新功能有适当的测试覆盖
3. 所有测试都能通过
4. 更新相关文档

## 许可证
[待添加许可证信息] 