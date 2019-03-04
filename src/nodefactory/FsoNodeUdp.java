package nodefactory;
import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class FsoNodeUdp implements RemoteNode {

	private NodeList nodeServer;
	private String m_server;
	private String m_serverIpAddress;
	private String m_serverUserName;
	private String m_serverPassword;
	private String m_client;
	private String m_clientIpAddress;
	private String m_clientUserName;
	private String m_clientPassword;
	private String m_direction;
	private String m_protocol;
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
	private JSONObject m_serverObject = null;
	private JSONObject m_clientObject = null;
	public FsoNodeUdp(String direction) {
		m_serverObject =  new JSONObject();
		m_clientObject =  new JSONObject();
		direction = direction.toUpperCase();
		this.m_direction = direction;
		if(direction.equals("ENGTOBUS")) {

		   
		      
			setnode("Business");
			this.m_server = "Business";
			this.m_protocol = UDP;
			m_serverObject.put("name","server");
			m_serverObject.put("location",this.m_server);
			m_serverObject.put("interfaceType","FSO");
			m_serverObject.put("protocol",UDP);
			m_serverObject.put("serverUsername",getM_username());
			m_serverObject.put("serverIpAddress",getM_ipAddress());
			m_serverObject.put("serverPassword",getM_password());
			
			

			setnode("Engineering");
			this.m_client = "Business";
			m_clientObject.put("name","server");
			m_clientObject.put("location",this.m_client);
			m_clientObject.put("interfaceType","FSO");
			m_clientObject.put("protocol",UDP);
			m_clientObject.put("clientUsername",getM_username());
			m_clientObject.put("clientIpAddress",getM_ipAddress());
			m_clientObject.put("clientPassword",getM_password());
			
		}else if(direction.equals("BUSTOENG")) {
			this.m_server = "Engineering";
			this.m_client = "Business";
			this.m_protocol = UDP;
		}
	}

	@Override
	public JSONObject getServer() {
		return m_serverObject;
	}

	@Override
	public JSONObject getClient() {
		return m_clientObject;
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
               }
          }
 		

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

	public String getM_clientIpAddress() {
		return m_clientIpAddress;
	}

	public void setM_clientIpAddress(String m_clientIpAddress) {
		this.m_clientIpAddress = m_clientIpAddress;
	}

	public String getM_clientUserName() {
		return m_clientUserName;
	}

	public void setM_clientUserName(String m_clientUserName) {
		this.m_clientUserName = m_clientUserName;
	}

	public String getM_clientPassword() {
		return m_clientPassword;
	}

	public void setM_clientPassword(String m_clientPassword) {
		this.m_clientPassword = m_clientPassword;
	}

	public String getM_serverIpAddress() {
		return m_serverIpAddress;
	}

	public void setM_serverIpAddress(String m_serverIpAddress) {
		this.m_serverIpAddress = m_serverIpAddress;
	}

	public String getM_serverPassword() {
		return m_serverPassword;
	}

	public void setM_serverPassword(String m_serverPassword) {
		this.m_serverPassword = m_serverPassword;
	}

	public String getM_serverUserName() {
		return m_serverUserName;
	}

	public void setM_serverUserName(String m_serverUserName) {
		this.m_serverUserName = m_serverUserName;
	}

	

}
