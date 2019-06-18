package com.inspur.controller;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/kafkaproducer")
public class KafkaProducerController {
	
	private static final Logger LOG = Logger . getLogger (KafkaProducerController.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping(value="/sendMessage.action")
	public void sendMessage(HttpServletRequest request, HttpServletResponse response, String params){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("sendMessage", "消息发送失败");
		String jsonResult = JSONObject.fromObject(jsonMap).toString();
		PrintWriter out = null;
		try {
			if(StringUtils.isNotBlank(params)){

				for(int i = 0; i < 1000; i++){
					kafkaTemplate.sendDefault("hello-world-" + i);
				}
				jsonMap.put("sendMessage", "消息发送成功");
			}
			jsonResult = JSONObject.fromObject(jsonMap).toString();
			out = response.getWriter();
			out.write(jsonResult);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonMap.put("sendMessage", "消息发送异常");
				jsonResult = JSONObject.fromObject(jsonMap).toString();
				if(out == null){
					out = response.getWriter();
				}
				out.write(jsonResult);
				out.flush();
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
