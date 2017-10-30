package uniqueInstance;

import java.io.FileNotFoundException;
import java.io.FileReader;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;


/**
 * this class ensures that there will be a unique instance of the CoreV1Api
 * I did this because I believe that these objects deals with the runtime of the k8.
 * Also, this class deals with the configuration of the k8 cluster, setting the file with 
 * the configuration script
 * @author Vinicius
 *
 */
public class ConfigGFADS {
	
	private static final String pathToGfadsConfigFile = "/home/vinicius/Documentos/gfads-test.config";
	private static FileReader gfadsConfigFile;
	private static KubeConfig kc;
	private static ApiClient client;

	private static CoreV1Api api = null;
	private static AppsV1beta1Api apiBeta = null;


	public ConfigGFADS() throws FileNotFoundException {
		gfadsConfigFile = new FileReader(pathToGfadsConfigFile );
		kc = KubeConfig.loadKubeConfig(gfadsConfigFile);
		client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);		
	}
	
	public static ApiClient getApiClient() {
		return client;
	}
	
	/**
	 * this method will return the unique instance of the k8 runtime deployment object
	 * @return the runtime object
	 */
	
	public static AppsV1beta1Api getAppsV1BetaApi() {
		if(apiBeta ==null)
			apiBeta = new AppsV1beta1Api();
		return apiBeta;
	}

	/**
	 * this method will return the unique instance of the k8 runtime pod object
	 * @return the runtime object
	 */
	public static CoreV1Api getCoreV1ApiInstance() {
		if(api == null)
			api = new CoreV1Api();
		return api;			
	}


}
