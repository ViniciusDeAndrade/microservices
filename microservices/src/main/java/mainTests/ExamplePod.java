package mainTests;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;

import java.io.FileReader;
import java.io.IOException;
/**
 * inspecting pods
 * @author vinicius
 *
 */
public class ExamplePod {
	public static void main(String[] args) throws IOException, ApiException{
		final String pathToGfadsConfigFile = "/home/vinicius/Documentos/gfads-test.config";
		FileReader gfadsConfigFile;
		KubeConfig kc;
		gfadsConfigFile = new FileReader(pathToGfadsConfigFile );
		kc = KubeConfig.loadKubeConfig(gfadsConfigFile);

		ApiClient client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);


		CoreV1Api api = new CoreV1Api();
		V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null);
		for (V1Pod item : list.getItems()) 
			if(item.getMetadata().getName().equalsIgnoreCase("kube-proxy-8ktf4"))
				System.out.println(item.getSpec().getNodeName());
	}
}