# 框架说明

- Flask是Python的轻量级Web框架，核心只依赖Werkzeug(WSGI工具)和Jinja2(模板引擎)
- 设计理念：微框架(micro framework)，核心简洁，通过扩展增加功能
- 特点：灵活、易于上手、文档完善、社区活跃
- 最小应用示例：

  ```python
  from flask import Flask
  app = Flask(__name__)

  @app.route('/')
  def index():
      return 'Hello Flask'

  if __name__ == '__main__':
      app.run(debug=True)
  ```

- 与Django的区别：Flask自由度高但需自行选择组件，Django全栈开箱即用
- 适合小型项目、API服务、微服务架构

# 服务器配置

- app.run()参数：host(监听地址), port(端口), debug(调试模式)
- 调试模式(debug=True)：代码修改自动重载，错误页面显示详细traceback
- 配置管理：通过app.config对象管理，支持多种加载方式

  ```python
  app.config['SECRET_KEY'] = 'hard-to-guess'
  app.config.from_object('config.ProductionConfig')
  app.config.from_pyfile('config.cfg')
  app.config.from_envvar('APP_SETTINGS')
  ```

- 生产部署不使用app.run()，而是通过Gunicorn/uWSGI启动
- 工厂模式(application factory)：create_app()函数创建app实例，便于测试和多实例

# url 与 view

## 基本 view

- 路由通过@app.route()装饰器绑定URL到视图函数
- 视图函数处理请求并返回响应(字符串、Response对象、元组)
- URL变量规则：`<converter:variable_name>`，支持string/int/float/path/uuid

  ```python
  @app.route('/user/<int:user_id>', methods=['GET', 'POST'])
  def user_profile(user_id):
      if request.method == 'POST':
          return f'update user {user_id}'
      return f'show user {user_id}'
  ```

- url_for()根据函数名反向生成URL：`url_for('user_profile', user_id=1)`
- methods参数指定允许的HTTP方法，默认只允许GET
- 返回值：字符串(自动包装为Response)、(body, status_code, headers)元组、jsonify()

## 可拔插类视图

- 类视图(MethodView)将不同HTTP方法映射到类的同名方法
- 适合RESTful风格的接口，代码更有组织性
- 支持继承和mixin，便于代码复用

  ```python
  from flask.views import MethodView

  class UserAPI(MethodView):
      def get(self, user_id):
          if user_id is None:
              return jsonify(get_all_users())
          return jsonify(get_user(user_id))

      def post(self):
          return jsonify(create_user(request.json))

      def delete(self, user_id):
          delete_user(user_id)
          return '', 204

  app.add_url_rule('/users/', view_func=UserAPI.as_view('users'),
                   defaults={'user_id': None}, methods=['GET', 'POST'])
  app.add_url_rule('/users/<int:user_id>',
                   view_func=UserAPI.as_view('user'), methods=['GET', 'DELETE'])
  ```

- decorators类属性可为类视图添加装饰器(如login_required)

## 蓝图

- 蓝图(Blueprint)用于将应用分割为多个模块，实现大型项目的结构化组织
- 蓝图本身不是应用，需要注册到app上才能生效
- 每个蓝图可以有自己的模板目录、静态文件目录、错误处理

  ```python
  # user/views.py
  from flask import Blueprint
  user_bp = Blueprint('user', __name__, url_prefix='/user')

  @user_bp.route('/profile')
  def profile():
      return 'user profile'

  # app.py
  from user.views import user_bp
  app.register_blueprint(user_bp)
  ```

- url_prefix统一为蓝图下所有路由添加前缀
- 蓝图内使用url_for时需加蓝图名：`url_for('user.profile')`
- 蓝图支持before_request/after_request等钩子，仅作用于本蓝图

## 嵌套蓝图

