
public class TestLogManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				NodeManager nodeFactory = new NodeManager();

			      //RF interfaces.
			      NodeImplementor nodeserverRf = nodeFactory.getNode("SERVER");
			      NodeImplementor nodeclientRf = nodeFactory.getNode("CLIENT");
			      //RF interfaces.
			      NodeImplementor nodeserverFso = nodeFactory.getNode("SERVER");
			      NodeImplementor nodeclientFso = nodeFactory.getNode("CLIENT");
			      //call draw method of Circle
			      nodeserverRf.setnode();
			    //call draw method of Circle
			      nodeclientRf.setnode();
			      
			      
			      
	}
}