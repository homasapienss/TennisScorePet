import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchScore;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.PlayerScore;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.Points;
import edu.tennis.score.homasapienss.DTO.PlayerDTO;
import edu.tennis.score.homasapienss.services.MatchScoreService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestScore {
    MatchScoreService matchScoreService ;

    @BeforeEach
    void setUp() {
        matchScoreService = new MatchScoreService();
    }

    @Test
    void whenDeuceAndPlayer1Scores_thenGameNotOver() {
        //Arrange
        MatchDTO matchDTO = new MatchDTO(
                new PlayerDTO("Maksym"),
                new PlayerDTO("Andrey"),
                null,
                new MatchScore(new PlayerScore(Points.FORTY, 0, 0, false),
                                new PlayerScore(Points.FORTY, 0, 0, false)));


        //Act
        matchScoreService.calculate(matchDTO, 1);
        var matchScore = matchDTO.getScore();
        boolean result = (matchScore.getScorePlayer1().getGames()==0) && (matchScore.getScorePlayer2().getGames()==0) && matchScore.getScorePlayer1().isHasAdvantage();

        //assert
        assertTrue(result, "После выигрыша очка при счёте 40-40 должна быть Advantage, а не конец гейма");
        assertFalse(matchScore.getScorePlayer2().isHasAdvantage(), "Игрок 2 не должен иметь преимущество после очка игрока 1");
    }

    @Test
    void whenPlayer1ScoresAt40to0_thenWinsGame() {
        MatchDTO matchDTO = new MatchDTO(
                new PlayerDTO("Maksym"),
                new PlayerDTO("Andrey"),
                null,
                new MatchScore(new PlayerScore(Points.FORTY, 0, 0, false),
                                new PlayerScore(Points.ZERO, 0, 0, false)));


        matchScoreService.calculate(matchDTO, 1);
        var matchScore = matchDTO.getScore();
        assertEquals(1, matchScore.getScorePlayer1().getGames(),
                "Игрок 1 должен выиграть гейм при счёте 40-0");
        assertEquals(Points.ZERO, matchScore.getScorePlayer1().getPoints(),
                "После выигрыша гейма очки игрока 1 должны сброситься");
        assertEquals(Points.ZERO, matchScore.getScorePlayer2().getPoints(),
                "После выигрыша гейма очки игрока 2 должны сброситься");
    }

    @Test
    void whenScoreIs6to6_thenTieBreakBegins() {
        MatchDTO matchDTO = new MatchDTO(
                new PlayerDTO("Maksym"),
                new PlayerDTO("Andrey"),
                null,
                new MatchScore(new PlayerScore(Points.FORTY, 5, 0, false),
                                new PlayerScore(Points.ZERO, 6, 0, false)));


        matchScoreService.calculate(matchDTO, 1); //сравнял 6-6 в геймах -> должен начаться тай брейк
        var matchScore = matchDTO.getScore();
        boolean result = (matchScore.isTieBreak());

        assertTrue(result, "При счёте 6-6 должен начинаться тайбрейк");
        assertEquals(Points.ZERO, matchScore.getScorePlayer1().getPoints(),
                "При начале тай брейка должны сбрасываться очки");
        assertEquals(Points.ZERO, matchScore.getScorePlayer2().getPoints(),
                "При начале тай брейка должны сбрасываться очки");
    }
}
