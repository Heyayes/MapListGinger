package com.shak.heyyes.maplistginger;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by stshakun on 13.09.16.
 */
public class MindBuilder {
    static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    static BuilderMap mb;
//    public static void main(String[] args) {
//        DocumentBuilder db = null;
//        try {
//            db = dbf.newDocumentBuilder();
//            Document doc = db.newDocument();
//            mb = new BuilderMap(doc);
//
//            Element obj = mb.insertNew("map");
//            Element one = mb.insertNew("node1", obj);
//
//            mb.addAttribute(one,"captain", "onew");
//            Element two = mb.insertNode("node2", one);
//            mb.insertNew("three", two);
//
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//
//            DOMSource source = new DOMSource(doc);
//            FileWriter target = new FileWriter("file.xml");
//            StreamResult result = new StreamResult(target);
//
//            Transformer t = tf.newTransformer();
//            t.setOutputProperty(OutputKeys.INDENT, "yes");
//            t.setOutputProperty(OutputKeys.METHOD, "xml");
//            t.transform(source, result);
//
//
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
//    }
}

