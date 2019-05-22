# Spring 整合demo
## Spring整合JDBC
### 步骤
1. 引入Spring-context 和 Spring-jdbc 依赖
2. 通过注解的方式配置DriverManagerDataSource数据源的bean及JdbcTemplate模板bean
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
