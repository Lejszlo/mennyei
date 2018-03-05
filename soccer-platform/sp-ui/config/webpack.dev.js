var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var packageJSON = require('../package.json');
var path = require('path');
var commonConfig = require('./webpack.common.js');


const PATHS = {
    build: path.join(__dirname, '..','target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version),
    htmlPath: path.join('webjars', packageJSON.name, packageJSON.version),
    resource: path.join(__dirname, '..','target', 'classes', 'META-INF', 'resources')
};


module.exports = webpackMerge(commonConfig, {

    devtool: 'source-map',

    output: {
        path: PATHS.build,
        filename: '[name].js',
        chunkFilename: '[id].chunk.js'
    },

    plugins: [
        new ExtractTextPlugin('[name].css')
    ],

    devServer: {
        historyApiFallback: true,
        hot: true,
        port: 8090,
        inline: true,
        proxy: {
            "/api/competition": "http://localhost:8081",
            "/api/matches": "http://localhost:8083"
        }
    }
});
