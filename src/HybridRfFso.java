import nodefactory.*;

public class HybridRfFso {

	public static void main(String[] args) {
		// TODO Auto-grenerated method stub
		NodeFactory myRfNodeFactory,myFsoNodeFactory;
		RemoteNode fsoNodeTcp;
		myFsoNodeFactory = new FsoNodeFactory();
		fsoNodeTcp = myFsoNodeFactory.createNode("BUSTOENG", "TCP");
		fsoNodeTcp.getClient();
		fsoNodeTcp.getServer();
		fsoNodeTcp.getNode();
		System.out.println("***********");
		fsoNodeTcp.setnode("Engineering");
		fsoNodeTcp.setnode("Business");

	}

}
