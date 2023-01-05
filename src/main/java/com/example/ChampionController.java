package com.example;

import com.example.entities.Champion;
import com.example.entities.ChampionDTO;
import com.example.repo.ChampionRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/champion")
public class ChampionController {

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

    @POST
    @Path("next")
    @Consumes(MediaType.APPLICATION_JSON)
    public ChampionDTO getNext(Champion champion) {//create new Champion class with champion img?
        if (champion.getId().isBlank() || champion.getId().equals("Zyra")) {
            Champion aatrox = championRepo.find("id", "Aatrox").firstResult();
            return new ChampionDTO(aatrox, fetchChampionImage(aatrox));
        }
//        Champion currentChampion = championRepo.findById(champion.getKey());// TODO Tommy check does this even work with key???
//        List<Champion> champions = championRepo.listAll();
//        List<Champion> foundChampions = championRepo.list("id", champion.getId());
        List<Champion> allChampions = championRepo.listAll();
        Champion nextChamp = allChampions
            .stream()
            .filter(champ -> champ.getId().equals(champion.getId()))
            .findFirst()
            .map(searchedChamp ->
                allChampions.get(
                    allChampions.indexOf(searchedChamp) + 1
                )
            )
            .get();// error?
        ChampionDTO result = new ChampionDTO(nextChamp, fetchChampionImage(nextChamp));
        return result;
        //TODO tommy add img to champion + before that refactor logic of fetchChampionImgs to fetchChampionImg for only one Img
    }

    @GET
    @Path("img/all")
    public List<String> getAllImgs() {
        return fetchChampionImages(championRepo.listAll());
    }

    @GET
    @Path("img/few")
    public List<String> getFewImgs() {
        return fetchChampionImages(championRepo.listAll().subList(0, 10));
    }

    private List<String> fetchChampionImages(List<Champion> champions) {
        List<String> championImages = new ArrayList<>();
//        String name = "Aatrox";
//        String baseURL = "http://ddragon.leaqueoflegends.com/cdn/img/champion/loading/" + name + "_0.jpg";
        for (int champion = 0; champion < champions.size(); champion++) {
            championImages.add(fetchChampionImage(champions.get(champion)));
        }
        return championImages;
    }

    private String fetchChampionImage(Champion champion) {
        StringBuilder baseUrlBuilder;
        baseUrlBuilder = new StringBuilder("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/");
        baseUrlBuilder.append(champion.getId()).append("_0.jpg");
        return baseUrlBuilder.toString();
    }
}
