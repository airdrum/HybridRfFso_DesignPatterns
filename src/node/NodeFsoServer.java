package node;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeFsoServer implements NodeImplementor{
	
	private NodeList nodeServer;
	private String m_ipAddress;
	private String m_username;
	private String m_password;
	private String m_rfInterfaceIpAddress;
	private String m_rfDutIpAddress;
	private String m_rfDutTelnetName;
	private String m_rfDutTelnetPassword;
	private String m_fsoInterfaceIpAddress;
	private String m_fsoDutIpAddress;
	private String m_fsoDutTelnetName;
	private String m_fsoDutTelnetPassword;
	
	@Override
	public void setnode(String nodeType) {
		// TODO Auto-generated method stub
		
		nodeServer = getNode();
		for (int temp = 0; temp < nodeServer.getLength(); temp++) {
            Node nNode = nodeServer.item(temp);
              
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("name");
                if(name.equals(nodeType)) {
             	   //System.out.println("SERVER Name : " + eElement.getAttribute("name") +" Id : " + eElement.getAttribute("id"));
             	   setM_ipAddress(eElement.getElementsByTagName("IpAddress").item(0).getTextContent());
             	   setM_username(eElement.getElementsByTagName("Username").item(0).getTextContent());
             	   setM_password(eElement.getElementsByTagName("Password").item(0).getTextContent());
             	   setM_rfInterfaceIpAddress(eElement.getElementsByTagName("RfInterfaceIpAddress").item(0).getTextContent());
             	   setM_rfDutIpAddress(eElement.getElementsByTagName("RfDutIpAddress").item(0).getTextContent());
             	   setM_rfDutTelnetName(eElement.getElementsByTagName("RfDutTelnetName").item(0).getTextContent());
             	   setM_rfDutTelnetPassword(eElement.getElementsByTagName("RfDutTelnetPassword").item(0).getTextContent());
             	   setM_fsoInterfaceIpAddress(eElement.getElementsByTagName("FsoInterfaceIpAddress").item(0).getTextContent());
             	   setM_fsoDutIpAddress(eElement.getElementsByTagName("FsoDutIpAddress").item(0).getTextContent());
             	   setM_fsoDutTelnetName(eElement.getElementsByTagName("FsoDutTelnetName").item(0).getTextContent());
             	   setM_fsoDutTelnetPassword(eElement.getElementsByTagName("FsoDutTelnetPassword").item(0).getTextContent());
             	   
             	                  
                }
// 	               m_ipAddress = eElement.getElementsByTagName("IpAddress").item(0).getTextContent();
// 	               m_username = eElement.getElementsByTagName("Username").item(0).getTextContent();
// 	               m_password = eElement.getElementsByTagName("Password").item(0).getTextContent();
               }
          }
 		
 		System.out.println(nodeType + " ipAddress: " +getM_ipAddress());
 		System.out.println(nodeType + " username: " +getM_username());
 		System.out.println(nodeType + " password: " +getM_password());
 		System.out.println(nodeType + " rfInterfaceIpAddress: " +getM_rfInterfaceIpAddress());
 		System.out.println(nodeType + " rfDutIpAddress: " +getM_rfDutIpAddress());
 		System.out.println(nodeType + " rfDutTelnetName: " +getM_rfDutTelnetName());
 		System.out.println(nodeType + " rfDutTelnetPassword: " +getM_rfDutTelnetPassword());
 		System.out.println(nodeType + " fsoInterfaceIpAddress: " +getM_fsoInterfaceIpAddress());
 		System.out.println(nodeType + " fsoDutIpAddress: " +getM_fsoDutIpAddress());
 		System.out.println(nodeType + " fsoDutTelnetName: " +getM_fsoDutTelnetName());
 		System.out.println(nodeType + " fsoDutTelnetPassword: " +getM_fsoDutTelnetPassword());
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

	public String getM_ipAddress() {
		return m_ipAddress;
	}

	public void setM_ipAddress(String m_ipAddress) {
		this.m_ipAddress = m_ipAddress;
	}

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_rfInterfaceIpAddress() {
		return m_rfInterfaceIpAddress;
	}

	public void setM_rfInterfaceIpAddress(String m_rfInterfaceIpAddress) {
		this.m_rfInterfaceIpAddress = m_rfInterfaceIpAddress;
	}

	public String getM_rfDutIpAddress() {
		return m_rfDutIpAddress;
	}

	public void setM_rfDutIpAddress(String m_rfDutIpAddress) {
		this.m_rfDutIpAddress = m_rfDutIpAddress;
	}

	public String getM_fsoDutTelnetPassword() {
		return m_fsoDutTelnetPassword;
	}

	public void setM_fsoDutTelnetPassword(String m_fsoDutTelnetPassword) {
		this.m_fsoDutTelnetPassword = m_fsoDutTelnetPassword;
	}

	public String getM_fsoDutTelnetName() {
		return m_fsoDutTelnetName;
	}

	public void setM_fsoDutTelnetName(String m_fsoDutTelnetName) {
		this.m_fsoDutTelnetName = m_fsoDutTelnetName;
	}

	public String getM_fsoDutIpAddress() {
		return m_fsoDutIpAddress;
	}

	public void setM_fsoDutIpAddress(String m_fsoDutIpAddress) {
		this.m_fsoDutIpAddress = m_fsoDutIpAddress;
	}

	public String getM_fsoInterfaceIpAddress() {
		return m_fsoInterfaceIpAddress;
	}

	public void setM_fsoInterfaceIpAddress(String m_fsoInterfaceIpAddress) {
		this.m_fsoInterfaceIpAddress = m_fsoInterfaceIpAddress;
	}

	public String getM_rfDutTelnetPassword() {
		return m_rfDutTelnetPassword;
	}

	public void setM_rfDutTelnetPassword(String m_rfDutTelnetPassword) {
		this.m_rfDutTelnetPassword = m_rfDutTelnetPassword;
	}

	public String getM_rfDutTelnetName() {
		return m_rfDutTelnetName;
	}

	public void setM_rfDutTelnetName(String m_rfDutTelnetName) {
		this.m_rfDutTelnetName = m_rfDutTelnetName;
	}





}
