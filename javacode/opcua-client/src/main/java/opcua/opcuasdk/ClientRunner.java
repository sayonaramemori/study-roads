package opcua.opcuasdk;

// import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
// import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.security.Security;
import java.util.List;
import java.util.concurrent.CompletableFuture;
// import java.io.File;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ClientRunner {
    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        // Security.addProvider(new BouncyCastleProvider());
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //Control the client life time by call future.complete
    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    //You truely wanna run.
    private final Client clientExample;

    private final String url = "opc.tcp://192.168.31.66:4840";

    //default client life time (second)
    private long timeOut = 15;

    //if blockTime(seconds) equals to zero, then the block Time is infinitive in this case you must call complete method by hand.
    //blockTime should be great than or equal to zero
    public ClientRunner(Client clientExample, long blockTime) throws Exception {
        this.clientExample = clientExample;
        if(blockTime>=0)
            this.timeOut = blockTime;
    }

    public ClientRunner(Client clientExample) throws Exception {
        this.clientExample = clientExample;
    }

    //Get a client with the specified url.
    private OpcUaClient createClient(){
        SecurityPolicy securityPolicy = SecurityPolicy.None;
        List<EndpointDescription> endpoints;
        try {
            endpoints = DiscoveryClient.getEndpoints(url).get();
        } catch (Exception ex) {
            // 发现服务
            logger.warn("Reconstruct url {} for:{}", this.url, ex.getMessage());
            String discoveryUrl = url;
            if (!discoveryUrl.endsWith("/")) {
                discoveryUrl += "/";
            }
            discoveryUrl += "discovery";
            try{
                endpoints = DiscoveryClient.getEndpoints(discoveryUrl).get();
            }catch(Exception innerEx){
                logger.error(discoveryUrl, innerEx);
                return null;
            }
        }

        try{
            EndpointDescription endpoint = endpoints.stream()
                    .filter(e -> e.getSecurityPolicyUri().equals(securityPolicy.getUri()))
                    .findFirst()
                    .orElseThrow(() -> new Exception("connect endpoint fail"));

            //this is very important, or you can solve it by modifying the server DNS resolutions.
            endpoint = EndpointUtil.updateUrl(endpoint, "192.168.31.66",4840);
            // System.out.println(endpoint.getEndpointUrl());

            OpcUaClientConfig config = OpcUaClientConfig.builder()
                .setApplicationName(LocalizedText.english("opc ua client"))
                .setApplicationUri("urn:opc:ua:client")
                // .setCertificate(loader.getClientCertificate())          
                // .setKeyPair(loader.getClientKeyPair())
                .setEndpoint(endpoint)
                //根据匿名验证和第三个用户名验证方式设置传入对象 AnonymousProvider（匿名方式）UsernameProvider（账户密码）
                //new UsernameProvider("admin","123456")
                .setIdentityProvider(new AnonymousProvider())
                //the timeout should be extract and rebuild
                .setRequestTimeout(uint(5000))
                .build();
            logger.debug("Create client successfully");
            return OpcUaClient.create(config);
        }
        catch(Exception ex){
            logger.error("Create client error for:"+ex.getMessage());
            return null;
        }
    }


    public void run() {
        try {
            OpcUaClient client = createClient();

            //To release the source of client when complete is called.
            //To ensure this function to be invoked in safe or other way.
            future.whenCompleteAsync((c, ex) -> {
                //c is result but no meaning
                if (ex != null) {
                    logger.error("Error running example: {}", ex.getMessage(), ex);
                }
                //release source
                try {
                    //why client can be captured.
                    client.disconnect().get();
                    Stack.releaseSharedResources();
                    //System.out.println("All resource is deleted");
                } catch (InterruptedException | ExecutionException e) {
                    logger.error("Error disconnecting: {}", e.getMessage(), e);
                }
                //exit Thread
                try {
                    Thread.sleep(1000);
                    //System.out.println("exit is invoked");
                    //System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            try {
                //do bussiness here
                clientExample.run(client, future);
                //blocked here for some seconds which is the lifetime of the client. Call that function in the specific time.
                //get method must be called, or the main thread will be finished quickly.
                if(timeOut==0){
                    future.get();
                }else{
                    //You can safely call complete method for this case
                    future.get(timeOut, TimeUnit.SECONDS);
                }
                //left some time to invoke complete function, infinitive is the best.
                Thread.sleep(20000);
                //You must call complete function when you use this method. Then exit is needed
                //future.get();
            } catch (Throwable t) {
                logger.error("Error running client: {}", t.getMessage(), t);
                future.completeExceptionally(t);
            }
        } catch (Throwable t) {
            logger.error("Error getting client: {}", t.getMessage(), t);
            future.completeExceptionally(t);
            try {
                Thread.sleep(1000);          
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
