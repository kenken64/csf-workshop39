package sg.edu.nus.iss.app.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.JsonReader;

public class MarvelCharDetails extends MarvelCharacter{
    private String path;
    private String extension;

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }

    public static MarvelCharDetails createJson(JsonObject o){
        MarvelCharDetails cd = new MarvelCharDetails();
        cd.path = o.getString("path");
        cd.extension = o.getString("extension");
        return cd;
    }


    public static MarvelCharDetails createCharDetails(String json) throws IOException{
        MarvelCharDetails md = new MarvelCharDetails();
        List<MarvelCharDetails> chars = new LinkedList<>();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            JsonObject oo = o.getJsonObject("data");
            chars = oo.getJsonArray("results").stream()
                .map(v-> (JsonObject)v)
                .map(v-> MarvelCharDetails.createJson(v))
                .toList(); 
        }
        return (MarvelCharDetails)chars.get(0);
    }

}
