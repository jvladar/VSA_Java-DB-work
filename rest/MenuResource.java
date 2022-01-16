/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author user
 */
@Singleton
@Path("menu")
public class MenuResource {

    @Context
    private UriInfo context;
    
    private List<Ponuka> ponuky;

    /**
     * Creates a new instance of MenuResource
     */
    public MenuResource() {
        ponuky = new ArrayList();
        Ponuka Pondelok = new Ponuka("pondelok");
        Pondelok.addJedlo(new Jedlo(3.5,"Gulas"));
        Ponuka Utorok = new Ponuka("utorok");
        Ponuka Streda = new Ponuka("streda");
        Ponuka Stvrtok = new Ponuka("stvrtok");
        Ponuka Piatok = new Ponuka("piatok");
        Ponuka Sobota = new Ponuka("sobota");
        Ponuka Nedela = new Ponuka("nedela");
        ponuky.add(Pondelok);
        ponuky.add(Utorok);
        ponuky.add(Streda);
        ponuky.add(Stvrtok);
        ponuky.add(Piatok);
        ponuky.add(Sobota);
        ponuky.add(Nedela);
    }

    /**
     * Retrieves representation of an instance of rest.MenuResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{den:pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getPocetJedla(@PathParam("den") String den ) {
        for (Ponuka ponuka: ponuky){
            if(ponuka.getDen().equals(den)){
                return ponuka.getJedla().size();
            }
        }
        return 0;
    }
    
    @GET
    @Path("{den: pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Jedlo> getMenu(@PathParam("den") String den) {
        for (Ponuka ponuka : ponuky) {
            if (ponuka.getDen().equals(den)) {
                return ponuka.getJedla();
            }
        }
        return null;
    }


    @POST
    @Path("{den: pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public int pridatJedlo(@PathParam("den") String den, Jedlo content) {
        if (content == null) return 0;
        for (Ponuka ponuka: ponuky){
            if(ponuka.getDen().equals(den)){
                for(Jedlo jedlo : ponuka.getJedla()){
                    if(jedlo.getNazov().equals(content.getNazov())){
                        return 0;
                    }
                }
                ponuka.addJedlo(content);
                return ponuka.getJedla().size();
            }
        }
        return 0;
    }
    @GET
    @Path("{den: pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}/{n}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getJedlo(@PathParam("den") String den, @PathParam("n") int n) {
        for (Ponuka ponuka : ponuky) {
            if (ponuka.getDen().equals(den)) {
                if (n < ponuka.getJedla().size()) {
                    return ponuka.getJedla().get(n);
                }
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{den: pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}/{n}")
    @Produces(MediaType.APPLICATION_XML)
    public void deleteJedlo(@PathParam("den") String den, @PathParam("n") int n) {
        for (Ponuka ponuka : ponuky) {
            if (ponuka.getDen().equals(den)) {
                if (n < ponuka.getJedla().size()) {
                    ponuka.getJedla().remove(n);
                }
            }
        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJedloZPonuky(@QueryParam("nazov") String nazov) {
        String nazvy = "";
        if (nazov == null) {
            return "NIEJE";
        }
        for (Ponuka ponuka : ponuky) {
            for (Jedlo jedlo : ponuka.getJedla()) {
                if (jedlo.getNazov().equals(nazov)) {
                    nazvy += jedlo.getNazov() + " ";
                }
            }
        }
        if (nazvy.isEmpty()) {
            return "NIEJE";
        }
        return nazvy.trim();
    }
    
}
