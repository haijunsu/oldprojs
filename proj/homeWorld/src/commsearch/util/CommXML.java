/**
 * @(#)CommXML.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

//import java.util.*;
import org.xml.sax.*;
//DocumentBuilderFactory;DocumentBuilder
import javax.xml.parsers.*;
//java.io.File
import java.io.*;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;

/**
 * <P>公用XML相关工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommXML {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommXML.class);
	String filename = "";                                    //XML文件
	/**
	* Constructor
	*/
	public CommXML() {
		super();
	}
	public void init (){
	  try {
		// Parse in XML file, and construct a document
		   Document document = readFile(this.getFilename());
		// Get the DOMImplementation and check it supports Level 2 Core
		DOMImplementation domImpl = document.getImplementation();
		boolean levelTwoCoreSupport = domImpl.hasFeature("CORE", "2.0");
		if (levelTwoCoreSupport)
		  System.out.println("DOM implementation supports DOM Level 2.0 Core");
		else
		  System.out.println("DOM implementation doesn't support DOM Level 2.0 Core");

		// 得到XML document element 的数值
		Element documentElement = document.getDocumentElement();
		System.out.println("");
		System.out.println("Document node name: " + documentElement.getTagName());
		System.out.println("");

		// 遍历DOM TREE 并输出
		attributeInformation(documentElement);

		// 创建一个attribute并追加，使用 document element object
		//本程序主要是用来读取信息，所以下面关于追加这段代码暂时注掉
		/*System.out.println("创建一个attribute 使用 document element object");
		documentElement.setAttribute("TESTATTRIBUTE", "TESTING");

		if (documentElement.hasAttribute("TESTATTRIBUTE"))
		  System.out.println("Attribute 已经成功追加");

		Attr testAttribute = documentElement.getAttributeNode("TESTATTRIBUTE");
		System.out.println("Element: " + testAttribute.getOwnerElement() + 
						   " has attribute " + testAttribute.getName() + 
						   "          with the value " + testAttribute.getValue());
		Element testElement = document.createElement("TESTELEMENT");
		testElement.setAttribute("TESTATTRIBUTE", "In TESTELEMENT");

		documentElement.appendChild(testElement);

		Text testTextNode = document.createTextNode("Test Text");
		testElement.appendChild(testTextNode);
      
		// 得到Tag
		NodeList testTags = document.getElementsByTagName("TESTELEMENT"); 
		System.out.println("TESTELEMENT tags found: " + testTags.getLength());
		*/
		// Output all the nodes recursively, down from the document node
		//System.out.println("");
		System.out.println("递归输出:");
		nodeInformation(document);
		// Walk DOM tree, if traversals supported
		if(document.isSupported("Traversal", "2.0")) {
		  System.out.println("");
		  System.out.println("Walk tree to output LINEITEM node information:");
		  TreeWalker walker = ((DocumentTraversal)document).createTreeWalker(
						documentElement, 
						NodeFilter.SHOW_ELEMENT, 
						null,
						true);

		  walker.firstChild();

		  do {
			Node currentNode = walker.getCurrentNode();
			System.out.println("Node name: " + currentNode.getNodeName());

			if (currentNode.getNodeType() == Node.ELEMENT_NODE && 
				currentNode.hasAttributes()) {
			  // Extract attribute information
			  attributeInformation((Element)currentNode);
			}
		  } while (walker.nextSibling() != null);

		  System.out.println("");
		  System.out.println("利用iterator输出Node信息:");
		  NodeIterator iterator = ((DocumentTraversal)document).createNodeIterator(
						documentElement, 
						NodeFilter.SHOW_ALL, 
						null,
						true);

		  Node iteratedNode = iterator.nextNode();
        
		  while (iteratedNode != null) {
			System.out.println("Node name: " + iteratedNode.getNodeName());

			if (iteratedNode.getNodeType() == Node.ELEMENT_NODE && 
				iteratedNode.hasAttributes()) {
			  // Extract attribute information
			  attributeInformation((Element)iteratedNode);
			}

			iteratedNode = iterator.nextNode();
		  } 
		}
	  } catch (Exception e) {
		e.printStackTrace(System.err);
	  }
	}
	public static Document readFile(String filename) throws Exception {	
		  if(filename==null){
			  throw new Exception("no read file name!") ;
		  }
		  return readFile(new File(filename));
	  }
	public static Document readFile(File file) throws Exception {	
	Document doc=null;
		try{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			DocumentBuilder db=dbf.newDocumentBuilder();
			doc=db.parse(file);
		}catch(java.io.FileNotFoundException e){	
			throw(e);
		}catch(SAXParseException ex){
			throw(ex);
		}catch(SAXException ex){
			Exception x=ex.getException();
			throw((x==null)?ex:x);
		}
    	 return doc;
	}
	public void attributeInformation(Element element) {
	  // Iterate over the element's attributes and output them
	  NamedNodeMap attributes = element.getAttributes();
	  for (int i = 0; i < attributes.getLength(); i++) {
		Attr attribute = (Attr)attributes.item(i);
		System.out.println("Element: " + attribute.getOwnerElement() + 
						   " has attribute " + attribute.getName() + 
						   " with the value " + attribute.getValue());
	  }
	}

	public void nodeInformation(Node node) {
	  if (node == null) {
		return;
	  }

	  // 输出NodeType和value
	  int type = node.getNodeType();
	  switch (type) {
		case Node.ATTRIBUTE_NODE: {
		  System.out.println("Attribute node: " + ((Attr)node).getName() + 
							 "            has value: " + ((Attr)node).getValue());
		  break;
		}
		case Node.CDATA_SECTION_NODE: {
		  System.out.println("CDATA node: " + node.getNodeName() + 
							 " has value: " + node.getNodeValue());
		  break;
		}
		case Node.COMMENT_NODE: {
		  System.out.println("Comment node: " + node.getNodeName() + 
							 " has value: " + node.getNodeValue());
		  break;
		}
		case Node.DOCUMENT_FRAGMENT_NODE: {
		  System.out.println("Document fragment node: " + node.getNodeName());
		  outputChildNodes(node);
		  break;
		}
		case Node.DOCUMENT_NODE: {
		  System.out.println("Document node: " + node.getNodeName());
		  outputChildNodes(node);
		  break;
		}
		case Node.DOCUMENT_TYPE_NODE: {
		  System.out.println("Document type node: " + node.getNodeName());
		  break;
		}
		case Node.ELEMENT_NODE: {
		  System.out.println("");
		  System.out.println("Element node: " + ((Element)node).getTagName());
		  outputAttributes(node);
		  outputChildNodes(node);
		  break;
		}
		case Node.ENTITY_NODE: {
		  System.out.println("Entity node: " + node.getNodeName());
		  break;
		}
		case Node.ENTITY_REFERENCE_NODE: {
		  System.out.println("Entity reference node: " + node.getNodeName());
		  break;
		}
		case Node.NOTATION_NODE: {
		  System.out.println("Notation node: " + node.getNodeName());
		  break;
		}
		case Node.PROCESSING_INSTRUCTION_NODE: {
		  System.out.println("Processing instruction node: " + node.getNodeName() + 
							 " has value: " + node.getNodeValue());
		  break;
		}
		case Node.TEXT_NODE: {
		  System.out.println("Text node: " + node.getNodeName() + 
							 "          has value: " + node.getNodeValue());
		  break;
		}
	  }
	}

	public void outputChildNodes(Node node) {
	  // 使用NodeList遍历输出node的所有ChildNodes
	  NodeList children = node.getChildNodes();

	  for (int i = 0; i < children.getLength(); i++) {
		Node child = children.item(i);
		nodeInformation(child);
	  }
	}

	public void outputAttributes(Node node) {
	  // 使用NamedNodeMap遍历输出node的所有attributes
	  NamedNodeMap attributes = node.getAttributes();

	  for (int i = 0; i < attributes.getLength(); i++) {
		Attr attribute = (Attr)attributes.item(i);
		nodeInformation(attribute);
	  }
	}

	
	/**
	 * @return
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * @param string
	 */
	public void setFilename(String string) {
		this.filename = string;
	}

}