package com.haih.util.xml;

import com.thoughtworks.xstream.converters.basic.StringConverter;

public class XStreamCDataConvert extends StringConverter {

    @Override
    public String toString(Object obj) {
      return "<![CDATA[" + super.toString(obj) + "]]>";
    }

  }

