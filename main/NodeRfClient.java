import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeRfClient implements NodeImplementor{
	private NodeList nodeClient;
	@Override
	public void setnode(String nodeType) {
		// TODO Auto-generated method stub
		System.out.println("Client connect");
		nodeClient = getNode();
		for (int temp = 0; temp < nodeClient.getLength(); temp++) {
			 Node nNode = nodeClient.item(temp);
             
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               String name = eElement.getAttribute("name");
	               if(name.equals(nodeType)) {
	            	   System.out.println("CLIENT Name : " + eElement.getAttribute("name") +" Id : " + eElement.getAttribute("id"));
	                   System.out.println("CLIENT IpAddress : " + eElement.getElementsByTagName("IpAddress").item(0).getTextContent());
	                   System.out.println("CLIENT Username : " + eElement.getElementsByTagName("Username").item(0).getTextContent());
	                   System.out.println("CLIENT Password : " + eElement.getElementsByTagName("Password").item(0).getTextContent());
	                
	               }
	            }
		}
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		System.out.println("client disconnect");
	}

	@Override
	public void setNodeInformation() {
		// TODO Auto-generated method stub
		System.out.println("client setnodeinfo");
	}

}
