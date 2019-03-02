import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeServer implements NodeImplementor{
	
	private NodeList nodeServer;
	private String m_ipAddress;
	private String m_username;
	private String m_password;
	
	@Override
	public void setnode() {
		// TODO Auto-generated method stub
		System.out.println("server connect");
		nodeServer = getNode();
		for (int temp = 0; temp < nodeServer.getLength(); temp++) {
            Node nNode = nodeServer.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               m_ipAddress = eElement.getElementsByTagName("IpAddress").item(0).getTextContent();
               m_username = eElement.getElementsByTagName("Username").item(0).getTextContent();
               m_password = eElement.getElementsByTagName("Password").item(0).getTextContent();
               System.out.println("SERVER Name : " + eElement.getAttribute("name") +" Id : " + eElement.getAttribute("id"));
               System.out.println("SERVER IpAddress : " + eElement.getElementsByTagName("IpAddress").item(0).getTextContent());
               System.out.println("SERVER Username : " + eElement.getElementsByTagName("Username").item(0).getTextContent());
               System.out.println("SERVER Password : " + eElement.getElementsByTagName("Password").item(0).getTextContent());
            }
         }
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		System.out.println("server disconnect");
	}

	@Override
	public void setNodeInformation() {
		// TODO Auto-generated method stub
		System.out.println("server setnodeinfo");
	}



}
