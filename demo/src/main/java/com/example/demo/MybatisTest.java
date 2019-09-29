package com.example.demo;
import com.example.demo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
   public static void main(String[] args) throws Exception {
      // 指定全局配置文件
      String resource = "mybatis-config.xml";
      // 读取配置文件
      InputStream inputStream = Resources.getResourceAsStream(resource);
      // 构建sqlSessionFactory
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      // 获取sqlSession
      SqlSession sqlSession = sqlSessionFactory.openSession();
      try {
         // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
         // 第二个参数：指定传入sql的参数：这里是用户id
//         User user = sqlSession.selectOne("MyMapper.selectUser", 2);
//         System.out.println(user);
         List<User> ulist = sqlSession.selectList("selectUser2");
         System.out.println("========s========"+ulist);
         int i = ulist.size();
         for(int s =0;s<i;s++){
        	System.out.println("=========list===="+ulist.get(s));
        	String name = ulist.get(s).getName();
        	System.out.println("name ="+name);
        }
      } finally {
         sqlSession.close();
      }
   }
}