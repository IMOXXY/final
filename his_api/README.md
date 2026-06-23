# his_api

医院信息系统（HIS）**后端 API**：**Spring Boot 3** 应用，提供 REST 接口，供前端 **`his_web`**（Vue）调用。

## 技术栈

- Java **17+**、Spring Boot **3.3.x**  
- Spring Web、Spring Data **JPA**  
- MySQL（**Connector/J**）  
- 其他：Lombok、Hutool、JWT（`java-jwt`）、支付宝 SDK（按业务启用）

## 环境要求

- **JDK 17 或以上**（需 `java`、`javac` 可用）  
- **MySQL 5.7 / 8.x**：本地创建库 **`his`**，账号密码与配置一致（见下）  
- **Maven**：可使用项目自带的 **Maven Wrapper**（`mvnw` / `mvnw.cmd`），无需单独安装 Maven

数据库初始化 SQL 可与前端仓库中 **`his_web/HIS课件/本地HIS数据库.md`** 对齐；完整环境步骤见同目录 **`环境安装.md`**（前后端、JDK、MySQL 等）。

## 配置说明

默认配置见 **`src/main/resources/application.properties`**：

| 项 | 默认值 |
|----|--------|
| 服务端口 | **5003** |
| 监听地址 | **0.0.0.0** |
| 数据源 URL | `jdbc:mysql://localhost:3306/his?serverTimezone=UTC` |
| 用户名 / 密码 | `root` / `123` |
| JPA `ddl-auto` | `none`（表结构由 SQL 或 DBA 维护） |

生产或联调请按需修改，**切勿**将弱密码用于公网。

支付宝相关占位项（`alipay.*`）需替换为真实参数后方可调用支付接口。

## 快速开始

```bash
# 进入本仓库根目录（与 his_web 同级）
cd his_api

# 查看 Wrapper 与 Java（macOS / Linux）
./mvnw -v

# 启动 Spring Boot（需 MySQL 已启动且已建库 his）
./mvnw spring-boot:run
```

**Windows**（在 `his_api` 目录下）：

```bat
mvnw.cmd -v
mvnw.cmd spring-boot:run
```

启动成功后，默认 API 根地址：**`http://localhost:5003`**（具体路径以 Controller 为准）。

停止：在运行终端中 **Ctrl + C**。

## 用 Visual Studio Code 运行（可选）

适合不习惯命令行的同学；仍需先安装 **JDK 17+**、**MySQL** 与 **`his`** 库。

1. **安装扩展**（扩展市场搜索并安装）  
   - **Extension Pack for Java**（发布者：Microsoft，内含 Language Support for Java、Maven、调试器等）  
   - （推荐）**Spring Boot Extension Pack**（Microsoft）：便于识别 Spring 项目、运行主类、查看 Bean 等。

2. **打开工程**  
   - **文件 → 打开文件夹**，选择 **`his_api` 仓库根目录**（含 `pom.xml`、`mvnw` 的那一层），不要只打开上一级 `HIS`。

3. **指定 JDK**  
   - 先**用鼠标点一下代码编辑区**，确保焦点在 VS Code / Cursor 窗口内（不要焦点在浏览器或其它 App）。  
   - 打开**命令面板**（任选一种，效果相同）：  
     - **macOS**：`Cmd + Shift + P`；若没反应，试 **`F1`**。  
     - **Windows / Linux**：`Ctrl + Shift + P` 或 **`F1`**。  
     - **菜单**：**查看 → 命令面板…**（英文界面为 **View → Command Palette…**）。  
   - 在命令面板里输入 **`Java: Configure Java Runtime`**，将 **Project JDKs** 设为 **17 或以上**。  
   - **`Cmd + Shift + P` 仍无反应时**：菜单 **Code → 设置 → 键盘快捷方式**（或 **文件 → 首选项 → 键盘快捷方式**），搜索 **`命令面板`** / **`Command Palette`**，看是否被改成别的快捷键或被占用；也可在 **系统设置 → 键盘 → 键盘快捷键** 里查 macOS 是否把该组合键留给别的功能。  
   - 若未识别 JDK：在设置中搜索 **`java.jdt.ls.java.home`**，填本机 JDK 安装路径（Windows 多为 `JAVA_HOME`，macOS 可为 `/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home` 等，以本机为准）。

   > **说明**：**Cursor** 基于 VS Code，一般同样支持 **`Cmd + Shift + P` / `F1`** 与上述菜单；若仅在 Cursor 中无效，可用菜单打开命令面板，或到 **Cursor → Settings → Keyboard Shortcuts** 检查快捷键。

4. **等待 Maven 导入**  
   - 右下角/状态栏会提示正在导入 Maven 项目，首次可能需几分钟并下载依赖，需联网。

5. **运行**  
   - 打开主类：  
     `src/main/java/com/his/SpringbootHisApplication.java`  
   - 在带 `public static void main` 的类上方点击 **Run | Debug**，或右键该文件 → **Run Java**。  
   - 若装了 **Spring Boot Dashboard**，也可在侧边栏 **Spring Boot** 中启动应用。

6. **查看日志**  
   - **终端**或 **DEBUG CONSOLE** 中出现 **Started SpringbootHisApplication** 且无数据库连接报错，即表示启动成功；端口仍为 **`5003`**。

停止：在 **RUN AND DEBUG** 工具栏点停止，或关闭调试会话。

## 与前端的关系

| 项目 | 说明 | 默认地址 |
|------|------|----------|
| **本仓库** | 后端 | `http://127.0.0.1:5003` |
| **his_web** | 前端 | `http://localhost:8080` |

前端 `src/request.js` 中 **`baseURL`** 已指向本服务端口，请保持两端同时运行以便联调。

## 构建可执行 Jar（可选）

```bash
./mvnw -DskipTests package
# 产物通常在 target/*.jar
java -jar target/springboot_his-0.0.1-SNAPSHOT.jar
```

（实际 jar 名以 `pom.xml` 中 `artifactId`、`version` 为准。）

## 常见问题

- **`No goals have been specified` / `BUILD FAILURE`（只输入了 `./mvnw`）**：Maven 必须带**阶段或插件目标**。启动本项目请用 **`./mvnw spring-boot:run`**；其它示例：`./mvnw -v`、`./mvnw compile`、`./mvnw package`。在 VS Code 里更推荐直接打开 **`SpringbootHisApplication.java`**，点 **`main` 上方的 Run / Debug**，不要依赖「只运行 `mvnw`」的任务。
- **`mvnw` 报找不到 java / javac**：安装 JDK 17+ 并配置 **`JAVA_HOME`** 与 **`PATH`**。  
- **无法连接数据库**：检查 MySQL 服务、库名 **`his`**、用户名密码与 `application.properties` 是否一致。  
- **端口占用**：修改 `server.port` 后，需同步修改前端 **`baseURL`**。

## 许可与课程使用

仅供课程教学与本地实验使用；生产环境需自行加固安全、密钥与数据库权限。
