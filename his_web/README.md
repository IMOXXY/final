# his_web

医院信息系统（HIS）**前端**：基于 **Vue 2** 与 **Vue CLI 4** 的单页应用，与后端 **`his_api`**（Spring Boot）联调使用。

## 技术栈

- Vue 2、Vue Router、Vuex  
- Element UI / Element Plus、ECharts、Axios  
- 开发服务器默认端口 **`8080`**（见 `vue.config.js`）

## 环境要求

- **Node.js**：建议 **LTS**（如 18 / 20 / 22）；需自带 `npm`  
- **浏览器**：现代浏览器（Chrome / Edge 等）  
- **后端**：请先启动 **`his_api`**。前端请求基地址为 **`http://127.0.0.1:5003`**（见 `src/request.js`）

更完整的安装说明（含 Windows / macOS、与后端同级目录结构）见仓库内：**`HIS课件/环境安装.md`**。  
MySQL 建库与示例数据见：**`HIS课件/本地HIS数据库.md`**。

## 快速开始

```bash
# 安装依赖
npm install

# 开发模式（热更新）
npm run serve
```

浏览器访问终端里提示的地址（一般为 `http://localhost:8080`）。

```bash
# 生产构建（输出到 dist/）
npm run build
```

## 与后端的关系

| 项目     | 说明        | 默认地址              |
|----------|-------------|------------------------|
| **本仓库** | 前端        | `http://localhost:8080` |
| **his_api** | 后端 API    | `http://127.0.0.1:5003` |

请将与本仓库**同级**克隆或放置 **`his_api`**，先启动 MySQL 与后端，再启动本前端。

## 目录说明（简要）

- `src/`：页面、路由、状态、接口封装  
- `src/request.js`：Axios 实例与拦截器（Token、401 等）  
- `HIS课件/`：课程文档、SQL 与离线安装包说明（`azb` 若放在上一级目录，见环境安装文档）

## 常见问题

- **接口 404 / 连不上**：确认 **`his_api` 已启动**，且本机可访问 `5003` 端口。  
- **依赖安装失败**：可切换 npm 镜像后重试 `npm install`。  
- **数据库**：表结构与初始化以课程文档及后端 `ddl-auto` 配置为准；勿依赖本 README 作为唯一 SQL 来源。

## 许可与课程使用

仅供课程教学与本地实验使用；生产环境部署需自行补齐安全与配置审计。
