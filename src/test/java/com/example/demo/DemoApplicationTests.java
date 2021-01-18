package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper usermapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","cheche");
    }

    @Test
    public void testMybatisPlus(){
//        List<User> users = usermapper.selectList(null);
//        for (User user:users) {
//            System.out.println(user);
//        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","cheyinbo").eq("password","123");
        User user = usermapper.selectOne(queryWrapper);
        System.out.println(user);
//
//        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
//        userUpdateWrapper.set("name","cheyinbo");
//        userUpdateWrapper.eq("id",user.getId());
//        usermapper.update(user,userUpdateWrapper);

//        Page<User> page = new Page<>(1,1);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//
//        IPage<User> userIPage = usermapper.selectPage(page,queryWrapper);
//        System.out.println(userIPage);

    }

    @Test
    public void testM() throws FileNotFoundException {
//        System.out.println(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));
    }

    public int findRepeatNumber(int[] nums) {
        int[] arr = new int[nums.length];
        for(int i = 0;i < arr.length;i++){
            arr[i] = -1;
        }
        int result = -1;
        for(int i = 0;i<nums.length;i++){
            if(arr[nums[i]] == -1){
                arr[nums[i]] = nums[i];
            }else{
                result = nums[i];
                break;
            }
        }
        return result;
    }


}
