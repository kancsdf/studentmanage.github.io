const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})
module.exports = {
    devServer: {
        //自动打开浏览器
        open: true,
        port: 9000
    }
}
module.exports = {
    lintOnSave: false //关闭eslint
}