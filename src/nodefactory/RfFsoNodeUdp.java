package nodefactory;

import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RfFsoNodeUdp implements RemoteNode {
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

	private String m_rfInterfaceIpAddress;
	private String m_rfDutIpAddress;
	private String m_rfDutTelnetName;
	private String m_rfDutTelnetPassword;
	private String m_fsoInterfaceIpAddress;
	private String m_fsoDutIpAddress;
	private String m_fsoDutTelnetName;
	private String m_fsoDutTelnetPassword;
	
	public RfFsoNodeUdp(String direction) {
		m_nodeObject =  new JSONObject();
		direction = direction.toUpperCase();
		if(direction.equals("ENGTOBUS")) {

			setnode("Business");
			
			try {
				m_nodeObject.put("ServerRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ServerLocation","Business");
				m_nodeObject.put("ServerInterfaceType","RFFSO");
				m_nodeObject.put("ServerProtocol",UDP);
				m_nodeObject.put("ServerRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ServerRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ServerRfInterfaceIpAddress",this.m_rfInterfaceIpAddress);
				m_nodeObject.put("ServerRfDutIpAddress",this.m_rfDutIpAddress);
				m_nodeObject.put("ServerRfDutTelnetName",this.m_rfDutTelnetName);
				m_nodeObject.put("ServerRfDutTelnetPassword",this.m_rfDutTelnetPassword);
				m_nodeObject.put("ServerFsoInterfaceIpAddress",this.m_fsoInterfaceIpAddress);
				m_nodeObject.put("ServerFsoDutIpAddress",this.m_fsoDutIpAddress);
				m_nodeObject.put("ServerFsoDutTelnetName",this.m_fsoDutTelnetName);
				m_nodeObject.put("ServerFsoDutTelnetPassword",this.m_fsoDutTelnetPassword);

			} catch (Exception e) {
				assert e != null;
			}

			setnode("Engineering");
			try {
				m_nodeObject.put("ClientRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ClientLocation","Engineering");
				m_nodeObject.put("ClientInterfaceType","RFFSO");
				m_nodeObject.put("ClientProtocol",UDP);
				m_nodeObject.put("ClientRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ClientRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ClientRfInterfaceIpAddress",this.m_rfInterfaceIpAddress);
				m_nodeObject.put("ClientRfDutIpAddress",this.m_rfDutIpAddress);
				m_nodeObject.put("ClientRfDutTelnetName",this.m_rfDutTelnetName);
				m_nodeObject.put("ClientRfDutTelnetPassword",this.m_rfDutTelnetPassword);
				m_nodeObject.put("ClientFsoInterfaceIpAddress",this.m_fsoInterfaceIpAddress);
				m_nodeObject.put("ClientFsoDutIpAddress",this.m_fsoDutIpAddress);
				m_nodeObject.put("ClientFsoDutTelnetName",this.m_fsoDutTelnetName);
				m_nodeObject.put("ClientFsoDutTelnetPassword",this.m_fsoDutTelnetPassword);
			} catch (Exception e) {
				assert e != null;
			}
			
		}else if(direction.equals("BUSTOENG")) {
			/*SET SERVER LOCATION*/
			setnode("Engineering");
			
			try {
				m_nodeObject.put("ServerRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ServerLocation","Engineering");
				m_nodeObject.put("ServerInterfaceType","RFFSO");
				m_nodeObject.put("ServerProtocol",UDP);
				m_nodeObject.put("ServerRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ServerRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ServerRfInterfaceIpAddress",this.m_rfInterfaceIpAddress);
				m_nodeObject.put("ServerRfDutIpAddress",this.m_rfDutIpAddress);
				m_nodeObject.put("ServerRfDutTelnetName",this.m_rfDutTelnetName);
				m_nodeObject.put("ServerRfDutTelnetPassword",this.m_rfDutTelnetPassword);
				m_nodeObject.put("ServerFsoInterfaceIpAddress",this.m_fsoInterfaceIpAddress);
				m_nodeObject.put("ServerFsoDutIpAddress",this.m_fsoDutIpAddress);
				m_nodeObject.put("ServerFsoDutTelnetName",this.m_fsoDutTelnetName);
				m_nodeObject.put("ServerFsoDutTelnetPassword",this.m_fsoDutTelnetPassword);

			} catch (Exception e) {
				assert e != null;
			}

			setnode("Business");
			try {
				m_nodeObject.put("ClientRemoteIpAddress",this.m_remoteIpAddress);
				m_nodeObject.put("ClientLocation","Business");
				m_nodeObject.put("ClientInterfaceType","RFFSO");
				m_nodeObject.put("ClientProtocol",UDP);
				m_nodeObject.put("ClientRemoteUserName",this.m_remoteUserName);
				m_nodeObject.put("ClientRemotePassword",this.m_remotePassword);
				m_nodeObject.put("ClientRfInterfaceIpAddress",this.m_rfInterfaceIpAddress);
				m_nodeObject.put("ClientRfDutIpAddress",this.m_rfDutIpAddress);
				m_nodeObject.put("ClientRfDutTelnetName",this.m_rfDutTelnetName);
				m_nodeObject.put("ClientRfDutTelnetPassword",this.m_rfDutTelnetPassword);
				m_nodeObject.put("ClientFsoInterfaceIpAddress",this.m_fsoInterfaceIpAddress);
				m_nodeObject.put("ClientFsoDutIpAddress",this.m_fsoDutIpAddress);
				m_nodeObject.put("ClientFsoDutTelnetName",this.m_fsoDutTelnetName);
				m_nodeObject.put("ClientFsoDutTelnetPassword",this.m_fsoDutTelnetPassword);
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
             	   this.m_rfInterfaceIpAddress = eElement.getElementsByTagName("RfInterfaceIpAddress").item(0).getTextContent();
            	   this.m_rfDutIpAddress = eElement.getElementsByTagName("RfDutIpAddress").item(0).getTextContent();
            	   this.m_rfDutTelnetName = eElement.getElementsByTagName("RfDutTelnetName").item(0).getTextContent();
            	   this.m_rfDutTelnetPassword = eElement.getElementsByTagName("RfDutTelnetPassword").item(0).getTextContent();
            	   this.m_fsoInterfaceIpAddress = eElement.getElementsByTagName("FsoInterfaceIpAddress").item(0).getTextContent();
            	   this.m_fsoDutIpAddress = eElement.getElementsByTagName("FsoDutIpAddress").item(0).getTextContent();
            	   this.m_fsoDutTelnetName = eElement.getElementsByTagName("FsoDutTelnetName").item(0).getTextContent();
            	   this.m_fsoDutTelnetPassword = eElement.getElementsByTagName("FsoDutTelnetPassword").item(0).getTextContent();

                }
            }
        }
	}

}
