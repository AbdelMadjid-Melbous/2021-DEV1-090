package com.digitalstork.tennisgamekata.service;

import com.digitalstork.tennisgamekata.dto.ScoreDto;
import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.mapper.TennisGameCreateDtoMapper;
import com.digitalstork.tennisgamekata.mapper.TennisGameMapper;
import com.digitalstork.tennisgamekata.model.TennisGame;
import com.digitalstork.tennisgamekata.repository.TennisGameRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TennisGameServiceImpl implements TennisGameService {

    private static final Logger log = LoggerFactory.getLogger(TennisGameServiceImpl.class);

    private final TennisGameRepository tennisGameRepository;
    private final TennisGameCreateDtoMapper tennisGameCreateDtoMapper = new TennisGameCreateDtoMapper();
    private final TennisGameMapper tennisGameMapper = new TennisGameMapper();

    @Override
    public TennisGameDto createGame(TennisGameCreateDto tennisGameCreateDto) {
        TennisGame tennisGame = tennisGameCreateDtoMapper.apply(tennisGameCreateDto);
        TennisGame savedTennisGame = tennisGameRepository.save(tennisGame);

        log.info("New Tennis Game between {} and {}", savedTennisGame.getPlayerOne(), savedTennisGame.getPlayerTwo());

        return tennisGameMapper.apply(savedTennisGame);
    }

    @Override
    public TennisGameDto score(Long gameId, ScoreDto scoreDto) {
        return null;
    }
}
