// vue.config.js
module.exports = {
    devServer: {
        port: 8080,
        host: '0.0.0.0',
        open: false,
        disableHostCheck: true,
        proxy: {
            '/patient': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/doctororder': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/outvisit': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/medicalrecord': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/labcheck': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/overview': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/user': { target: 'http://127.0.0.1:5003', changeOrigin: true },
            '/outbillpay': { target: 'http://127.0.0.1:5003', changeOrigin: true }
        }
    }
}