- Flask 2.0+支持蓝图嵌套，将子蓝图注册到父蓝图上
- 适合更细粒度的模块划分和URL层级组织

  ```python
  parent_bp = Blueprint('parent', __name__, url_prefix='/api')
  child_bp = Blueprint('child', __name__, url_prefix='/v1')

  @child_bp.route('/items')
  def items():
      return 'items list'

  parent_bp.register_blueprint(child_bp)
  app.register_blueprint(parent_bp)
  # 最终URL: /api/v1/items
  ```

- 嵌套蓝图的url_prefix会自动拼接
- 子蓝图的before_request等钩子独立于父蓝图

# Cookie 和 Session

- Cookie：存储在客户端浏览器的小数据片段，每次请求自动携带
- Session：基于Cookie实现的服务端状态存储，Flask默认将session数据签名后存入Cookie

  ```python
  from flask import session, make_response

  @app.route('/login', methods=['POST'])
  def login():
      session['username'] = request.form['username']
      return 'logged in'

  @app.route('/set_cookie')
  def set_cookie():
      resp = make_response('cookie set')
      resp.set_cookie('key', 'value', max_age=3600)
      return resp
  ```

- Flask session需要设置SECRET_KEY用于签名(防篡改，但客户端可解码内容)
- request.cookies获取请求中的cookie
- 服务端session存储方案：Flask-Session扩展支持Redis/Memcached/文件系统
- session的生命周期：默认为浏览器关闭时过期，可设置PERMANENT_SESSION_LIFETIME

# LocalProxy

- Flask中的request、session、g、current_app都是LocalProxy对象
- LocalProxy在每次属性访问时动态查找实际对象，而非固定绑定
- 原因：多线程/协程环境下，每个请求需要访问各自独立的上下文数据

  ```python
  from werkzeug.local import LocalProxy
  from flask.globals import _request_ctx_stack

  # Flask内部实现示意
  request = LocalProxy(lambda: _request_ctx_stack.top.request)
  ```

- 好处：可以像全局变量一样使用request等对象，但实际是线程安全的
- 底层原理：通过__getattr__等魔法方法将所有操作转发给被代理的真实对象
- 可以用_get_current_object()获取被代理的实际对象

# 上下文机制

## request

- 请求上下文(Request Context)在每个请求进入时自动创建并压入栈
- request对象封装了HTTP请求的所有信息
- 常用属性：request.method, request.args(GET参数), request.form(POST表单)
- request.json获取JSON请求体，request.files获取上传文件
- request.headers获取请求头，request.cookies获取Cookie
- 请求上下文包含request和session两个对象
- 请求结束后上下文自动出栈销毁

## response

- 视图函数的返回值被Flask自动转为Response对象
- 手动构造响应：make_response()创建Response对象后设置属性

  ```python
  from flask import make_response, jsonify
  resp = make_response(render_template('index.html'))
  resp.status_code = 200
  resp.headers['X-Custom'] = 'value'
  return resp
  # 或直接返回元组: return 'body', 200, {'X-Custom': 'value'}
  ```

- jsonify()返回JSON响应并自动设置Content-Type为application/json
- redirect()返回重定向响应，abort()抛出HTTP错误

## app

- 应用上下文(Application Context)代表当前运行的Flask应用
- current_app指向当前处理请求的app实例，在工厂模式中尤为重要
- 应用上下文在请求上下文创建时自动推入，请求结束时弹出
- 手动推入应用上下文(用于脚本/测试)：

  ```python
  with app.app_context():
      db.create_all()
      print(current_app.config['DEBUG'])
  ```

- 一个进程中可以存在多个app实例，current_app确保指向正确的那个

## g

- g是请求级别的全局临时存储对象，生命周期与请求相同
- 用于在同一请求的多个函数间传递数据(如before_request中查询的用户信息)

  ```python
  from flask import g

  @app.before_request
  def load_user():
      g.user = get_user_from_token(request.headers.get('Token'))

  @app.route('/profile')
  def profile():
      return f'hello {g.user.name}'
  ```

- 每个请求的g对象独立，请求结束后销毁
- 与session的区别：g仅存活于单次请求，session跨请求持久化

