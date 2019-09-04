const path = require('path');

module.exports = {
    entry: './resources/assets/js/index.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'resources/dist')
    }
};