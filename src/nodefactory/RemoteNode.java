package nodefactory;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public interface RemoteNode {
	public static final String RF = "RF";
	public static final String FSO = "FSO";
	public static final String UDP = "UDP";
	public static final String TCP = "TCP";
	void getServer();
	void getClient();
	default NodeList getNode() {
		File inputFile = new File("config\\nodes.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Document doc = null;
		try {
			doc = dBuilder.parse(inputFile);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Node");
        return nList;
    }
	void setnode(String nodeType);
}
