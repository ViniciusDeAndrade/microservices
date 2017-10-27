package mainTests;

import java.io.FileNotFoundException;
import java.io.FileReader;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Node;
import io.kubernetes.client.models.V1NodeList;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
/**
 * inspecting nodes
 * @author vinicius
 *
 */
public class Gfads {
	public static void main(String[] args) throws ApiException, FileNotFoundException {

		final String pathToGfadsConfigFile = "/home/vinicius/Documentos/gfads-test.config";
		FileReader gfadsConfigFile;
		KubeConfig kc;
		gfadsConfigFile = new FileReader(pathToGfadsConfigFile );
		kc = KubeConfig.loadKubeConfig(gfadsConfigFile);

		ApiClient client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);

		CoreV1Api api = new CoreV1Api();
		//update node
		//api.patchNode("swarm1", body, pretty)


		V1NodeList list = api.listNode(null, null, null, null, null, null);
		for (V1Node item : list.getItems())
			if(item.getMetadata().getName().equalsIgnoreCase("swarm1"))
				System.out.println();		
	}
}
