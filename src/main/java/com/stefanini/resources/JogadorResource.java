package com.stefanini.resources;

import com.stefanini.dto.JogadorDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.service.JogadorService;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jogador")
public class JogadorResource {

    @Inject
    JogadorService jogadorService;
//
//    @GET
//    @Produces("text/plain")
//    public String hello() {
//        return "Hello RESTEasy";
//    }

    @GET
    @Path("/{id}")
    public Response pegarPorId(@PathParam("id") Long id){
        return Response.status(Response.Status.OK).entity(jogadorService.pegarPorId(id)).build();
    }

    @GET
    @Path("/todos")
    public Response listarTodos(){
        return Response.status(Response.Status.OK).entity(jogadorService.listarTodos()).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(JogadorDTO jogadorDTO) {
//        jogadorService.salvar(jogadorDTO);
//        return Response.status(Response.Status.CREATED).build();
        return Response.ok(jogadorService.salvar(jogadorDTO)).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(Jogador jogadorDTO) {
        jogadorService.alterar(jogadorDTO);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            jogadorService.deletar(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Jogador jogadorDTO) throws Exception {

        return Response.status(Response.Status.OK).entity(jogadorService.login(jogadorDTO)).build();
    }

}
