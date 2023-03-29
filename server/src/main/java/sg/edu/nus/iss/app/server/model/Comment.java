package sg.edu.nus.iss.app.server.model;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String charId;
    private String comment;

    public String getCharId() {
        return charId;
    }
    public void setCharId(String id) {
        this.charId = id;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setCharId(d.getObjectId("charId").toString());
        c.setComment(d.getString("comment"));
        return c;
    }

    public JsonObject toJSON() {

        return Json.createObjectBuilder()
                .add("charId", getCharId())
                .add("comment", getComment())
                .build();
    }
    
}
