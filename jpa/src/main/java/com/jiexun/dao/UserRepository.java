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
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer> {
    //查询所有
    List<User> findAll();
}
