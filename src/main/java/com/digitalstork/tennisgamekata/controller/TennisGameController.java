package com.digitalstork.tennisgamekata.controller;

import com.digitalstork.tennisgamekata.dto.TennisGameCreateDto;
import com.digitalstork.tennisgamekata.dto.TennisGameDto;
import com.digitalstork.tennisgamekata.service.TennisGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tennis-game")
@AllArgsConstructor
public class TennisGameController {

    private final TennisGameService tennisGameService;

    @PostMapping("/new")
    public ResponseEntity<TennisGameDto> createNewGame(@RequestBody TennisGameCreateDto tennisGameCreateDto) {
        return ResponseEntity.ok(new TennisGameDto());
    }
}
