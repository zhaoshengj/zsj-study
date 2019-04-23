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

        /**
         * PageHelper 方法使用了静态的 ThreadLocal 参数，分页参数和线程是绑定的。
         * 只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的。
         *      因为 PageHelper 在 finally 代码段中自动清除了 ThreadLocal 存储的对象。
         *
         *  如果代码在进入 Executor 前发生异常，就会导致线程不可用，
         *  这属于人为的 Bug（例如接口方法和 XML 中的不匹配，导致找不到 MappedStatement 时），
         *  这种情况由于线程不可用，也不会导致 ThreadLocal 参数被错误的使用。
         *
         * 但是如果你写出下面这样的代码，就是不安全的用法：
         * PageHelper.startPage(1, 10);
         * List<Country> list;
         * if(param1 != null){
         *     list = countryMapper.selectIf(param1);
         * } else {
         *     list = new ArrayList<Country>();
         * }
         * 这种情况下由于 param1 存在 null 的情况，就会导致 PageHelper 生产了一个分页参数，但是没有被消费，
         * 这个参数就会一直保留在这个线程上。当这个线程再次被使用时，就可能导致不该分页的方法去消费这个分页参数，这就产生了莫名其妙的分页。
         *
         * 上面这个代码，应该写成下面这个样子：
         *
         * List<Country> list;
         * if(param1 != null){
         *     PageHelper.startPage(1, 10);
         *     list = countryMapper.selectIf(param1);
         *
         *
         * } else {
         *     list = new ArrayList<Country>();
         * }
         * 这种写法就能保证安全。
         *
         */
        PageHelper.startPage(1,10);
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
