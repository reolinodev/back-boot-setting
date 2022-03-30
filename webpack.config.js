const path = require('path');
const webpack = require('webpack');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

const childProcess = require('child_process');

const removeNewLine = (buffer) => {
    return buffer.toString().replace('\n', '');
};

const env = process.env.NODE_ENV;

module.exports = {
    mode: 'development',

    entry: {
        'js/config': path.join(
            __dirname + '/src/main/resources/static/js/config.js'
        ),
        'js/index': [
            path.join(
                __dirname + '/src/main/resources/static/js/index/index.js'
            ),
        ],
        'js/module': [
            path.join(
                __dirname + '/src/main/resources/static/js/module/moment.js'
            ),
            path.join(
                __dirname + '/src/main/resources/static/js/module/alert.js'
            ),
        ],
    },
    output: {
        path: path.resolve(__dirname + '/src/main/resources/static/dist'),
        filename: '[name].js',
        clean: true,
    },
    module: {
        rules: [
            {
                test: /\.s?css$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'postcss-loader',
                    'sass-loader',
                ],
            },
            {
                test: /\.js$/,
                use: ['babel-loader'],
            },
            {
                test: /\.(gif|png|jpe?g|svg)$/i,
                use: [
                    {
                        loader: 'image-webpack-loader',
                        options: {
                            mozjpeg: {
                                progressive: true,
                                quality: 65,
                            },
                            optipng: {
                                enabled: false,
                            },
                            pngquant: {
                                quality: [0.65, 0.9],
                                speed: 4,
                            },
                            gifsicle: {
                                interlaced: false,
                            },
                            webp: {
                                quality: 75,
                            },
                        },
                    },
                ],
            },
        ],
    },
    plugins: [
        new CleanWebpackPlugin({
            cleanAfterEveryBuildPatterns: ['dist'],
        }),
        new webpack.BannerPlugin({
            banner: `
                Build Date :: ${new Date().toLocaleString()}
                Commit Version :: ${removeNewLine(
                    childProcess.execSync('git rev-parse --short HEAD')
                )}
                Auth.name :: ${removeNewLine(
                    childProcess.execSync('git config user.name')
                )}
                Auth.email :: ${removeNewLine(
                    childProcess.execSync('git config user.email')
                )}
            `,
        }),
        new webpack.DefinePlugin({
            VERSION: JSON.stringify('v.1.0.0'),
            MAX_COUNT: JSON.stringify(999),
            'api.domain': JSON.stringify('http://127.0.0.1'),
        }),
        new webpack.ProvidePlugin({
            $: 'jquery',
            moment: 'moment',
            _: 'lodash',
            Swal: 'sweetalert2',
        }),
    ],
};
