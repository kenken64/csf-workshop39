package sg.edu.nus.iss.app.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
}
