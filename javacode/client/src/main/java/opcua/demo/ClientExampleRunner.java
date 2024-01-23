package opcua.demo;

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

public class ClientExampleRunner {

    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        // Security.addProvider(new BouncyCastleProvider());
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    private final ClientExample clientExample;

    private final String url = "opc.tcp://192.168.31.66:4840";

    public ClientExampleRunner(ClientExample clientExample) throws Exception {
        this.clientExample = clientExample;
    }

    //Get a client with the specified url.
    private OpcUaClient createClient() throws Exception {
        SecurityPolicy securityPolicy = SecurityPolicy.None;
        List<EndpointDescription> endpoints;
        try {
            endpoints = DiscoveryClient.getEndpoints(url).get();
        } catch (Throwable ex) {
            // 发现服务
            String discoveryUrl = url;
            if (!discoveryUrl.endsWith("/")) {
                discoveryUrl += "/";
            }
            discoveryUrl += "discovery";
            endpoints = DiscoveryClient.getEndpoints(discoveryUrl).get();
        }

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
            .setRequestTimeout(uint(5000))
            .build();
        return OpcUaClient.create(config);
    }


    public void run() {
        try {
            OpcUaClient client = createClient();

            //To release the source of client when complete is called.
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
                } catch (InterruptedException | ExecutionException e) {
                    logger.error("Error disconnecting: {}", e.getMessage(), e);
                }
                //exit Thread
                try {
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            try {
                //do bussiness here
                clientExample.run(client, future);
                //blocked here for some seconds
                future.get(10, TimeUnit.SECONDS);
                // future.get();
            } catch (Throwable t) {
                logger.error("Error running client example: {}", t.getMessage(), t);
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
