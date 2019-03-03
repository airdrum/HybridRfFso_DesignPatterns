package nodefactory;

public class RfNodeTcp implements RemoteNode {

	private String m_server="";
	private String m_client="";
	private String m_direction = "";
	
	public RfNodeTcp(String direction) {
		
		direction = direction.toUpperCase();
		this.m_direction = direction;
		if(direction.equals("ENGTOBUS")) {
			this.m_server = "Business";
			this.m_client = "Engineering";
		}else if(direction.equals("BUSTOENG")) {
			this.m_server = "Engineering";
			this.m_client = "Business";
		}
	}

	@Override
	public void getServer() {
		// TODO Auto-generated method stub
		System.out.println(TCP + " " + RF +" SERVER: m_direction: " + this.m_direction);
		System.out.println(TCP + " " + RF +" SERVER: m_server: " + this.m_server);
		System.out.println(TCP + " " + RF +" SERVER: m_client: " + this.m_client);
	}

	@Override
	public void getClient() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println(TCP + " " + RF +" CLIENT: m_direction: " + this.m_direction);
		System.out.println(TCP + " " + RF +" CLIENT: m_server: " + this.m_server);
		System.out.println(TCP + " " + RF +" CLIENT: m_client: " + this.m_client);
	}

	@Override
	public void setnode(String nodeType) {
		// TODO Auto-generated method stub
		
	}

}
