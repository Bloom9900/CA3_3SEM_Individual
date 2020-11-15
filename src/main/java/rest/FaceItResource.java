package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.IndividualDTOs.CombinedDTO;
import dto.IndividualDTOs.FaceItDTO;
import facades.UserFacade;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.HttpUtils;
import utils.Keys;

@Path("faceit")
public class FaceItResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final UserFacade facade =  UserFacade.getUserFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static String URL = "https://open.faceit.com/data/v4/";
    
    @Path("games")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public static String getGames() throws IOException {
        Keys.createKeys();
        String games = HttpUtils.fetchData(URL+"games?offset=0&limit=20");
        FaceItDTO faceitDTO = gson.fromJson(games, FaceItDTO.class);
        return gson.toJson(faceitDTO);
    }
    
    @Path("csgo")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public static String getCsgo() throws IOException {
        Keys.createKeys();
        String csgo = HttpUtils.fetchData(URL+"games/csgo");
        FaceItDTO faceitDTO = gson.fromJson(csgo, FaceItDTO.class);
        return gson.toJson(faceitDTO);
    }
    
    
    //Bemærk at der IKKE kan benyttes parrallel metode her da man skal benytte brugerens nickname for at kunne lave nummer 2 api kald!
    @Path("csgo/player")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"user", "admin"})
    public static String getMatches(String nickname) throws IOException {
        Keys.createKeys();
        FaceItDTO nicknameDTO = gson.fromJson(nickname, FaceItDTO.class);
        String player = HttpUtils.fetchData(URL+"players?nickname="+nicknameDTO.getNickname()+"&game=csgo");
        FaceItDTO faceitDTO = gson.fromJson(player, FaceItDTO.class);
        
        String matches = HttpUtils.fetchData(URL+"players/"+faceitDTO.getPlayer_id()+"/history?game=csgo&offset=0&limit=1");
        FaceItDTO matchDTO = gson.fromJson(matches, FaceItDTO.class);
        return gson.toJson(new CombinedDTO(faceitDTO, matchDTO));
    }
}
