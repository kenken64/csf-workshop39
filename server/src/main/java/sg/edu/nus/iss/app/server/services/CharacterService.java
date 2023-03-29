package sg.edu.nus.iss.app.server.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.server.model.Comment;
import sg.edu.nus.iss.app.server.model.MarvelCharacter;
import sg.edu.nus.iss.app.server.repository.CharacterRepository;

@Service
public class CharacterService {
    @Autowired
    private MarvelApiService marvelApiSvc;

    @Autowired
    private CharacterRepository charRepo;
 
    
    public Optional<List<MarvelCharacter>> getCharacters(String characterName,
            Integer limit , Integer offset){
        return marvelApiSvc.getCharacters(characterName, limit, offset);
    }

    public MarvelCharacter getCharacterDetails(String characterId) 
            throws IOException{
        Optional<MarvelCharacter> c  = marvelApiSvc.getCharacterDetails(characterId);
        MarvelCharacter cc = c.get();
        cc.setComments(this.getAllComments(characterId));
        return cc;
    }

    public Comment insertComment(Comment r){
        return charRepo.insertComment(r);
    }

    public List<Comment> getAllComments(String charId){
        return charRepo.getAllComments(charId);
    }
}
