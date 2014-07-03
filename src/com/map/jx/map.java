package com.map.jx;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/*import org.codehaus.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;*/
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.SAXException;

/*import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;*/

public class map {

	public static void main(String[] args) throws JSONException, TransformerException, ParserConfigurationException, SAXException, IOException {
		 int PRETTY_PRINT_INDENT_FACTOR =4;
		 String jsonPrettyPrintString =null;
		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
         org.w3c.dom.Document doc = docBuilder.parse (new File("/playground/empolyee.xml")); //fetch the xml file and parse
       /*  System.out.println("doc: "+doc);*/
         if(doc!=null){
		 doc.getDocumentElement().normalize();
         DOMSource domSource = new DOMSource(doc);
         StringWriter writer = new StringWriter();
         StreamResult result = new StreamResult(writer);
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         transformer.transform(domSource, result);
         
         if(writer.toString()!=null){
         JSONObject xmlJSONObj = XML.toJSONObject(writer.toString());
         jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
         System.out.println("converting to json from xml file: "+jsonPrettyPrintString);
         }
         else{
        	 System.out.println("no value to convert json");
         }
         
         JSONObject json = new JSONObject(jsonPrettyPrintString);
         String xml = XML.toString(json);
         System.out.println("convertet to xml: "+xml);
         
         try {
             JSONObject xmlJSONObj = XML.toJSONObject(xml);
             String js = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
             System.out.println("convertet to json: "+js);
         } catch (JSONException je) {
             System.out.println(je.toString());
         }
 }

}}
