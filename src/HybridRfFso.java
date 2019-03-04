import nodefactory.*;

public class HybridRfFso {

	public static void main(String[] args) {
		// TODO Auto-grenerated method stub
		NodeFactory myRfNodeFactory,myFsoNodeFactory;
		RemoteNode fsoNodeTcp;
		myFsoNodeFactory = new FsoNodeFactory();
		fsoNodeTcp = myFsoNodeFactory.createNode("ENGTOBUS", "TCP");
		
		fsoNodeTcp.getClient();
		fsoNodeTcp.getServer();
		fsoNodeTcp.getNode();

	}

}
