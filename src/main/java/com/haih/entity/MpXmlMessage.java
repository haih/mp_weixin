package com.haih.entity;

import java.io.Serializable;

import com.haih.util.xml.XStreamCDataConvert;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;


@XStreamAlias("xml")
public class MpXmlMessage implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    ///////////////////////
    // 以下都是微信推送过来的消息的xml的element所对应的属性
    ///////////////////////
    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XStreamCDataConvert.class)
    private String toUserName;
}
