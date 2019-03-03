package nodefactory;

public class FsoNodeFactory implements NodeFactory{
	public RemoteNode createNode(String direction, String protocol) {
		if (protocol.equals("UDP")) {
			return new FsoNodeUdp(direction);
		}else if (protocol.equals("TCP")) {
			return new FsoNodeTcp(direction);
		}else {
			return null;
		}
	}
}
