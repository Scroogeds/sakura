package com.org.scrooged.user.config.mybatis;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * <p>Title: MybatisInterceptor</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-09-10   luyangqian  Created
 * </pre>
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class }) })
@Component
public class MybatisInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            //获取xml中的一个select/update/insert/delete节点，主要描述的是一条SQL语句
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = null;
            //获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
            if(invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
                logger.info("parameter = " + parameter);
            }
            //获取到节点的id，即sql语句的id
            String sqlId = mappedStatement.getId();
            logger.info("sqlId = " + sqlId);
            //BoundSql就是封装myBatis最终产生的sql类
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            //获取节点的配置
            Configuration configuration = mappedStatement.getConfiguration();
            //获取最终的sql语句
            String sql = getSql(configuration, boundSql, sqlId);
            logger.info("sql = " + sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //执行完上面的任务后，不改变原有的sql执行过程
        return invocation.proceed();
    }

    /**
     *
     * Describe: 获取sql语句，使得结果返回完整xml路径下的sql语句节点id + sql语句
     *
     * @param configuration
     * @param boundSql
     * @param sqlId
     * @return: {@link String}
     * @exception
     * @auther: luyangqian
     * @date: 2019-09-10 16:31
     */
    private String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        String sql = showSql(configuration, boundSql);
        StringBuilder builder = new StringBuilder(100);
        builder.append(sqlId);
        builder.append(":");
        builder.append(sql);
        return builder.toString();
    }

    /**
     *
     * Describe: 如果参数是String，则添加单引号；如果是日期，则转换为时间格式器并加单引号，
     *           对参数是null和不是null的情况作了处理
     * @param obj
     * @return: {@link String}
     * @exception 
     * @auther: luyangqian
     * @date: 2019-09-10 16:39
     */
    private String getParameterValue(Object obj){
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat
                    .getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    /**
     *
     * Describe: 进行？的替换
     *
     * @param configuration
     * @param boundSql
     * @return: {@link String}
     * @exception
     * @auther: luyangqian
     * @date: 2019-09-10 16:38
     */
    private String showSql(Configuration configuration, BoundSql boundSql) {
        //获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings;
        parameterMappings = boundSql
                .getParameterMappings();
        //sql语句中多个空格都用一个空格代替
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
            //获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换<br>
            //如果根据parameterObject.getClass(）可以找到对应的类型，则替换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

            } else {
                //MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,
                //主要支持对JavaBean、Collection、Map三种类型对象的操作
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        //该分支是动态sql
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    }else {
                        //打印出缺失，提醒该参数缺失并防止错位
                        sql=sql.replaceFirst("\\?","缺失");
                    }
                }
            }
        }
        sqlParser(sql);
        return sql;
    }

    private void sqlParser(String sql) {
        //String sql = "select user from emp_table";
        String dbType = JdbcConstants.MYSQL;

        //格式化输出
        String result = SQLUtils.format(sql, dbType);
        logger.info("result = " + result);
        System.out.println(result); // 缺省大写格式
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

        //解析出的独立语句的个数
        //System.out.println("size is:" + stmtList.size());
        logger.info("size is:" + stmtList.size());
        for (int i = 0; i < stmtList.size(); i++) {

            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);

            //获取表名称
            //System.out.println("Tables : " + visitor.getCurrentTable());
            //获取操作方法名称,依赖于表名称
            logger.info("Manipulation : " + visitor.getTables());
            //System.out.println("Manipulation : " + visitor.getTables());
            //获取字段名称
            logger.info("fields : " + visitor.getColumns());
            //System.out.println("fields : " + visitor.getColumns());
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