# 信号机制(blinker)

- Flask的信号机制基于blinker库，实现发布-订阅模式的解耦通信
- 信号不会修改数据，只用于通知(如日志记录、监控)
- Flask内置信号：request_started, request_finished, before_render_template等

  ```python
  from flask import request_finished

  def log_response(sender, response, **kwargs):
      print(f'{response.status_code} response sent')

  request_finished.connect(log_response, app)
  ```

- 自定义信号：

  ```python
  from blinker import Namespace
  my_signals = Namespace()
  order_created = my_signals.signal('order-created')
  order_created.send(app, order=order_data)
  ```

- 信号与装饰器钩子(before_request等)的区别：信号不能修改请求/响应流程

# Flask 插件

- Flask扩展遵循统一命名规范Flask-XXX，通过init_app()模式集成
- 常见扩展：Flask-SQLAlchemy(ORM), Flask-Migrate(数据库迁移), Flask-Login(认证)
- 扩展初始化模式：

  ```python
  from flask_sqlalchemy import SQLAlchemy
  db = SQLAlchemy()

  def create_app():
      app = Flask(__name__)
      db.init_app(app)  # 延迟初始化，支持工厂模式
      return app
  ```

- 选择扩展时关注：维护活跃度、文档质量、与Flask版本兼容性

# Flask-sqlAlchemy

- [sqlAlchemy](./sqlAlchemy.md)

## Flask-wtf

- Flask-WTF是WTForms的Flask集成，提供表单处理和CSRF防护
- 功能：表单验证、CSRF令牌自动生成、文件上传处理
- 表单类定义字段和验证规则，视图中validate_on_submit()处理提交

  ```python
  from flask_wtf import FlaskForm
  from wtforms import StringField, PasswordField
  from wtforms.validators import DataRequired, Length

  class LoginForm(FlaskForm):
      username = StringField('Username', validators=[DataRequired()])
      password = PasswordField('Password', validators=[Length(min=6)])

  @app.route('/login', methods=['GET', 'POST'])
  def login():
      form = LoginForm()
      if form.validate_on_submit():
          user = User.query.filter_by(name=form.username.data).first()
          return redirect(url_for('index'))
      return render_template('login.html', form=form)
  ```

- 模板中使用{{ form.hidden_tag() }}渲染CSRF令牌
- 自定义验证器：定义validate_fieldname(self, field)方法

## Flask-Restful

- Flask-RESTful简化RESTful API的开发，提供资源(Resource)抽象
- Resource类将HTTP方法映射到同名方法，配合reqparse处理参数验证

  ```python
  from flask_restful import Api, Resource, reqparse

  api = Api(app)

  class UserResource(Resource):
      def get(self, user_id):
          return {'user': user_id}

      def put(self, user_id):
          parser = reqparse.RequestParser()
          parser.add_argument('name', required=True)
          args = parser.parse_args()
          return {'updated': args['name']}

  api.add_resource(UserResource, '/users/<int:user_id>')
  ```

- 支持marshal_with装饰器格式化输出字段
- 自动处理内容协商，返回JSON格式响应

## Flask-appbuiler

- Flask-AppBuilder基于Flask快速构建管理后台和CRUD应用
- 内置用户认证、角色权限管理、自动生成管理界面
- 通过ModelView快速为数据模型创建增删改查界面

  ```python
  from flask_appbuilder import AppBuilder, ModelView
  from flask_appbuilder.models.sqla.interface import SQLAInterface

  class UserModelView(ModelView):
      datamodel = SQLAInterface(User)
      list_columns = ['name', 'email', 'created_at']

  appbuilder = AppBuilder(app, db.session)
  appbuilder.add_view(UserModelView, 'Users', category='Admin')
  ```

- 支持多种认证方式：DB、LDAP、OAuth
- Apache Superset就是基于Flask-AppBuilder构建的

## Flask-Session

