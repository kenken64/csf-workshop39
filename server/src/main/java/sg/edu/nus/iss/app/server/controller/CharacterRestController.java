package sg.edu.nus.iss.app.server.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.app.server.services.CharacterService;
import sg.edu.nus.iss.app.server.model.MarvelCharacter;

@RestController
@RequestMapping(path="/api/characters", consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CharacterRestController {
    private Logger logger = LoggerFactory.getLogger(CharacterRestController.class);
    
    @Autowired
    private CharacterService charSvc;
    
    @GetMapping
    public ResponseEntity<String> getCharacters(
        @RequestParam(required=true) String characterName,
        @RequestParam(required=true) Integer limit,
        @RequestParam(required=true) Integer offset) {

        JsonArray result = null;
        logger.info("limit : " + limit);
        logger.info("offset  : " + offset);
        Optional<List<MarvelCharacter>> or =charSvc.getCharacters(characterName, limit, offset);
        List<MarvelCharacter> results = or.get(); 
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (MarvelCharacter mc : results)
            arrBuilder.add(mc.toJSON());
        result = arrBuilder.build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());
    }

    @GetMapping(path="/{charId}")
    public ResponseEntity<String> getCharacterDetails(
        @RequestParam(required=true) String charId) {
        logger.info("charId : " + charId);
    
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(null);
    }

    @PostMapping
    public ResponseEntity<String> saveCharacterComment(
        @RequestBody String comment) {
        
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(null);
    }


}
