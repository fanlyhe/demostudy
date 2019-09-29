package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
/**
 * @Auth 
 * @Date 
 * @Desc
 */
@RestController
@RequestMapping("/kafka")
public class ProducerController {
 protected final Logger logger = LoggerFactory.getLogger(this.getClass());
 	@Autowired
 	private KafkaTemplate<String, String> kafkaTemplate;
  	@RequestMapping(value = "/send", method = RequestMethod.GET)
 public String sendKafka(@RequestParam("message") String message) {
 try {
	logger.info("kafka的消息={}", message);
	kafkaTemplate.send("test", "key", message);
	logger.info("发送kafka成功.");
	return "successs";
  } catch (Exception e) {
	  logger.error("发送kafka失败.", e);
	  return "failure";
	  }
 	}
 }