- Flask-Session将session数据存储到服务端(Redis/Memcached/文件系统/SQLAlchemy)
- 解决Flask默认session存储在Cookie中的容量和安全性限制

  ```python
  from flask_session import Session

  app.config['SESSION_TYPE'] = 'redis'
  app.config['SESSION_REDIS'] = redis.from_url('redis://localhost:6379')
  app.config['SESSION_PERMANENT'] = False
  Session(app)
  ```

- Cookie中只存储session_id，实际数据存储在服务端
- 支持设置session过期时间、key前缀等配置
- 适合需要存储大量session数据或对安全性要求高的场景

## Flask-Login

[文档](http://www.pythondoc.com/flask-login)

- Flask-Login管理用户登录状态，处理登录/登出/记住我等功能
- 用户模型需实现UserMixin提供的接口(is_authenticated, get_id等)

  ```python
  from flask_login import LoginManager, login_user, login_required

  login_manager = LoginManager(app)
  login_manager.login_view = 'login'

  @login_manager.user_loader
  def load_user(user_id):
      return User.query.get(int(user_id))

  @app.route('/dashboard')
  @login_required
  def dashboard():
      return f'welcome {current_user.name}'
  ```

- @login_required装饰器保护需要登录的视图
- current_user代理当前登录用户，未登录时为AnonymousUserMixin
- remember=True实现"记住我"功能，通过持久Cookie保持登录

## Flask-Cache

- Flask-Caching(Flask-Cache的后继)为Flask应用添加缓存支持
- 支持多种后端：SimpleCache(内存)、Redis、Memcached、文件系统
- 通过装饰器缓存视图函数或任意函数的返回值

  ```python
  from flask_caching import Cache

  cache = Cache(app, config={'CACHE_TYPE': 'redis'})

  @app.route('/data')
  @cache.cached(timeout=300)  # 缓存5分钟
  def get_data():
      return expensive_query()

  @cache.memoize(timeout=60)
  def get_user(user_id):  # 按参数缓存
      return User.query.get(user_id)

  cache.delete_memoized(get_user, user_id)  # 手动清除
  ```

- cache.clear()清除所有缓存
- @cache.cached缓存整个视图，@cache.memoize按参数缓存函数

# python web运行原理

## WSGI

- WSGI(Web Server Gateway Interface, PEP 3333)定义了Web服务器与Python应用的标准接口
- 三个角色：Web服务器(Nginx/Apache)、WSGI服务器(Gunicorn/uWSGI)、WSGI应用(Flask/Django)
- WSGI应用是一个可调用对象：接收environ(请求信息)和start_response(响应回调)

  ```python
  # Flask应用本质是一个WSGI应用
  # app.__call__(environ, start_response) 被WSGI服务器调用
  # Flask内部根据environ路由到对应的视图函数处理请求
  ```

- 请求处理流程：Nginx接收HTTP请求 -> 转发给Gunicorn -> 调用Flask app -> 返回响应
- 中间件(Middleware)：包装WSGI应用，可添加日志、认证、压缩等横切关注点
- ASGI(Asynchronous Server Gateway Interface)是WSGI的异步版本，支持WebSocket等长连接

# 参考资料

- [ ] [WSGI 到底是什么？](https://zhuanlan.zhihu.com/p/95942024)
- [ ] [中文文档](https://dormousehole.readthedocs.io/en/latest/index.html)
- [ ] [Flask基础教程](https://www.zlkt.net/book/detail/10/304)
- flask中的session使用，实现以及SECRET_KEY。自定义Session存储以及flask-session扩展
  - [ ] [Flask中的session详细用法教程](https://blog.51cto.com/douya/2151255)
  - [ ] [flask基础之session原理详解(十)](https://www.cnblogs.com/cwp-bg/p/10084523.html)
- [解析Flask运行原理](https://bbs.huaweicloud.com/blogs/232880)
- [Flask 路由分发及转换器](https://juejin.cn/post/6979145558543826980)
- [flask是如何处理多个访问请求的?](https://segmentfault.com/q/1010000004532745)
