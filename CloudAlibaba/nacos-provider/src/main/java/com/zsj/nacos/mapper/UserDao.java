package com.zsj.nacos.mapper;

import com.zsj.nacos.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
//@Mapper
@Repository
public interface UserDao {
    /**
     * 查询所有用户信息
     */
    //@Select("SELECT * FROM user")
    List<User> findAllUser();

    /**
     * 插入用户信息
     */
    //@Insert("INSERT INTO user(name, age,money) VALUES(#{name}, #{age}, #{money})")
   // void insertUser(@Param("name") String name, @Param("age") Integer age, @Param("money") BigDecimal money);
    void insertUser(User user);

    /**
     * 根据 id 更新用户信息
     */
    //@Update("UPDATE  user SET name = #{name},age = #{age},money= #{money} WHERE id = #{id}")
    //void updateUser(@Param("name") String name, @Param("age") Integer age, @Param("money") BigDecimal money,
    //                @Param("id") int id);
    void updateUser(User user);

    /**
     * 根据 id 删除用户信息
     */
    //@Delete("DELETE from user WHERE id = #{id}")
    void deleteUser(@Param("id") int id);

    /**
     * 根据名字进行查找
     * @param name
     * @return
     */
    //@Select("SELECT * FROM user where name = #{name}")
    User findUserByName(String name);
}
