package sg.edu.nus.iss.app.server.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class MarvelCharacter implements Serializable{
    private Integer id;
    private String name;
    private List<String> comments;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComments() {
        return comments;
    }
    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public static MarvelCharacter createJson(JsonObject o){
        MarvelCharacter c = new MarvelCharacter();
        c.id = o.getJsonNumber("id").intValue();
        c.name = o.getString("name");
        return c;
    }
    

    public static List<MarvelCharacter> create(String json) throws IOException {
        //Character c = new Character();
        List<MarvelCharacter> chars = new LinkedList<>();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            JsonObject oo = o.getJsonObject("data");
            chars = oo.getJsonArray("results").stream()
                .map(v-> (JsonObject)v)
                .map(v-> MarvelCharacter.createJson(v))
                .toList(); 
        }
        return chars;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("name", getName())
                .build();
    }
    
}
