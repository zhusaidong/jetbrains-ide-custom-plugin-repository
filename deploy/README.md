# 部署文档

## 目录结构

```
/
    lib
        ${project.build.finalName}.jar
        ...(各种依赖)
    bin
        stop.sh
        start.sh
    config
        application.properties
    README.md
```

`lib`：核心jar包

`bin`：运行脚本目录，包含启动脚本，停止脚本

`config`：配置目录

## 部署方法

解压压缩包：`unzip ${project.build.finalName}.zip`

修改配置文件：`vim config/application.properties`

运行启动脚本：`bash bin/start.sh`
