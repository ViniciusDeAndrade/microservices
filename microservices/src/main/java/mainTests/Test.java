package mainTests;

import java.io.FileNotFoundException;
import java.util.Scanner;

import abstraction.DeploymentAbstraction;
import abstraction.PodAbstraction;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.models.AppsV1beta1Deployment;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import microservices.RuntimeDealer;
import uniqueInstance.ConfigGFADS;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, ApiException {
		final String podName = "etcd-swarm1";

		final String oldNode = "swarm1";
		final String newNode = "swarm5";

		DeploymentAbstraction deploy = new DeploymentAbstraction();
		V1PodList podList = ConfigGFADS.getCoreV1ApiInstance().listPodForAllNamespaces(null, null, null, null, null, null);
		for(V1Pod pod: podList.getItems()) 
			if(pod.getMetadata().getName().equals(podName)) {		
				//passo 1 - setar labels aos pods.
				V1Pod newPod = deploy.setLabelsToPods(pod.getMetadata().getName(), pod.getMetadata().getNamespace(), pod, null);

			}
		//passo 2 - add selector ao deployment
		AppsV1beta1Deployment d = deploy.patchDeployment();


	}

}
