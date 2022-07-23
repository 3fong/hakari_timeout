## Hikari 超时问题示例

HikariCp是一个效率非常高的连接池.这里简单展示了它的连接池配置,用来测试 jdbcTemplate.queryForStream 流查询方法

### 使用方式

1. 替换数据源
2. 替换Controller中查询语句
3. 启动服务
4. 访问 http://localhost:8081/get


### HikariCP连接超时问题:

```
2022-07-23 11:10:48.805  INFO 17080 --- [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-07-23 11:10:48.806  INFO 17080 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-07-23 11:10:48.807  INFO 17080 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2022-07-23 11:10:48.865  INFO 17080 --- [nio-8081-exec-1] com.zaxxer.hikari.HikariDataSource       : selfHikariCP - Starting...
2022-07-23 11:10:49.214  INFO 17080 --- [nio-8081-exec-1] com.zaxxer.hikari.HikariDataSource       : selfHikariCP - Start completed.
--------------------------------------------- Controller.MyBean(app_name=xxl-job-executor-sample)
2022-07-23 11:10:54.536 ERROR 17080 --- [nio-8081-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLTransientConnectionException: selfHikariCP - Connection is not available, request timed out after 1015ms.] with root cause

java.sql.SQLTransientConnectionException: selfHikariCP - Connection is not available, request timed out after 1015ms.
	at com.zaxxer.hikari.pool.HikariPool.createTimeoutException(HikariPool.java:695) ~[HikariCP-3.4.5.jar:na]
	at com.zaxxer.hikari.pool.HikariPool.getConnection(HikariPool.java:197) ~[HikariCP-3.4.5.jar:na]
	at com.zaxxer.hikari.pool.HikariPool.getConnection(HikariPool.java:162) ~[HikariCP-3.4.5.jar:na]
	at com.zaxxer.hikari.HikariDataSource.getConnection(HikariDataSource.java:128) ~[HikariCP-3.4.5.jar:na]
	at org.springframework.jdbc.datasource.DataSourceUtils.fetchConnection(DataSourceUtils.java:159) ~[spring-jdbc-5.3.20.jar:5.3.20]
	at org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection(DataSourceUtils.java:117) ~[spring-jdbc-5.3.20.jar:5.3.20]
	at org.springframework.jdbc.datasource.DataSourceUtils.getConnection(DataSourceUtils.java:80) ~[spring-jdbc-5.3.20.jar:5.3.20]
	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:376) ~[spring-jdbc-5.3.20.jar:5.3.20]
	at org.springframework.jdbc.core.JdbcTemplate.queryForStream(JdbcTemplate.java:497) ~[spring-jdbc-5.3.20.jar:5.3.20]
	at org.example.Controller.get(Controller.java:21) ~[classes/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_291]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_291]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_291]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_291]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197) ~[spring-web-5.3.9.jar:5.3.9]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141) ~[spring-web-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1064) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:655) ~[tomcat-embed-core-9.0.50.jar:4.0.FR]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883) ~[spring-webmvc-5.3.9.jar:5.3.9]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:764) ~[tomcat-embed-core-9.0.50.jar:4.0.FR]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:228) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:163) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:190) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:163) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.3.9.jar:5.3.9]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.3.9.jar:5.3.9]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:190) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:163) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-5.3.9.jar:5.3.9]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.3.9.jar:5.3.9]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:190) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:163) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-5.3.9.jar:5.3.9]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.3.9.jar:5.3.9]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:190) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:163) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202) ~[tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:382) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:893) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1723) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_291]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_291]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-9.0.50.jar:9.0.50]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_291]
```

现象描述:

> 当请求次数超过连接池配置的最大连接数时就开始报请求获取超时异常.根据异常显示就是连接池里的连接有问题

代码调用情况:

实际代码调用也很简单,根据spring封装的JdbcTemplate来查询数据库,获取信息后数据.不同的是这里使用了流查询方法 queryForStream().

```
        Stream<MyBean> objectStream = jdbcTemplate.queryForStream("SELECT * FROM xxl_job_group", new BeanPropertyRowMapper<>(MyBean.class));
        Optional<MyBean> first = objectStream.findFirst();
        System.out.println("--------------------------------------------- "+first.orElse(null));

```

