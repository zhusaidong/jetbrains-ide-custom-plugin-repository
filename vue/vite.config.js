import { fileURLToPath, URL } from 'node:url'

import { defineConfig,loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  // 根据当前工作目录中的 `mode` 加载 .env 文件
  // 设置第三个参数为 '' 来加载所有环境变量，而不管是否有 `VITE_` 前缀。
  const env = loadEnv(mode, process.cwd())
  console.log("mode=",mode)
  console.log("env=",env)
  return {
    base: '/admin',
    // vite 配置
    define: {
      __APP_ENV__: JSON.stringify(env.APP_ENV),
    },
    plugins: [
      vue(),
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    // 打包配置 npm run build
    build:{
      outDir:"../src/main/resources/admin",
      emptyOutDir: true
    },
    //开发配置  npm run dev
    server: {
      port: 8001,//端口号
      strictPort: true,//是否是严格的端口号，如果true，端口号被占用的情况下，vite会退出
      host: 'localhost',
      cors: true,//为开发服务器配置 CORS , 默认启用并允许任何源
      open: true,//是否自动打开浏览器
      // 反向代理 跨域配置
      proxy: {
          '/api': {
              target: env.VITE_APP_BASEAPI,
              changeOrigin: true,
              rewrite: (path) => path.replace(/^\/api/, '')
          }
      }
    },
    //预览设置  npm run build　打包之后，会生成dist文件 然后运行npm run preview；vite会创建一个服务器来运行打包之后的文件
    preview:{
        port: 4000,//端口号
        host: 'localhost',
        open: true,//是否自动打开浏览器
      },
  }
})
