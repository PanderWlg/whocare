package com.womow.toc.whocare.helper;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.womow.toc.whocare.log.LogManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;


/**
 * XML 相关帮助方法
 *
 * @author CXY 2015-07-30
 */
public class Helper4Xml {

    /**
     * 解析sqlxml文件 返回name属性符合参数值的sql语句
     *
     * @param xmlFilePath xml文件路径
     * @param name        &lt;sql&gt;元素的name属性
     * @return sql语句
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static String getSqlbyXML(String xmlFilePath, String name) throws DocumentException {
        Document doc = parseXml(xmlFilePath);
        Element root = doc.getRootElement();
        List<Element> sqls = root.elements("sql");
        Iterator<Element> iter = sqls.iterator();
        while (iter.hasNext()) {
            Element e = (Element) iter.next();
            if (name.equals(e.attributeValue("name"))) {
                return e.getText();
            }
        }
        return null;
    }

    /**
     * 根据label 获取相关的内容集合
     *
     * @param xmlFilePath 文件路径
     * @param lable       要获取的xml标签值（多个）
     * @return
     * @author CXY 2015-07-30
     */
    public static Map<String, String> getXmlContent(String xmlFilePath, String[] lable) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlFilePath));
            Element rootElement = document.getRootElement();

            for (String one : lable) {
                result.put(one, rootElement.element(one).getText());
            }
        } catch (Exception e) {
            LogManager.logDebug("Helper4Xml", String.format("xml文件信息{%s.%s}获取失败", xmlFilePath, lable));
        }
        return result;
    }

    /**
     * 根据label 获取相关的内容集合
     *
     * @param xmlFilePath 文件路径
     * @param lable       要获取的xml标签值（单个）
     * @return
     * @author CXY 2015-07-30
     */
    public static String getXmlContent(String xmlFilePath, String lable) {
        String result = "";
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlFilePath));
            Element rootElement = document.getRootElement();

            result = rootElement.element(lable).getStringValue();
        } catch (Exception e) {
            System.out.println("xml文件信息获取失败");
        }
        return result;
    }

    /**
     * 从classpath 里获得xml内容（为了解决从jar中去文件问题）
     *
     * @param res
     * @param lable
     * @return
     */
    public static String getXmlContent(Resource res, String lable) {
        String result = "";
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(res.getInputStream());
            Element rootElement = document.getRootElement();
            result = rootElement.element(lable).getStringValue();
        } catch (Exception e) {
            LogManager.logDebug("Helper4Xml", String.format("xml文件信息{%s.%s}获取失败", res.getFilename(), lable));
        }
        return result;
    }

    /**
     * 解析指定路径下的xml文档,返回document对象模型
     *
     * @param xmlFilePath xml文件路径
     * @return Document
     * @throws DocumentException
     */
    private static Document parseXml(String xmlFilePath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xmlFilePath);
        return doc;
    }
}
