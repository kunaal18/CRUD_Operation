package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PlayerRepository;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.Response;
import com.example.demo.service.PlayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlayerController {
	// constructor injection (@requiredArgsConstructor + make final reference of
	// that class which u want here )
	private final PlayerService service;

	@Autowired
	private PlayerRepository dao;

	@PostMapping("/addPlayer")
	public ResponseEntity<Response> addPlayer(@RequestBody PlayerDto player) {

		return new ResponseEntity<>(new Response(false, service.addPlayer(player), "Added successfully"),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("/updatePlayer/{playerId}")
	public ResponseEntity<Response> updatePlayer(@PathVariable Integer playerId,@RequestBody PlayerDto playerDto) {
		PlayerDto updatePlayer = service.updatePlayer(playerId, playerDto);
		if (updatePlayer != null) {
			return new ResponseEntity<>(new Response(false, updatePlayer, "Updated Successfully"), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(new Response(true, null, "Not Updated "), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletePlayer/{playerId}")
	public ResponseEntity<Response> deletePlayer(@PathVariable int playerId) {

		return new ResponseEntity<>(new Response(false, service.deletePlayerById(playerId), "Deleted Successfully"),
				HttpStatus.OK);

	}

	@GetMapping("/getPlayerById/{playerId}")
	public ResponseEntity<Response> getPlayerById(@PathVariable int playerId) {

		return new ResponseEntity<>(new Response(false, service.getPlayerById(playerId), "Player Fetched Successfully"),
				HttpStatus.OK);
	}

	@GetMapping("getAllPlayers")
	public ResponseEntity<Response> getAllPlayer() {

		return new ResponseEntity<>(new Response(false, service.getAllPlayers(), "List fetched Successfully "),
				HttpStatus.OK);
	}

	@GetMapping("getAllPlayersByName/{playerName}")
	public ResponseEntity<Response> getAllPlayerByName(@PathVariable String playerName) {

		return new ResponseEntity<>(
				new Response(false, service.getPlayerByName(playerName), "List fetched Successfully "), HttpStatus.OK);
	}

//	@GetMapping("getPlayerBySameCountry")
//	public ResponseEntity<?> getPlayerBySameCountry() {
//		Set<Entry<String, Set<Player>>> entrySet = dao.findAll().stream()
//				.collect(Collectors.groupingBy(i -> i.getCountry(), Collectors.toSet())).entrySet();
//
//		return ResponseEntity<Response>
//	}
}
