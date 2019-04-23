package com.zsj.nacos.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsj.nacos.mapper.UserDao;
import com.zsj.nacos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionTemplate template;

    /**
     * 根据名字查找用户
     */
    public User selectUserByName(String name) {
        return userDao.findUserByName(name);
    }

    /**
     * 查找所有用户
     */
    public List<User> selectAllUser() {
        PageHelper.startPage(1,1);
        List<User> allUser = userDao.findAllUser();
        PageInfo<User> users = new PageInfo<>(allUser);

        System.out.println(allUser.toArray().toString());

        System.out.println(users.toString());
        return allUser;
    }

    /**
     * 插入两个用户
     */
    public void insertService() {
        template.execute(new TransactionCallback<Object>(){
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                userDao.insertUser(new User(5,"SnailClimb", 22, new BigDecimal(3000)));
                userDao.insertUser(new User(6,"Daisy", 19, new BigDecimal(3000)));
                return null;
            }
        });

    }

    /**
     * 根据id 删除用户
     */

    public void deleteService(int id) {
        userDao.deleteUser(id);
    }

    /**
     * 模拟事务。由于加上了 @Transactional注解，如果转账中途出了意外 SnailClimb 和 Daisy 的钱都不会改变。
     */
    //@Transactional
    public void changemoney() {
        template.execute((status) -> {
            userDao.updateUser(new User(5,"SnailClimb", 22, new BigDecimal(2000)));
            // 模拟转账过程中可能遇到的意外状况
            int temp = 1 / 0;
            userDao.updateUser(new User(6,"Daisy", 19, new BigDecimal(4000)));
            return null;
        });

    }
}
