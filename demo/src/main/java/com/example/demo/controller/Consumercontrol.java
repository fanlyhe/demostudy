package com.example.demo.controller;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.User;
/**
 * @Auth 
 * @Date 
 * @Desc
 */
@Component
public class Consumercontrol {
 @KafkaListener(topics = "test")
 public void listen (ConsumerRecord<?, ?> record) throws Exception {
 System.out.printf("topic = %s, offset = %s, value = %s \n", record.topic(), record.key(), record.value());
 //指定全局配置文件
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
//   User user = sqlSession.selectOne("MyMapper.selectUser", 2);
//   System.out.println(user);
   List<User> ulist = sqlSession.selectList("selectUser2",record.value());
   int i = ulist.size();
   for(int s =0;s<i;s++){
  	System.out.println("===Maven 构建的Spring Boot程序通过游览器发送并接受kafka消息并获取 再用mybatis连接库查询出用户信息===="+ulist.get(s));
  	String name = ulist.get(s).getName();
  	System.out.println("name ="+name);
  }
} finally {
   sqlSession.close();
	}
 }
}