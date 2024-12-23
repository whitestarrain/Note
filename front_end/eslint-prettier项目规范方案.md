eslint 和 prettier
eslint可以使用js,yaml,json进行配置，选择顺手的就行
vscode 和 vim都有对应使用方法

# 1. 项目整合eslint

## 1.1. 配置对js代码的lint

- 安装依赖

  ```bash
  npm install eslint eslint-plugin-standard -D
  ```

- 使用.eslintrc.js文件自动配置规则。
  ```bash
  # 自动生成
  eslint --init # global eslint
  npx eslint --init # local eslint(官方推荐使用非global的eslint)

  # 会有终端交互界面，根据需要进行选择即可
  ```

- 配置文件
  ```javascript
  // .eslintrc.js

  module.exports = {
    "env": {
      "browser": true,
      "node": true,
      "es2021": true
    },
    "parserOptions": {
        "ecmaVersion": 12 // 自动生成的可能为13，eslint报错，手动调成12
    },
    "extends": [
      "standard"
    ]
  };
  ```

  - 以上是使用网上流行的lint规则包`eslint-plugin-standard`，当然也可以使用其他预设lint，只需安装对应的依赖即可
  - eslint官方也有预定义的lint规则，比如`eslint:recommended`
    - 把以上的`standard`改成`eslint:recommended`即可，这个不需要安装其他依赖。
    - 但是eslint:recommended可能会有把一些node预设变量(如`__dirname`)识别为未定义
    - **推荐使用standard**

## 1.2. 配置对vue代码的lint

> 需要使用Vue官方提供的eslint-plugin-vue包来对vue文件进行lint操作，因为eslint默认只能识别js文件

- 安装依赖
  ```bash
  npm install eslint-plugin-vue -D
  ```

- 使用.eslintrc.js文件配置规则

  ```javascript
  module.exports = {
    "parser": "vue-eslint-parser", //解析.vue文件
    "extends": [
      "plugin:vue/recommended"
    ]
  };
  ```

- 若希望对.vue文件中的`<script>`代码进行代码校验
  - 则需要在parserOptions.parser去定义相应的解析器
  - 具体可查看Vue 官方文档关于parserOptions的配置

- 若是希望使用babel去校验`<script>`内代码，则需要在以上配置基础上添加该配置：

  ```json
  {
      "parser": "vue-eslint-parser",
      "parserOptions": {
          "parser": "babel-eslint",
          "sourceType": "module",
          "allowImportExportEverywhere": false
      }
  }
  ```

- 若是使用typescript编写.vue文件内的`<script>`内容
  - 需要进行typescript整合(后面)

- vscode编辑器处理
  - 在项目根目录中创建项目级的编辑器配置文件.vscode/settings.json，然后做以下两个处理：
  - 配置eslint.validate扩展名的选项以检查.vue文件，因为扩展名仅针对*.js或*.jsx默认为文件
  - 设置"vetur.validation.template": false为避免默认的Vetur模板验证
  - 示例**.vscode / settings.json**

    ```json
    {
      "eslint.validate": [
        "javascript",
        "javascriptreact",
        "vue"
      ],
      "vetur.validation.template": false
    }

    ```
- vim编辑器处理:详见`nvim-lsp`

# 2. 项目整合prettier

> prettier的lint整合步骤大致如下，具体可以了解prettier的官方文档：

- 安装依赖

  ```bash
  npm install --save-dev eslint-plugin-prettier eslint-config-prettier
  npm install --save-dev --save-exact prettier
  ```
  - eslint-plugin-prettier用于对文件代码进行风格检查
  - eslint-config-prettier用于关闭其他插件与prettier出现冲突的lint，然后以prettier为准
  - –save-exact 意思是锁定prettier在package.json的版本，不会出现版本兼容符号^/~

