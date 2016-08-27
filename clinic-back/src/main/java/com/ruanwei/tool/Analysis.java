package com.ruanwei.tool;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import org.xml.sax.InputSource;

import com.ijianbao.framework.bean.Code;


public class Analysis {
	 public static Code xmlElements(String xmlDoc, Code code) {
		 System.out.println(xmlDoc);
	        //创建一个新的字符串
	        StringReader read = new StringReader(xmlDoc);
	        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
	        InputSource source = new InputSource(read);
	        //创建一个新的SAXBuilder
	        SAXBuilder sb = new SAXBuilder();
//	        Code code = new Code();
	        try {
	            //通过输入源构造一个Document
	            Document doc = sb.build(source);
	            //取的根元素
	            Element root = doc.getRootElement();
	            //得到根元素所有子元素的集合
	            List jiedian = root.getChildren();

	            Element status = (Element) jiedian.get(0);
	            Element message = (Element) jiedian.get(1);
	            code.setStatus(status.getText().equals("Success") ? 1 : 2);
	            code.setMessage(message.getText());
	        } catch (JDOMException e) {
	            // TODO 自动生成 catch 块
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO 自动生成 catch 块
	            e.printStackTrace();
	        }
	        return code;
	    }
}
