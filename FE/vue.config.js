const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
   devServer: {
    proxy: {
      '^/api': {
        target: 'https://localhost:9002',
        changeOrigin: true,
        secure:false,
        pathRewrite: {'^/api': '/'}
        },
    }
  },
  configureWebpack:{
    optimization: {
      splitChunks: {
        minSize: 10000,
        maxSize: 250000,
      }
    }
  }
})
