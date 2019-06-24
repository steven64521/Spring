package com.jiexun.dao;

import com.jiexun.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * 定义UserRepository接口，有五种形式, 第一和第二种是两种不同表现形式的同一种方式，都需要自定义接口方法，后三种继承父接口的方法
 * <p>
*      @RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
 *     public interface UserRepository{
 *          //自定义接口方法
 *     }
 * </p>
 * <p>
 *     public interface UserRepository extends Repository<User, Integer>{
 *         //自定义接口方法
 *     }
 * </p>
 * <p>
 *     public interface UserRepository extends JpaRepository<User, Integer>{
 *
 *     }
 * </p>
 * <p>
 *     public interface UserRepository extends CRUDRepository<User, Integer>{
 *
 *     }
 * </p>
 * <p>
 *     public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
 *
 *     }
 * </p>
 *
 * SpringDataJPA创建查询语句的方式：
 * 1、通过解析方法名创建查询：在进行方法名解析时，会先把方法名多余的前缀去掉，比如：find、findBy、read、readBy、get、getBy，然后对剩下部分进行解析，如果方法的最后的一个参数是Sort或者Pageable类型，一会提取相关的信息，以便进行排序或分页查询
 * 在创建查询时，通过在方法名中使用属性名称表达，比如findByUserAddressZip()，框架在解析该方法时，首先剔除findBy，然后对剩下的属性进行解析(以AccountInfo为例):
 *  a、先判断userAddressZip是否为AccountInfo的一个属性，如果是，则表示根据该属性进行查询，如果不是，进行第二步
 *  b、从右往左截取第一个大写字母开头的字符串（次处为Zip）,然后检查剩下的字符串是否为AccountInfo的一个属性，如果是，则表示根据此属性进行查询，如果没有，重复该步骤，继续从右往左截取，最后假设user为AccountInfo的一个属性
 *  c、接着处理剩下部分（AddressZip）,先判断user所对应的类型是否有addressZip属性，如果有，则表示该方法最终是根据AccountInfo.user.addressZip的取值进行查询，否则继续按照步骤b的规则从右往左截取，最终表示根据AccountInfo.user.address.zip的值进行查询
 *  可能会存在一种特殊情况，比如AccountInfo包含一个user属性，也有一个addressZip属性，此时会存在混淆。可通过在属性之前加上"_"以显示表达意图，比如fndByUser_AddressZip()或者findByUserAddress_Zip()
 *  在查询时，通常需要根据多个属性进行查询，且查询的条件也各式各样，springDataJPA提供了一些表达条件查询的关键字：
 *  <p>
 *      And---等价与SQL中的and关键字，比如：findByUserNameAndPassword(String userName, String password);
 *      Or----等价于SQL中的or关键字，比如：findByUserNameOrAddress(String userName, String address);
 *      Between---等价于SQL中的between关键字，比如：findBySalaryBetween(int max, int min);
 *      LessThan----等价于SQL中的"<", 比如：findBySalaryLessThan(int max);
 *      GreaterThan---等价于SQL中的">"，比如：findBySalaryGreaterThan(int min);
 *      IsNull---等价于SQL中的"is null",比如：findByUserNameIsNull();
 *      IsNotNull---等价于SQL中的"is not null"，比如：findByUserNameIsNotNull();
 *      NotNull---与IsNotNull等价
 *      Like---等价于SQL中的"like",findByUserNameLike(String userName);
 *      NotLike---等价于SQL中的"not like"，findByUserNameNotLike(String userName);
 *      OrderBy---等价于SQL中的"order by"，findByUserNameOrderBySalaryAsc(String userName);
 *      Not---等价于SQL中的"!="，比如：findByUserNameNot(String userName);
 *      In---等价于SQL中的"in",比如：findByUserNameIn(Collection<String> list)，参数可以是Collection类型，也可以是数组或者不定长参数
 *      NotIn---等价于SQL中的"not in",比如：findByUserNameNotIn(Collection<String> list),参数可以是Collection类型，也可以是数组或者不定长参数;
 *  </p>
 * 2、使用@Query创建查询
 * @Query 注解的使用非常简单，只需在申明的方法上标注该注解，同时提供一个JP QL查询语句，
 * <p>
 *     public interface UserDao extends Repository<AccountInfo, Long> {
 *
 *      @Query("select a from AccountInfo a where a.accountId = ?1")
 *      public AccountInfo findByAccountId(Long accountId);
 *
 *      @Query("select a from AccountInfo a where a.balance > ?1")
 *      public Page<AccountInfo> findByBalanceGreaterThan(Integer balance,Pageable pageable);
 * }
 * 很多开发者在创建 JP QL 时喜欢使用命名参数来代替位置编号，@Query 也对此提供了支持。JP QL 语句中通过": 变量"的格式来指定参数，同时在方法的参数前面使用 @Param 将方法参数与 JP QL 中的命名参数对应，示例如下：
 *      public interface UserDao extends Repository<AccountInfo, Long> {
 *
 *      public AccountInfo save(AccountInfo accountInfo);
 *
 *      @Query("from AccountInfo a where a.accountId = :id")
 *      public AccountInfo findByAccountId(@Param("id")Long accountId);
 *
 *      @Query("from AccountInfo a where a.balance > :balance")
 *       public Page<AccountInfo> findByBalanceGreaterThan(@Param("balance")Integer balance, Pageable pageable);
 * }
 *
 * 此外，开发者也可以通过使用 @Query 来执行一个更新操作，为此，我们需要在使用 @Query 的同时，用 @Modifying 来将该操作标识为修改查询，这样框架最终会生成一个更新的操作，而非查询。如下所示：
 *      @Modifying
 *      @Query("update AccountInfo a set a.salary = ?1 where a.salary < ?2")
 *      public int increaseSalary(int after, int before);
 * </p>
 * 3、通过调用 JPA 命名查询语句创建查询：
 *  命名查询是 JPA 提供的一种将查询语句从方法体中独立出来，以供多个方法共用的功能。Spring Data JPA 对命名查询也提供了很好的支持。用户只需要按照 JPA 规范在 orm.xml 文件或者在代码中使用 @NamedQuery（或 @NamedNativeQuery）定义好查询语句，唯一要做的就是为该语句命名时，需要满足”DomainClass.methodName()”的命名规则
 *
 * 创建查询的顺序：
 * Spring Data JPA在为接口创建代理对象时，如果发现同时存在多种上述情况可用，它该优先采用那种策略呢？为此：<jpa:repositories>提供了query-lookup-strategy属性，用以指定查询的顺序。它有如下三个取值：
 * a、create--- 通过解析方法名字来创建查询。即使有符合的命名查询，或者方法通过@Query指定的查询语句，都将被忽略
 * b、create-if-not-found---- 如果方法通过@Query指定了查询语句，则使用该语句实现查询，如果没有，则查找是否定义了符合条件的命名查询，如果找到，则使用该命名查询，如果两个都没找到，则通过解析方法名来创建查询，这是query-lookup-strategy属性的默认值
 * c、use-declared-query--- 如果方法通过@Query指定了查询语句，则使用该语句实现查询，如果没有，则查找是否定义了符合条件的命名查询，如果找到，则使用该命名查询，如果两个都没找到，则抛出异常
 */
public interface UserRepository extends Repository<User, Integer> {
    //查询所有
    List<User> findAll();

    //根据id查询
    User findUserByUid(Integer uid);

    //新增
    void save(User user);}
