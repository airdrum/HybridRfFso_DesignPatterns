package nodefactory;

public class RfFsoNodeFactory implements NodeFactory{
	public RemoteNode createNode(String direction, String protocol) {
		if (protocol.equals("UDP")) {
			return new RfFsoNodeUdp(direction);
		}else if (protocol.equals("TCP")) {
			return new RfFsoNodeTcp(direction);
		}else {
			return null;
		}
	}
}
