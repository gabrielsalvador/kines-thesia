const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: './src/index.ts', // Update this to the path of your main TS file
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js',
  },
  resolve: {
    // Add `.ts` and `.tsx` as a resolvable extension.
    extensions: ['.ts', '.tsx', '.js']
  },
  module: {
    rules: [
      // all files with a `.ts` or `.tsx` extension will be handled by `ts-loader`
      { test: /\.tsx?$/, loader: 'ts-loader',  exclude: /node_modules/ },
      { test: /\.css$/, use: ['style-loader', 'css-loader'] }

    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html', // Path to your HTML file
    }),
  ],
  devServer: {
    static: {
      directory: path.join(__dirname, 'dist'),
    },
    hot: true,
    port: 3000,
  },
};