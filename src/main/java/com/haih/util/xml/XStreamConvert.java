package com.haih.util.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.haih.entity.MpXmlMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class XStreamConvert {
    
    private static final Map<Class, XStream> Class_2_XStream_Map = createMapInstance(); 
    /**
     * xml -> pojo
     *
     * @param clazz
     * @param xml
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Class<T> clazz, String xml) {
      T object = (T) Class_2_XStream_Map.get(clazz).fromXML(xml);
      return object;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(Class<T> clazz, InputStream is) {
      T object = (T) Class_2_XStream_Map.get(clazz).fromXML(is);
      return object;
    }
    
    private static Map<Class, XStream> createMapInstance() {
        Map<Class, XStream> map = new HashMap<Class, XStream>();
        map.put(MpXmlMessage.class, config_WxMpXmlMessage());
        return map;
    }
    
    private static XStream config_WxMpXmlMessage() {
        XStream xstream = new XStream();
        xstream.processAnnotations(MpXmlMessage.class);
        xstream.aliasField("MsgID", MpXmlMessage.class, "msgId");
        return xstream;
    }
    
    
    
    public static void main(String[] args) {
        PhoneNumber phone = new PhoneNumber(111,"123456");
        Person person = new Person("hai","hu", phone);
        XStream xstream = new XStream();
        xstream.processAnnotations(Person.class);
        xstream.processAnnotations(PhoneNumber.class);
        xstream.alias("person", Person.class);
        xstream.alias("phonenumber", PhoneNumber.class);
        System.out.println(xstream.toXML(person));
    }
}

@XStreamAlias("test")
class Person{
    @XStreamAlias("FirstName")
    @XStreamConverter(value = XStreamCDataConvert.class)
    private String firstname;
    private String lastname;
    private PhoneNumber phone;
    public Person(String firstname,String lastname,PhoneNumber phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }
}

class PhoneNumber {
    @XStreamAlias("Code")
    private int code;
    
    @XStreamAlias("Number")
    @XStreamConverter(value = XStreamCDataConvert.class)
    private String number;
    public PhoneNumber(int code,String number) {
        this.code = code;
        this.number = number;
    }
  }