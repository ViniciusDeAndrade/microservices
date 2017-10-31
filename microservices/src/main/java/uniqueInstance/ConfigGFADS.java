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
	
	private  final String pathToGfadsConfigFile = "/home/vinicius/Documentos/gfads-test.config";
	private FileReader gfadsConfigFile;
	private KubeConfig kc;
	private ApiClient client;

	private CoreV1Api api = null;
	private AppsV1beta1Api apiBeta = null;


	public ConfigGFADS() throws FileNotFoundException {
		gfadsConfigFile = new FileReader(pathToGfadsConfigFile );
		kc = KubeConfig.loadKubeConfig(gfadsConfigFile);
		client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);
		client.setConnectTimeout(10000);
	}
	
	public ApiClient getApiClient() {
		return client;
	}
	
	/**
	 * this method will return the unique instance of the k8 runtime deployment object
	 * @return the runtime object
	 */
	
	public  AppsV1beta1Api getAppsV1BetaApi() {
		if(apiBeta ==null)
			apiBeta = new AppsV1beta1Api(client);
		return apiBeta;
	}

	/**
	 * this method will return the unique instance of the k8 runtime pod object
	 * @return the runtime object
	 */
	public  CoreV1Api getCoreV1ApiInstance() {
		if(api == null)
			api = new CoreV1Api(client);
		return api;			
	}


}
