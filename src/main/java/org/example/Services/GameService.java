package org.example.Services;
import org.example.Game;
import org.example.Games.A11;
import org.example.Games.FindNumber;
import org.example.Games.WordGuessingGame;
import org.example.Model.Text;
import org.example.Repositories.A11Repository;
import org.example.Repositories.GameRepositories;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    private final GameRepositories repositories;
    public String getText(long id){
        Text text = repositories.findById(id).orElse(null);
        return text != null ? text.getText() : null;
    }
    private final Map<Integer, Game> gameMap = new HashMap<>();

    public GameService(GameRepositories repositories, A11Repository a11Repository) {
        this.repositories = repositories;
        gameMap.put(1, new A11());
        gameMap.put(2, new WordGuessingGame());
        gameMap.put(3, new FindNumber());
    }

    public Game getGameById(int gameId) {
        return gameMap.get(gameId);
    }

    public Map<Integer, Game> getAllGames() {
        return gameMap;
    }
}
