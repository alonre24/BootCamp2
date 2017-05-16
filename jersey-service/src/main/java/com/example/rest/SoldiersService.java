package com.example.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static jdk.nashorn.internal.objects.Global.print;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Path("/soldiers")
public class SoldiersService {

    private final String soldiersIndex = "soldiers";
    private final String soldierType = "soldier";
    private SoldiersResponse response;

    public SoldiersService() {
        response = new SoldiersResponse();
        response.soldiers = new ArrayList<>();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSoldiers() throws IOException {


        TransportClient client = Main.client;

        SearchResponse response = client.prepareSearch("soldiers").setTypes("soldier").execute().actionGet();
        SoldiersResponse solResponse = new SoldiersResponse();
        solResponse.soldiers = new ArrayList<>();
        SearchHits hits = response.getHits();
        ObjectMapper mapper = new ObjectMapper();
        int totalHits = (int)hits.getTotalHits();

        for (int i=0; i<totalHits; i++) {


            Soldier sol = mapper.reader().forType(Soldier.class).readValue(hits.getHits()[i].getSourceAsString());
            solResponse.soldiers.add(sol);


        }

        return Response.status(Response.Status.OK).entity(solResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getSoldiersById(@PathParam("id") String id) throws IOException {

        GetResponse response = Main.client.prepareGet("soldiers", "soldier", id).get();
        ObjectMapper mapper = new ObjectMapper();
        Soldier sol = mapper.reader().forType(Soldier.class).readValue(response.getSourceAsString());

        return Response.status(Response.Status.OK).entity(sol).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("crew/{num}")
    public Response getSoldiersByCrewNum(@PathParam("num") long num) throws IOException {


        TransportClient client = Main.client;

        SearchResponse response = client.prepareSearch("soldiers").setTypes("soldier")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("crewNum", num)).get();

        SoldiersResponse solResponse = new SoldiersResponse();
        solResponse.soldiers = new ArrayList<>();
        SearchHits hits = response.getHits();
        ObjectMapper mapper = new ObjectMapper();
        int totalHits = (int)hits.getTotalHits();

        for (int i=0; i<totalHits; i++) {


            Soldier sol = mapper.reader().forType(Soldier.class).readValue(hits.getHits()[i].getSourceAsString());
            solResponse.soldiers.add(sol);


        }

        return Response.status(Response.Status.OK).entity(solResponse).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/newSoldier")
    public Response addSoldier(String solInJsonString) throws ExecutionException, InterruptedException, IOException {

        TransportClient client = Main.client;
        ObjectMapper mapper = new ObjectMapper();
        Soldier sol = mapper.reader().forType(Soldier.class).readValue(String.valueOf(solInJsonString));



        IndexResponse response = client.prepareIndex(soldiersIndex, soldierType, sol.getPersonalId().toString())
                .setSource(jsonBuilder().startObject().field("personalId", sol.getPersonalId())
                .field("firstName", sol.getFirstName()).field("lastName", sol.getLastName())
                .field("profile", sol.getProfile()).field("personalCommanderId", sol.getPersonalCommanderId())
                .field("crewNum", sol.getCrewNum()).field("rank", sol.getRank())
                .endObject()).get();
        return Response.status(Response.Status.CREATED).entity(sol).build();
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removeSoldier/{id}")
    public Response removeSoldier(@PathParam("id") String id) throws IOException {

        TransportClient client = Main.client;

        GetResponse response = Main.client.prepareGet("soldiers", "soldier", id).get();
        ObjectMapper mapper = new ObjectMapper();
        Soldier sol = mapper.reader().forType(Soldier.class).readValue(response.getSourceAsString());

        client.prepareDelete(soldiersIndex,soldierType,id).get();


        return Response.status(Response.Status.OK).entity(sol).build();

    }

}