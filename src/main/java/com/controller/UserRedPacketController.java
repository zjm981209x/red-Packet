package com.controller;

import com.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserRedPacketController {
	
	@Autowired
	private UserRedPacketService userRedPacketService = null;
	
	@RequestMapping("/grap")
	@ResponseBody
	public Map<String,Object> grap(Long redPacketId,Long userId){
		int result = userRedPacketService.grapRedPacket(redPacketId, userId);
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag = result > 0;
		map.put("success", flag);
		map.put("message", flag? "抢红包成功":"抢红包失败");
		return map;
	}
}