queryForStream: 实现方式

它只是封装了一个内部类,重写了数据库查询执行方式,每次调用都将响应返回结果流.
```
    public <T> Stream<T> queryForStream(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        class StreamStatementCallback implements StatementCallback<Stream<T>>, SqlProvider {
            StreamStatementCallback() {
            }

            public Stream<T> doInStatement(Statement stmt) throws SQLException {
                ResultSet rs = stmt.executeQuery(sql);
                Connection con = stmt.getConnection();
                return (Stream)(new JdbcTemplate.ResultSetSpliterator(rs, rowMapper)).stream().onClose(() -> {
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(stmt);
                    DataSourceUtils.releaseConnection(con, JdbcTemplate.this.getDataSource());
                });
            }

            public String getSql() {
                return sql;
            }
        }

        return (Stream)result(this.execute(new StreamStatementCallback(), false));
    }
```

ResultSetSpliterator: 定义流处理规则.它是queryForStream流处理方法的核心.它会获取ResultSet,每一次流获取都会触发ResultSet.next(),
这样每次处理一条记录,实现大数据量操作中内存的尽量少占用.避免如一些图片处理等list操作,一次性将结果集全部堆到内存中,引起内存溢出
```
    private static class ResultSetSpliterator<T> implements Spliterator<T> {
        private final ResultSet rs;
        private final RowMapper<T> rowMapper;
        private int rowNum = 0;

        public ResultSetSpliterator(ResultSet rs, RowMapper<T> rowMapper) {
            this.rs = rs;
            this.rowMapper = rowMapper;
        }

        public boolean tryAdvance(Consumer<? super T> action) {
            try {
                if (this.rs.next()) {
                    action.accept(this.rowMapper.mapRow(this.rs, this.rowNum++));
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException var3) {
                throw new InvalidResultSetAccessException(var3);
            }
        }

       ...
    }
```

该方法涉及两个连接释放的操作,用于归还在用的连接.
1 stream().onClose(..) 重写了流的关闭方法
2 execute(new StreamStatementCallback(), false)

execute方法中如果第二个参数为true,则会在方法结束前触发连接释放,这里明显没有使用该方式释放连接.    
原因也很简单,因为ResultSetSpliterator内部类需要获取ResultSet数据,如果连接释放就无法ResultSet中的数据

这里有一个匿名方法定义: 它是整个流处理连接关闭的关键
```
	onClose(() -> {
		JdbcUtils.closeResultSet(rs);
		JdbcUtils.closeStatement(stmt);
		DataSourceUtils.releaseConnection(con, JdbcTemplate.this.getDataSource());
	})
```

onClose()是BaseStream的实现方法,该方法用于关闭ResultSet,Statement和释放DataSource.而此方法需要显示调用,由于流处理的特殊性,
jdbcTemplate中没有实现自动触发该方法的调用.

```
// 流对象的基本接口,用于定义元素序列化和并行聚合操作的顺序
java.util.stream.BaseStream {

     /**
        接收一个额外的关闭处理器(close handler)并返回相同的流对象(equivalent stream).所有的关闭处理器会在close()方法被调用时触发,执行顺序取决于添加顺序.
        前面的关闭处理器异常不影响其他关闭处理执行.如果有关闭处理器抛出异常,会被延迟到close()调用,多个异常会被合并抛出.
     */
    S onClose(Runnable closeHandler);

     // 关闭流.触发当前流处理(stream pipeline)上的所有关闭处理器(close handlers)
    @Override
    void close();
}
```

故:

jdbcTemplate.queryForStream 方法调用触发的异常是因为连接池中的连接一直被占用,无法释放,造成连接枯竭而发生连接获取时的超时异常.
解决方法也很简单:

只需要在返回的流结果上调用close方法即可:

```
       Stream<MyBean> objectStream = jdbcTemplate.queryForStream("SELECT * FROM BD_PSNBASDOC", new BeanPropertyRowMapper<>(MyBean.class));
        objectStream.forEach(data -> {
            System.out.println(data.getPhoto());
        });
        objectStream.close();//点睛之笔
```

### 代码示例

[hikari_timeout](https://github.com/3fong/hikari_timeout.git)








































