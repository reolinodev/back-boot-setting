module.exports = {
    extends: ['airbnb-base', 'plugin:node/recommended', 'prettier'],
    parserOptions: {
        parser: 'babel-eslint',
        sourceType: 'module',
        allowImportExportEverywhere: true,
        ecmaVersion: 11,
    },
};
