package com.haih.service;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.haih.entity.MpXmlMessage;
import com.haih.util.crypto.SHA1;
import com.haih.util.xml.XStreamConvert;

@Service
public class WxMpServiceImp implements WxMpService {
    public boolean checkSignature(String signature,String nonce,String timestamp) {
        try{
            return SHA1.gen(nonce, timestamp).equals("signature");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    public MpXmlMessage fromXml(InputStream input){
        MpXmlMessage message = XStreamConvert.fromXml(MpXmlMessage.class, input);
        return message;
    }
}
