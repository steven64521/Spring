# Spring 整合demo
## Spring整合JDBC
### 步骤
1. 引入Spring-context 和 Spring-jdbc 依赖
```xml
使用gradle作为项目构建工具
dependencies{
    testCompile group:'junit', name:'junit', version:'4.12'
    compile group:'org.springframework', name:'spring-context', version:'5.1.5.RELEASE'
    compile group:'org.springframework', name:'spring-jdbc', version:'5.1.5.RELEASE'
    compile group:'mysql', name:'mysql-connector-java', version:'5.1.47'
}
```
2. 通过注解的方式配置DriverManagerDataSource数据源的bean及JdbcTemplate模板bean
```java
@Configuration
@PropertySource(value = "jdbc.properties")
public class JdbcConfig{
    @Value("${jdbc.dataSource.driverClass}")
    private String driverClass;
    @Value("${jdbc.dataSource.url}")
    private String url;
    @Value("${jdbc.dataSource.user}")
    private String userName;
    @Value("${jdbc.dataSource.password}")
    private String password;
    
    @Bean
    public DrivermanagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
    /**
    * 具名参数JdbcTemplate
    * @return 
    */
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate();
        namedParameterJdbcTemplate.setDataSource(dataSource());
        return namedParameterJdbcTemplate;
    }
    
    /**
    * 针对新增操作进一步封装的Jdbc模板
    * @return 
    */
    public SimpleJdbcInsert simpleJdbcInsert(){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert();
        simpleJdbcInsert.setDataSource(dataSource());
        return simpleJdbcInsert;
    }
}
```
3. 完成数据库表与实体类的映射
```java
public class Dept implements RowMapper<Dept>{
    private Integer id;
    private String deptName;
    private Integer num;
    
    ...省略了setter和getter及构造函数
    
    @Override
    public Dept mapRow(ResultSet rs, int rowNum) throws SQLException{
        Dept dept = new Dept();
        dept.setId(rs.getInt("id"));
        dept.setDeptName(rs.getString("deptName"));
        dept.setNum(rs.getInt("num"));
        return dept;
    }
}
```
4. DAO层中注入JdbcTemplate，即可使用jdbcTemplate的方法执行数据库CRUD操作
## Spring整合Mybatis
## Spring整合MybatisPlus(MP)
