package mainTests;

import java.io.FileNotFoundException;

import abstraction.PodAbstraction;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.models.V1Pod;
import microservices.RuntimeDealer;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, ApiException {
		final String podName = "etcd-swarm1";

		//final String oldNode = "swarm1";
		final String newNode = "swarm5";
		
		PodAbstraction pa = new PodAbstraction(podName, newNode);
		RuntimeDealer rd = new RuntimeDealer();
		V1Pod pod = rd.searchPod(podName);
		
		//parei aqui
		pa.updateNodeSelector(pod);		
		
		
	}

}
