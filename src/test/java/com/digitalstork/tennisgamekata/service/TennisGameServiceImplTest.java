package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.model.TennisGame;
import com.digitalstork.tennisgamekata.repository.TennisGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TennisGameServiceImplTest {

    @InjectMocks
    private TennisGameServiceImpl tennisGameService;

    @Mock
    private TennisGameRepository tennisGameRepository;

    @Test
    void should_create_new_tennis_game_properly() {
        // Given
        TennisGameCreateDto tennisGameCreateDto = new TennisGameCreateDto();
        tennisGameCreateDto.setPlayerOne("Player 1");
        tennisGameCreateDto.setPlayerTwo("Player 2");

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(1L);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");

        // When
        when(tennisGameRepository.save(any(TennisGame.class)))
                .thenReturn(tennisGame);
        TennisGameDto tennisGameDto = tennisGameService.createGame(tennisGameCreateDto);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertNotNull(tennisGameDto.getId());
        assertFalse(tennisGame.isGameEnded());
        assertEquals(tennisGame.getPlayerOne(), tennisGameDto.getPlayerOne());
        assertEquals(tennisGame.getPlayerTwo(), tennisGameDto.getPlayerTwo());
    }

    @Test
    void should_score_and_return_updated_game_details() {
        // Given
        Long gameId = 1L;

        TennisGame tennisGame = new TennisGame();
        tennisGame.setId(gameId);
        tennisGame.setPlayerOne("Player 1");
        tennisGame.setPlayerTwo("Player 2");
        tennisGame.setPlayerOneScore(1);
        tennisGame.setPlayerTwoScore(2);

        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setScorer("Player 1");

        TennisGame updatedTennisGame = new TennisGame();
        updatedTennisGame.setId(gameId);
        updatedTennisGame.setPlayerOne(tennisGame.getPlayerOne());
        updatedTennisGame.setPlayerTwo(tennisGame.getPlayerTwo());
        updatedTennisGame.setPlayerOneScore(2);
        updatedTennisGame.setPlayerTwoScore(2);

        // When
        when(tennisGameRepository.findById(gameId))
                .thenReturn(Optional.of(tennisGame));
        when(tennisGameRepository.save(any(TennisGame.class)))
                .thenReturn(updatedTennisGame);
        TennisGameDto tennisGameDto = tennisGameService.score(gameId, scoreDto);

        // Test Assertions
        assertNotNull(tennisGameDto);
        assertEquals("30", tennisGameDto.getPlayerOneScore());
        assertEquals("30", tennisGameDto.getPlayerTwoScore());
        assertFalse(tennisGame.isGameEnded());
    }

}