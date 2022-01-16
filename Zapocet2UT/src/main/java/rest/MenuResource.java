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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author user
 */

@Path("menu")
@Singleton
public class MenuResource {

    @Context
    private UriInfo context;
    private List<Ponuka> ponuky;

    /**
     * Creates a new instance of MenuResource
     */
    public MenuResource() {
        ponuky = new ArrayList<>();
        Ponuka Pondelok = new Ponuka("pondelok");
        Pondelok.addJedlo(new Jedlo(3.5,"gulas"));
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
    @Path("{den: pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getPocetJedla(@PathParam("den") String den ) {
        for (Ponuka ponuka: ponuky){
            if(ponuka.getDen().equals(den)){
                return ponuka.getJedlo().size();
            }
        }
        return 0;
    }
    
    @GET
    @Path("{den: pondelok|utorok|streda|stvrtok|piatok|sobota|nedela}")
    @Produces(MediaType.APPLICATION_XML)
    public Ponuka getMenu(@PathParam("den") String den) {
        for (Ponuka ponuka : ponuky) {
            if (ponuka.getDen().equals(den)) {
                return ponuka;
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
                for(Jedlo jedlo : ponuka.getJedlo()){
                    if(jedlo.getNazov().equals(content.getNazov())){
                        return 0;
                    }
                }
                ponuka.addJedlo(content);
                return ponuka.getJedlo().size();
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
                if (n <= ponuka.getJedlo().size()) {
                    return ponuka.getJedlo().get(n-1);
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
                if (n < ponuka.getJedlo().size()) {
                    ponuka.getJedlo().remove(n-1);
                }
            }
        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJedloZPonuky(@QueryParam("nazov") String nazov) {
        String nazvy = "";
        if (nazov == null) {
            return "NEMAME";
        }
        for (Ponuka ponuka : ponuky) {
            for (Jedlo jedlo : ponuka.getJedlo()) {
                if (jedlo.getNazov().equals(nazov)) {
                    nazvy += ponuka.getDen() + " ";
                }
            }
        }
      
        if (nazvy.isEmpty()) {
            return "NEMAME";
        }
        return nazvy.trim();
    }
    
}
