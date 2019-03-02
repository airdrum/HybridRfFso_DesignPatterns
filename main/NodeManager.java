

public class NodeManager {
	//use getShape method to get object of type shape 
	   public NodeImplementor getNode(String nodeType){
	      if(nodeType == null){
	         return null;
	      }		
	      if(nodeType.equalsIgnoreCase("SERVER")){
	         return new NodeServer();
	         
	      } else if(nodeType.equalsIgnoreCase("CLIENT")){
	         return new NodeClient();
	         
	      } 
	      
	      return null;
	   }
}
