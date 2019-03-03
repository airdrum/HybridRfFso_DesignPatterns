package node;



public class NodeManager {
	//use getShape method to get object of type shape 
	   public NodeImplementor getNode(String nodeType){
	      if(nodeType == null){
	         return null;
	      }		
	      if(nodeType.equalsIgnoreCase("RFSERVER")){
	         return new NodeRfServer();
	         
	      } else if(nodeType.equalsIgnoreCase("RFCLIENT")){
	         return new NodeRfClient();
	         
	      } else if(nodeType.equalsIgnoreCase("FSOSERVER")){
	         return new NodeFsoServer();
		         
	      } else if(nodeType.equalsIgnoreCase("FSOCLIENT")){
	         return new NodeFsoClient();
	         
	      } 
	      
	      return null;
	   }
}