- 使用.eslintrc.js文件配置规则
  ```javascript
  module.exports = {
    extends: [
        // ...其他lint
        // 为了保证格式化后代码都以prettier为准，把这两项配置放到数组最后
        "plugin:prettier/recommended",
        "prettier"
    ]
  }
  ```

  > 以上等价于：

  ```json
  {
    "extends": ["prettier"],
    "plugins": ["prettier"],
    "rules": {
      "prettier/prettier": "error",
      "arrow-body-style": "off",
      "prefer-arrow-callback": "off"
      }
  }
  ```

- 创建prettier配置文件.prettierrc

  ```json
  {
    "singleQuote": true,
    "tabWidth": 2,
    "useTabs": false,
    "semi": true,
    "trailingComma": "all",
    "printWidth": 120,
    "endOfLine":"auto"
  }
  ```
  - 虽然直接在eslint配置文件中去配置prettier的规则，但是不建议这样配置，而应该把配置写到单独一个文件中。
  - prettier的官方文档有说到：由于部分编辑器插件（如prettier-vscode）只会读取文件.prettier，而不会去读取eslint中的配置。

- prettier设置字段项如下说明

  ```json
  {
      "printWidth": 100, // 超过最大值换行
      "tabWidth": 4, // 缩进字节数
      "useTabs": false, // 缩进不使用tab，使用空格
      "semi": true, // 句尾添加分号
      "singleQuote": true, // 使用单引号代替双引号
      "proseWrap": "preserve", // 默认值。因为使用了一些折行敏感型的渲染器（如GitHub comment）而按照markdown文本样式进行折行
      "arrowParens": "avoid", // (x) => {} 箭头函数参数只有一个时是否要有小括号。avoid：省略括号
      "bracketSpacing": true, // 在对象，数组括号与文字之间加空格 "{ foo: bar }"
      "disableLanguages": ["vue"], // 不格式化vue文件，vue文件的格式化单独设置
      "endOfLine": "auto", // 结尾是 \n \r \n\r auto
      "eslintIntegration": false, // 不让prettier使用eslint的代码格式进行校验
      "htmlWhitespaceSensitivity": "ignore",
      "ignorePath": ".prettierignore", // 不使用prettier格式化的文件填写在项目的.prettierignore文件中
      "jsxBracketSameLine": false, // 在jsx中把'>' 是否单独放一行
      "jsxSingleQuote": false, // 在jsx中使用单引号代替双引号
      "parser": "babylon", // 格式化的解析器，默认是babylon
      "requireConfig": false, // Require a 'prettierconfig' to format prettier
      "stylelintIntegration": false, // 不让prettier使用stylelint的代码格式进行校验
      "trailingComma": "es5", // 在对象或数组最后一个元素后面是否加逗号（在ES5中加尾逗号）
      "tslintIntegration": false // 不让prettier使用tslint的代码格式进行校验
  }
  ```

- `.eslintrc.json`示例

  ```json
  {
      "env": {
          "browser": true,
          "commonjs": true,
          "es2021": true,
          "node": true
      },
      "extends": [
          "standard",
          "plugin:prettier/recommended",
          "prettier"
      ],
      "parserOptions": {
          "ecmaVersion": 12
      },
      "rules": {
      }
  }
  ```


# 3. 项目整合typescript

- 安装依赖

  ```bash
  npm i --save-dev eslint typescript @typescript-eslint/parser @typescript-eslint/eslint-plugin
  ```

- 使用.eslintrc.js文件配置规则（`eslint --init`中也有相关选项）
  ```javascript
  module.exports = {
    root: true,
    parser: '@typescript-eslint/parser',
    plugins: [
      '@typescript-eslint',
    ],
    extends: [
      // ...其他规则
      'plugin:@typescript-eslint/recommended',
    ]
  }
  ```

