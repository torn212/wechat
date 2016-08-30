package com.torn.farmer.service;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.torn.farmer.util.Config;
import com.torn.farmer.util.HttpRequest;
import com.torn.farmer.util.JSONUtil;
import com.torn.farmer.util.MessageDigestUtil;
import com.torn.farmer.vo.TokenVo;

@Component
public class TicketService {

	private String ticket;
	private long expirestime;

	@Resource
	private TokenService tokenService;

	public String getSignature(String nonceStr, Long timestamp, String url) {
		LinkedHashMap<String, Object> config = new LinkedHashMap<String, Object>();
		config.put("jsapi_ticket", getTicket());
		config.put("noncestr", nonceStr);
		config.put("timestamp", timestamp);
		config.put("url", url);
		StringBuffer sb = new StringBuffer();

		Set<String> keySet = config.keySet();
		for (String key : keySet) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append("&");
			}
			sb.append(key);
			sb.append("=");
			sb.append(config.get(key));
		}
		String sha1 = MessageDigestUtil.SHA1(sb.toString());
		return sha1;
	}

	public String getTicket() {
		Calendar now = Calendar.getInstance();
		try {
			if (expirestime <= 0 || expirestime < now.getTimeInMillis()) {// 已过期或未获取
				String url = Config.jsapi_ticket_url.replace("#{access_token}", tokenService.getAccess_token());
				String tockenjson = HttpRequest.sendInfoForGet(url);
				TokenVo jsonToBean = JSONUtil.jsonToBean(tockenjson, TokenVo.class);
				if (jsonToBean.getErrcode() == null || jsonToBean.getErrcode() <= 0) {
					ticket = jsonToBean.getTicket();
					now.add(Calendar.SECOND, jsonToBean.getExpires_in());
					expirestime = now.getTimeInMillis();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

}
