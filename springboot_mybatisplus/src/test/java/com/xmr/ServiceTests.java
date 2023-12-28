package com.xmr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmr.entity.User;
import com.xmr.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SpringBootTest
class ServiceTests {

    @Test
    void contextLoads() {
    }




    @Autowired
    private IUserService userService;


    @Test
    public void testSave(){
        User user=new User();
        user.setAge(22);
        user.setEmail("bded@126.com");
        user.setName("john");
        userService.save(user);
    }
    @Test
    public void testsaveBatch(){
        List<User> list=new ArrayList<>();
        User user=new User();
        user.setAge(22);
        user.setEmail("bded@126.com");
        user.setName("john");
        User user1=new User();
        user1.setAge(32);
        user1.setEmail("bded@163.com");
        user1.setName("jam");
        list.add(user);
        list.add(user1);
        userService.saveBatch(list);
    }

    @Test
    public void testSaveOrUpdate(){
        User user=new User();
        user.setAge(24);
        user.setId(Long.valueOf(6));
        user.setEmail("test5@baomidou.com");
        user.setName("Billie");
        userService.saveOrUpdate(user);
    }

    @Test
    public void testSaveOrUpdateWithWrapper(){
        User user=new User();
        user.setAge(24);
        user.setId(Long.valueOf(5));
        user.setEmail("test51@baomidou.com");
        user.setName("Billie1");
        userService.saveOrUpdate(user,new UpdateWrapper<User>().eq("age",24));
        //lambda表达式的方式
//        userService.saveOrUpdate(user,new UpdateWrapper<User>().lambda().eq(User::getAge,24));
        //更新的时候使用QueryWrapper虽然不报错，但是没有更新成功
//        userService.saveOrUpdate(user,new QueryWrapper<User>().eq("age",24));
    }

    @Test
    public void testRemove(){
        userService.remove(new QueryWrapper<User>().eq("id",Long.valueOf(6)));
    }
    @Test
    public void testRemoveById(){
        userService.removeById(5);
    }
    @Test
    public void testRemoveMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("age",24);
        map.put("id",6);
        userService.removeByMap(map);
    }
    @Test
    public void testGetById(){
        User user=userService.getById(5);
        System.out.println(user);

    }
    @Test
    public void testGetOne(){
        User user=userService.getOne(new QueryWrapper<User>().eq("id",5));
        System.out.println(user);
    }
    @Test
    public void testGetMap(){
        Map<String,Object> user=userService.getMap(new QueryWrapper<User>().eq("id",5));
        System.out.println(user);

    }
    @Test
    public void testObj(){
        User user= userService.getObj(new QueryWrapper<User>().eq("id", 5), new Function<Object, User>() {
             //下面的参数o对应QueryWrapper的val
            @Override
            public User apply(Object o) {
                //进行一系列转换操作 ，最终返回我们所需要的类型V
                User user=userService.getById(5);
                user.setAge(222);
                return user;
            }
        });
        System.out.println(user.toString());
    }
    @Test
    public void testList(){
        List<User> userList=userService.list();
        System.out.println(userList);

    }
    @Test
    public void testListObjs(){
        List<User> userList=userService.listObjs(new QueryWrapper<User>().ge("id", 3), new Function<Object, User>() {
            @Override
            public User apply(Object o) {
                User user = userService.getById((Serializable) o);
                user.setAge(222);
                return user;
            }
        });
        System.out.println(userList);

    }
//-------------------------Page---------------------
    @Test
    public void testPage(){
        IPage<User> page=new Page<>(1,5);
        IPage<User> result=userService.page(page);
        System.out.println(result.getSize());

    }
    @Test
    public void testPageMaps(){
        IPage page=new Page<>(1,5);
        IPage<Map<String, Object>> result=userService.pageMaps(page);
        System.out.println(result.getSize());

    }
    @Test
    public void testCount(){
        long result=userService.count();
        System.out.println(result);

    }
    @Test
    public void testChainQuery(){
        long result=userService.query().count();
        System.out.println(result);

    }
    @Test
    public void testChainUpdate(){
//        userService.update().eq("id",5).set("name","xmr");
        userService.update().eq("id",5).remove();

    }

}
