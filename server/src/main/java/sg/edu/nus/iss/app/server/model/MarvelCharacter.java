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
    private String desc;
    private String photo;
    private List<Comment> comments;
    
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public static MarvelCharacter createJson(JsonObject o){
        MarvelCharacter c = new MarvelCharacter();
        JsonObject t = o.getJsonObject("thumbnail");
        String path = t.getString("path");
        String ext = t.getString("extension");
        
        c.id = o.getJsonNumber("id").intValue();
        c.name = o.getString("name");
        c.desc = o.getString("description");
        c.photo = path + '.' + ext; 
        return c;
    }
    

    public static List<MarvelCharacter> create(String json) throws IOException {
        List<MarvelCharacter> chars = new LinkedList<>();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            JsonObject oo = o.getJsonObject("data");
            if(oo.getJsonArray("results") != null)
                chars = oo.getJsonArray("results").stream()
                    .map(v-> (JsonObject)v)
                    .map(v-> MarvelCharacter.createJson(v))
                    .toList(); 
        }
        return chars;
    }

    public static MarvelCharacter createForCache(String json) throws IOException {
        MarvelCharacter c = new MarvelCharacter();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            c.setId(o.getJsonNumber("id").intValue());
            c.setName(o.getString("name"));
            c.setDesc(o.getString("description"));
            c.setPhoto(o.getString("photo"));
        }
        return c;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("name", getName())
                .add("description", getDesc())
                .add("photo", getPhoto())
                .build();
    }
    
}
