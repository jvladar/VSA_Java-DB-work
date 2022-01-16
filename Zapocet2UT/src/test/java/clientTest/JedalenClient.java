/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:JedalenResource [jedalen]<br>
 * USAGE:
 * <pre>
        JedalenClient client = new JedalenClient();
        Object response = client.XXX(...);
        // do whatever with response
        client.close();
 </pre>
 *
 * @author igor
 */
public class JedalenClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:9999/RestUnitTest/resources";

    public JedalenClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("menu");
    }

        public <T> T getJedlo(Class<T> responseType, String den, String n) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, n}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String getPocet(String den) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{den}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T getPocet(Class<T> responseType, String den) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{den}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public <T> T getMenu(Class<T> responseType, String den) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{den}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String postJedlo(Object requestEntity, String den) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{den})).request(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
    }

    public void delJedlo(String den, String n) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, n})).request().delete();
    }

    public String find(String nazov) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nazov != null) {
            resource = resource.queryParam("nazov", nazov);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}

//    public <T> T getJedlo(Class<T> responseType, String den, String n) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, n}));
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
//    }
//
//    public String getPocet(String den) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{den}));
//        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
//    }
//
//    public <T> T getPocet(Class<T> responseType, String den) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{den}));
//        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
//    }
//    
//    public <T> T getMenu(Class<T> responseType, String den) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{den}));
//        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
//    }
//
//    public void postJedlo(Object requestEntity, String den) throws ClientErrorException {
//        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{den})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
//    }
//
//    public void delJedlo(String den, String n) throws ClientErrorException {
//        webTarget.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{den, n})).request().delete();
//    }
//
//    public String find(String nazov) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        if (nazov != null) {
//            resource = resource.queryParam("nazov", nazov);
//        }
//        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
//    }
//
//    
//    
//    public void close() {
//        client.close();
//    }
//    
//}
