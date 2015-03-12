package com.haih.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import com.haih.entity.MpXmlMessage;


public interface WxMpService {
    
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); 
    
    /**
     * @descrption 验证推送过来的消息的正确性 
     * @author haihu
     * @create 2015年3月12日上午11:46:16
     * @version 1.0
     * @param signature
     * @param nonce
     * @param timestamp
     * @return
     */
    public boolean checkSignature(String signature,String nonce,String timestamp);
 
    public MpXmlMessage fromXml(InputStream input);
}
