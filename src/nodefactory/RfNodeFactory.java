package nodefactory;

public class RfNodeFactory implements NodeFactory{
	public RemoteNode createNode(String direction, String protocol) {
		if (protocol.equals("UDP")) {
			return new RfNodeUdp(direction);
		}else if (protocol.equals("TCP")) {
			return new RfNodeTcp(direction);
		}else {
			return null;
		}
	}
}
