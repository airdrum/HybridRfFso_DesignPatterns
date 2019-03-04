import org.json.simple.JSONObject;

import nodefactory.*;

public class HybridRfFso {

	public static void main(String[] args) {
		// TODO Auto-grenerated method stub
		NodeFactory myRfNodeFactory,myFsoNodeFactory;
		RemoteNode fsoNodeTcp, rfNodeUdp;
		myFsoNodeFactory = new FsoNodeFactory();
		myRfNodeFactory = new RfNodeFactory();
		fsoNodeTcp = myFsoNodeFactory.createNode("ENGTOBUS", "TCP");
		rfNodeUdp = myRfNodeFactory.createNode("BUSTOENG", "UDP");
		System.out.println("*****");
		System.out.println(fsoNodeTcp.getServer());
		System.out.println(fsoNodeTcp.getClient());
		System.out.println(rfNodeUdp.getServer());
		System.out.println(rfNodeUdp.getClient());
		
		
		

	}

}
