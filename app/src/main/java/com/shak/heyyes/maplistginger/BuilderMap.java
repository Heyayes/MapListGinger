package com.shak.heyyes.maplistginger;

import android.content.Context;
import android.content.res.AssetManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by stshakun on 13.09.16.
 */
public class BuilderMap{
    public Document doc  = null;
    static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public BuilderMap(Document doc){
        this.doc = doc;
    }
    public BuilderMap(File file, Context context){
        this.doc = parseFile(file, context);

    }

    public void addNewMap(String name){
        try {

            //Создали новый файл
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            TransformerFactory tf = TransformerFactory.newInstance();

            DOMSource source = new DOMSource(doc);
            FileWriter target = new FileWriter(name + ".xml");
            StreamResult result = new StreamResult(target);

            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.transform(source, result);
            ///создали и записали
            target.close();

            //записываем в файл карт запись о созданной карте

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element insertNode(String newNodeStr, Element before, Element parent){
        Element newNode = doc.createElement(newNodeStr);
        parent.insertBefore(newNode,before);
        return newNode;
    }

    private Element insertNew(String nameOfNode, Element parent){
        Element newNode = doc.createElement(nameOfNode);
        parent.appendChild(newNode);
        return newNode;
    }

    private Element insertNew(String nameOfNode){
        Element newNode = doc.createElement(nameOfNode);
        doc.appendChild(newNode);
        return newNode;
    }

    private void addAttribute(Element node, String attr, String value){
        node.setAttribute(attr, value);
    }

    public Document parseFile(File file, Context context){
        Document result = null;
        AssetManager as = context.getAssets();

        try {
                InputStream is = context.openFileInput(file.getName());
                System.out.println(is.toString());
                DocumentBuilder db = dbf.newDocumentBuilder();
                result = db.parse(is);
                result.normalize();
                is.close();

                //System.out.println(result.getFirstChild().toString());

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String toString(){

        return "hello!";
    }

}