package com.ijianbao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ijianbao.course.pojo.SNSUserInfo;
import com.ijianbao.course.pojo.WeixinOauth2Token;
import com.ijianbao.util.AdvancedUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/oAuth")
public class OAuthController {

	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String code = request.getParameter("code");

		if (!"authdeny".equals(code)) {
			
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx67d6d6da171c551a", "b05ff38fee4aa66ac36e0bf278ceff83", code);
			String accessToken = weixinOauth2Token.getAccessToken();
			String openId = weixinOauth2Token.getOpenId();
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		request.getRequestDispatcher("weixin.jsp").forward(request, response);
	}
}
