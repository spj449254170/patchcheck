module.exports = {
    devServer:{
        host:'localhost',
        port : 9090,
        proxy:{
            '/patchcheck':{
                target:'http://localhost:9000',
                ws:false,
                changeOrigin: false
            }
        }
    }
}
