package nodefactory;


import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FsoNodeTcp implements RemoteNode {
	private NodeList nodeServer;
	
	
	/*
	 * CLIENT INFORMATION in PRIVATE
	 */
	
	private String m_remoteIpAddress;
	private String m_remoteUserName;
	private String m_remotePassword;
	/*
	 * SERVER INFORMATION in PRIVATE
	 */


	private JSONObject m_nodeObject = null;

	private String m_interfaceIpAddress;
	private String m_dutIpAddress;
	private String m_dutTelnetName;
	private String m_dutTelnetPassword;
	
	@SuppressWarnings("unchecked")
	public FsoNodeTcp(String direction) {
		m_nodeObject =  new JSONObject();
		direction = direction.toUpperCase();
		if(direction.equals("ENGTOBUS")) {

			setnode("Business");

			try {
				m_nodeObject.put("ServerRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ServerLocation","Business");
				m_nodeObject.put("ServerInterfaceType","FSO");
				m_nodeObject.put("ServerProtocol",TCP);
				m_nodeObject.put("ServerRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ServerRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ServerInterfaceIpAddress",this.m_interfaceIpAddress);
				m_nodeObject.put("ServerDutIpAddress",this.m_dutIpAddress);
				m_nodeObject.put("ServerDutTelnetName",this.m_dutTelnetName);
				m_nodeObject.put("ServerDutTelnetPassword",this.m_dutTelnetPassword);
			} catch (Exception e) {
				assert e != null;
			}

			setnode("Engineering");
			try {
				m_nodeObject.put("ClientRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ClientLocation","Engineering");
				m_nodeObject.put("ClientInterfaceType","FSO");
				m_nodeObject.put("ClientProtocol",TCP);
				m_nodeObject.put("ClientRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ClientRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ClientInterfaceIpAddress",this.m_interfaceIpAddress);
				m_nodeObject.put("ClientDutIpAddress",this.m_dutIpAddress);
				m_nodeObject.put("ClientDutTelnetName",this.m_dutTelnetName);
				m_nodeObject.put("ClientDutTelnetPassword",this.m_dutTelnetPassword);
			} catch (Exception e) {
				assert e != null;
			}
			
		}else if(direction.equals("BUSTOENG")) {
			/*SET SERVER LOCATION*/
			setnode("Engineering");

			try {
				m_nodeObject.put("ServerRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ServerLocation","Engineering");
				m_nodeObject.put("ServerInterfaceType","FSO");
				m_nodeObject.put("ServerProtocol",TCP);
				m_nodeObject.put("ServerRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ServerRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ServerInterfaceIpAddress",this.m_interfaceIpAddress);
				m_nodeObject.put("ServerDutIpAddress",this.m_dutIpAddress);
				m_nodeObject.put("ServerDutTelnetName",this.m_dutTelnetName);
				m_nodeObject.put("ServerDutTelnetPassword",this.m_dutTelnetPassword);
			} catch (Exception e) {
				assert e != null;
			}
			/*SET CLIENT LOCATION*/
			setnode("Business");
			try {
				m_nodeObject.put("ClientRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ClientLocation","Business");
				m_nodeObject.put("ClientInterfaceType","FSO");
				m_nodeObject.put("ClientProtocol",TCP);
				m_nodeObject.put("ClientRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ClientRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ClientInterfaceIpAddress",this.m_interfaceIpAddress);
				m_nodeObject.put("ClientDutIpAddress",this.m_dutIpAddress);
				m_nodeObject.put("ClientDutTelnetName",this.m_dutTelnetName);
				m_nodeObject.put("ClientDutTelnetPassword",this.m_dutTelnetPassword);
			} catch (Exception e) {
				assert e != null;
			}
		}
	}

	@Override
	public JSONObject getNodeObject() {
		return m_nodeObject;
	}
	
	public void setnode(String nodeType) {
		// TODO Auto-generated method stub
		
		nodeServer = getNode();
		for (int temp = 0; temp < nodeServer.getLength(); temp++) {
            Node nNode = nodeServer.item(temp);
              
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("name");
                if(name.equals(nodeType)) {
             	   this.m_remoteIpAddress = eElement.getElementsByTagName("IpAddress").item(0).getTextContent();
             	   this.m_remotePassword = eElement.getElementsByTagName("Password").item(0).getTextContent();
             	   this.m_remoteUserName = eElement.getElementsByTagName("Username").item(0).getTextContent();
             	   this.m_interfaceIpAddress = eElement.getElementsByTagName("FsoInterfaceIpAddress").item(0).getTextContent();
             	   this.m_dutIpAddress = eElement.getElementsByTagName("FsoDutIpAddress").item(0).getTextContent();
             	   this.m_dutTelnetName = eElement.getElementsByTagName("FsoDutTelnetName").item(0).getTextContent();
             	   this.m_dutTelnetPassword = eElement.getElementsByTagName("FsoDutTelnetPassword").item(0).getTextContent();
                }
            }
        }
	}

}
