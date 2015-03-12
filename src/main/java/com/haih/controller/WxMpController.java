package com.haih.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haih.entity.MpXmlMessage;
import com.haih.service.WxMpService;
import com.haih.util.StringUtils;

@Controller
public class WxMpController {
    /**
     * 日志信息
     */
    private static final Logger LOG = Logger.getLogger(WxMpController.class);
    
    @Autowired
    WxMpService mpService;
    
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	@ResponseBody
	public String identityVerify(HttpServletRequest request, HttpServletResponse response) {
	    LOG.info("[index]|identityVerify|begin");
	    response.setContentType("text/html;charset=utf-8");
	    response.setStatus(HttpServletResponse.SC_OK);

	    String signature = request.getParameter("signature");
	    String nonce = request.getParameter("nonce");
	    String timestamp = request.getParameter("timestamp");
	    String echostr = request.getParameter("echostr");
	    if (!mpService.checkSignature(signature,nonce,timestamp)){
	        LOG.info("[index]|index|request invailed!");
	        return "request invailed!";
	    }
	    // 说明是一个仅仅用来验证的请求，回显echostr
	    if (StringUtils.isNotBlank(echostr)){ 
	        LOG.info("[index]|index|developer verify passed!");
	        return echostr;
	    }
	    LOG.info("[index]|index|developer verify failed!");
		return "";
	}
	
	@RequestMapping(value = "/index.do", method = RequestMethod.POST)
	@ResponseBody
    public String msgHandler(HttpServletRequest request, HttpServletResponse response) {
	    LOG.info("[index]|msgHandler|begin");
	    try {
            MpXmlMessage inMessage = mpService.fromXml(request.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return "";
    }
}
