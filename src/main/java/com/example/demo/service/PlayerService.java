package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;

public interface PlayerService {
	
	public PlayerDto addPlayer(PlayerDto player);

	public PlayerDto updatePlayer(Integer playerId,PlayerDto player);

	public PlayerDto getPlayerById(int playerId);

	public boolean deletePlayerById(int playerId);

	public List<PlayerDto> getAllPlayers();
	
	public List<Player> getPlayerByName(String playerName);
}
