import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { viteMockServe } from 'vite-plugin-mock'
import path from 'path';

import config from "./config";

// mac: image to icon
// sips -z 64 64 pic.png --out favicon.ico

const env = (() => {
  const idx = process.argv.findIndex(arg => arg == '-m1' || arg == '--mode')
  const mode = (idx > 0 && process.argv.length > idx + 1 && process.argv[idx + 1]) || 'development'
  // @ts-ignore
  return config[mode];
})()

export default defineConfig({
  base: env.cdn || "",
  plugins: [
    react(),
    env.mocked && viteMockServe()
  ],
  server: {
    host: '0.0.0.0',
    port: env.port || 3000,
    proxy: {
      '/api': {
        target: env.apiBaseUrl,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },
  resolve: {
    alias: [
      {
        find: '@',
        replacement: path.resolve(__dirname, 'src')
      }
    ]
  },
  define: { 'process.env': env }
})
