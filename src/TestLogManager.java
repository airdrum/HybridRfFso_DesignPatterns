import node.NodeImplementor;
import node.NodeManager;

public class TestLogManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				NodeManager nodeFactory = new NodeManager();
				
			      //RF interfaces.
			      NodeImplementor nodeserverRf = nodeFactory.getNode("RFSERVER");
			      NodeImplementor nodeclientRf = nodeFactory.getNode("RFCLIENT");
			      //RF interfaces.
			      NodeImplementor nodeserverFso = nodeFactory.getNode("FSOSERVER");
			      NodeImplementor nodeclientFso = nodeFactory.getNode("FSOCLIENT");
			      //call draw method of Circle
			      System.out.println("**************");
			      nodeserverRf.setnode("Engineering");

			      System.out.println("**************");
			      nodeclientRf.setnode("Business");

			      System.out.println("**************");
			      nodeserverFso.setnode("Engineering");

			      System.out.println("**************");
			      nodeclientFso.setnode("Business");
			    
			      
			      
	}
}