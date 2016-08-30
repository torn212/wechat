package com.torn.farmer.service;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.torn.farmer.util.Config;
import com.torn.farmer.util.HttpRequest;
import com.torn.farmer.util.JSONUtil;
import com.torn.farmer.vo.TokenVo;

@Component
public class TokenService {

	private String access_token;
	private long expirestime;

	public long getExpirestime() {
		return expirestime;
	}

	public String getAccess_token() {
		Calendar now = Calendar.getInstance();
		try {
			if (expirestime <= 0 || expirestime < now.getTimeInMillis()) {// 已过期或未获取
				String url = Config.tockenurl.replace("#{appid}", Config.appid).replace("#{secret}", Config.secret);
				String tockenjson = HttpRequest.sendInfoForGet(url);
				TokenVo jsonToBean = JSONUtil.jsonToBean(tockenjson, TokenVo.class);
				if (jsonToBean.getErrcode() == null || jsonToBean.getErrcode() <= 0) {
					access_token = jsonToBean.getAccess_token();
					now.add(Calendar.SECOND, jsonToBean.getExpires_in());
					expirestime = now.getTimeInMillis();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}

	public static void main(String[] args) {
		try {
			String url = Config.tockenurl.replace("#{appid}", Config.appid).replace("#{secret}", Config.secret);
			String tockenjson = HttpRequest.sendInfoForGet(url);
			TokenVo jsonToBean = JSONUtil.jsonToBean(tockenjson, TokenVo.class);
			System.out.println(jsonToBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
