package com.example;

import com.example.entities.Champion;
import com.example.repo.ChampionRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/champion")
public class ExampleResource {

    @Inject
    ChampionRepo championRepo;

    @GET
    @Path("ty")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Ty is testing";
    }

    @GET
    @Path("all")
    public List<Champion> getAll() {
        List<Champion> champions = championRepo.listAll();
        return champions;
    }

    @GET
    @Path("img/all")
    public List<String> getAllImgs() {
        return fetchChampionImages(championRepo.listAll());
    }
    @GET
    @Path("img/few")
    public List<String> getFewImgs() {
        return fetchChampionImages(championRepo.listAll().subList(0,10));
    }

    private List<String> fetchChampionImages(List<Champion> champions){
        List<String> championImages = new ArrayList<>();
//        String name = "Aatrox";
//        String baseURL = "http://ddragon.leaqueoflegends.com/cdn/img/champion/loading/" + name + "_0.jpg";
        StringBuilder baseUrlBuilder;
        for (int champion = 0; champion < champions.size(); champion++) {
            baseUrlBuilder = new StringBuilder("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/");
            baseUrlBuilder.append(champions.get(champion).getId()).append("_0.jpg");
            championImages.add(baseUrlBuilder.toString());
        }
        return championImages;
    }
}