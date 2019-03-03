package throughput;

public class ThroughputInterfaceFactory implements ThroughputFactory{
	@Override
	public Throughput createThroughput(String medium, String protocol) {
		if(medium.equals(Throughput.RF)&&protocol.equals(Throughput.UDP)) {
			return new RfThroughputUdp();
		}else if(medium.equals(Throughput.RF)&&protocol.equals(Throughput.TCP)) {
			return new RfThroughputTcp();
		}else if(medium.equals(Throughput.FSO)&&protocol.equals(Throughput.UDP)) {
			return new FsoThroughputUdp();
		}else if(medium.equals(Throughput.FSO)&&protocol.equals(Throughput.TCP)) {
			return new FsoThroughputTcp();
		}else {
			return null;
		}
	}

}
