package com.torn.farmer.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.torn.farmer.service.TicketService;
import com.torn.farmer.util.Config;

@Controller
@RequestMapping("wechat")
public class WeChatAction {

	@Resource
	private TicketService ticketService;

	@ResponseBody
	@RequestMapping("wxConfig")
	public List<Object> wxConfig(String url) {
		List<Object> result = new ArrayList<>();
		String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		String signature = ticketService.getSignature(nonceStr, timeInMillis, url);

		result.add(Config.appid);
		result.add(timeInMillis);
		result.add(nonceStr);
		result.add(signature);
		return result;
	}
}
