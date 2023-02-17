package sg.edu.nus.iss.app.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.server.model.MarvelCharacter;

@Service
public class CharacterService {
    @Autowired
    private MarvelApiService marvelApiSvc;
 
    public Optional<List<MarvelCharacter>> getCharacters(String characterName,
            Integer limit , Integer offset){
        Optional<List<MarvelCharacter>> result= marvelApiSvc
                .getCharacters(characterName, limit, offset);
        return result;
    }
}