- 注意！注意！
  - 以上配置的parse字段@typescript-eslint/parser表示eslint需要使用该解释器去解析源代码，因为默认的解析器不识别ts文件的代码
  - 若项目是Vue项目，也就是eslint集成了eslint-plugin-vue去校验vue代码，由于eslint-plugin-vue插件也使用parse配置项（vue-eslint-parser）
  - 因此集成typescript就不能替换这一项，需要把这个值配置到parserOptions，即如下：

  ```javascript
  module.exports = {
    root: true,
    parser: "vue-eslint-parser",
    parserOptions: {
      parser: "@typescript-eslint/parser",
        sourceType: "module"
    }
    plugins: [
      '@typescript-eslint',
    ],
    extends: [
      // ...其他规则
      'plugin:@typescript-eslint/recommended',
    ]
  }
  ```

# 4. 文件保存时自动格式化

- vscode:
  - 实现文件保存时自动格式化内容，把代码格式化为符合prettier风格
  - 需要修改编辑器配置文件.vscode/settings.json

  ```json
  {
    "editor.formatOnSave": true,
    "editor.codeActionsOnSave": {
      "source.fixAll": true
    }
  }
  ```

- vim:使用`Prettier插件`与`autocmd + 命令行`配置

# 5. git提交时eslint

## 5.1. 说明

- 说明
  - 使用husky和lint-staged两个库进行完成git钩子commit时的校验与格式化处理
  - 只有eslint通过时，代码才允许commit，这样保证了代码的风格统一。

- 原理
  - husky可用于在执行git命令操作时自定义命令，比如在代码被commit到本地仓库前，可以让我们定义一些预检查和格式化等工作，
    > 注意：husky实际上是利用了git提供的Hooks的技术即通过修改.git/hooks文件来实现的，所以要先有.git目录，然后再安装husky使用，否则会导致husky不生效
  - lint-staged库是一个可对处于git暂存区文件（被git add的文件）进行自定义逻辑的库

## 5.2. 配置

- 安装依赖
  ```bash
  npm i -D husky@4.3.6 lint-staged
  ```

  - 这里husky的版本写死是由于，4.0版本和5.0版本的配置不一样
  - 如果直接安装会安装到5.0版本，而5.0版本配置稍微复杂点，有兴趣可以去husky官网研究一下。

- 创建.huskyrc文件

  > 以下是husky4.0版本的配置
  ```json
  {
      "hooks": {
        "pre-commit": "lint-staged"
      }
  }
  ```

  - 以上代码表示执行git commit命令时，让lint-stated库对处于git暂存区的文件进行逻辑处理

- 创建.lintstagedrc文件

  ```json
  {
    "src/**/*.*": [
      "eslint --fix",
      "git add"
    ]
  }
  ```

  - 配置文件中的每一项的键表示对暂存区的文件根据匹配规则过滤，值为对过滤出来的文件进行命令处理。
  - 以上的定义为：
    - 对处于暂存区的文件列表，执行匹配过滤操作，文件路径必须符合为在src目录下的任意文件
    - 然后对这些过滤后的文件执行数组中的命令eslint修复命令，然后再把修复好的代码git add。
  - 当不报错时才算执行git commit代码成功，否则中断提交代码的操作并输出报错

- 添加format命令（非必须）
  > 往package.json文件中添加format命令

  ```javascript
  {
    scritps: {
      "format": "eslint --fix --ext xxx src"
    }
  }
  ```

  - 注意，以上xxx位置填写所有需要lint的文件扩展名
  - 比如若需要lint并且自动修复错误的文件有ts文件，vue文件
  - 则命令脚本为`eslint --ext .ts,.vue src`

# 6. git提交规范-Angular

- [跳转](../workflow/git.md)

# 7. 参考资料

- [x] [eslint规则在项目中的整合](https://www.panyanbin.com/article/b679027e.html)
- [ ] [eslint报错：Parsing error: No Babel config file detected?](https://www.cnblogs.com/hmy-666/p/16441069.html)
  > 如果root为true，就不会进行查找。非bool值时，会遍历目录，找到babel.config.js
