package opcua.opcuasdk;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;


public interface Client {
    static String url = "192.168.31.66";
    static Integer port = 4840;
    static Integer nameSpaceIndex = 4;
    static String protocl = "opc.tcp://";
    static Integer timeOut = 5;

    public static Integer getNameSpaceIndex(){
        return nameSpaceIndex;
    }
    
    default String getEndpointUrl() {
        return protocl + url + port;
    }

    default Predicate<EndpointDescription> endpointFilter() {
        return e -> getSecurityPolicy().getUri().equals(e.getSecurityPolicyUri());
    }

    default SecurityPolicy getSecurityPolicy() {
//        return SecurityPolicy.Basic256Sha256;
//        return SecurityPolicy.Basic128Rsa15;
        return SecurityPolicy.None;
    }

    default IdentityProvider getIdentityProvider() {
//        return new UsernameProvider("ua_user","IwjqYp(8F+gW~^1");
        return new AnonymousProvider();
    }

    default void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception{
        return;
    }
}
