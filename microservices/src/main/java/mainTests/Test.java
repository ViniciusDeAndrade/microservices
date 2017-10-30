package mainTests;

import java.io.FileNotFoundException;
import java.util.Scanner;

import abstraction.PodAbstraction;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.models.V1Pod;
import microservices.RuntimeDealer;
import uniqueInstance.ConfigGFADS;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, ApiException {
		ConfigGFADS gfads = new ConfigGFADS();
		final String podName = "etcd-swarm1";

		//final String oldNode = "swarm1";
		final String newNode = "swarm5";
		
		PodAbstraction podAbstraction = new PodAbstraction(podName, newNode);
		RuntimeDealer runtimeDealer = new RuntimeDealer();
		V1Pod pod = runtimeDealer.searchPod(podName);
		
		podAbstraction.updateNodeSelector(pod);	
				
	}

}
