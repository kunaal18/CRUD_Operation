package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlayerRepository;
import com.example.demo.dto.LeaguesDto;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.PlayerStatsDto;
import com.example.demo.entity.Leagues;
import com.example.demo.entity.Player;
import com.example.demo.entity.PlayerStats;
import com.example.demo.exception.PlayerNotFoundException;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerRepository repository;

	@Override
	public PlayerDto addPlayer(PlayerDto playerDto) {
		Player player = new Player();
		BeanUtils.copyProperties(playerDto, player);

		PlayerStatsDto playerStatsDto = playerDto.getPlayerStats();
		PlayerStats playerStats = new PlayerStats();
		BeanUtils.copyProperties(playerStatsDto, playerStats);
		player.setPlayerStats(playerStats);
		playerStats.setPlayer(player);

		List<LeaguesDto> listOfLeaguesDto = playerDto.getLeagues();
		List<Leagues> listOfLeagues = new ArrayList<>();
		listOfLeaguesDto.forEach(i -> {
			Leagues leagues = new Leagues();
			BeanUtils.copyProperties(i, leagues);
			listOfLeagues.add(leagues);

			player.setLeagues(listOfLeagues);
			leagues.setPlayer(player);
		});

		Player save = repository.save(player);
		BeanUtils.copyProperties(save, playerDto);

		return playerDto;

	}

	@Override
	public PlayerDto updatePlayer(Integer playerId, PlayerDto playerDto) {
		Player player = repository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Player is not present with this Id:" + playerId));
		BeanUtils.copyProperties(playerDto, player);

		PlayerStatsDto playerStatsDto = playerDto.getPlayerStats();
		PlayerStats playerStats = player.getPlayerStats();
		BeanUtils.copyProperties(playerStatsDto, playerStats);
		player.setPlayerStats(playerStats);

		List<LeaguesDto> listOfLeagueDto = playerDto.getLeagues();
		List<Leagues> listOfLeague = player.getLeagues();
		listOfLeagueDto.forEach(i -> {
			Leagues leagues = new Leagues();
			BeanUtils.copyProperties(i, leagues);
			listOfLeague.add(leagues);
			player.setLeagues(listOfLeague);
		});
		Player save = repository.save(player);

		BeanUtils.copyProperties(save, playerDto);
		return playerDto;
	}

	@Override
	public PlayerDto getPlayerById(int playerId) {

		Player player = repository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Player is Not present with this Id"));

		PlayerDto playerDto = new PlayerDto();

		BeanUtils.copyProperties(player, playerDto);

		return playerDto;
	}

	@Override
	public boolean deletePlayerById(int playerId) {
		Player player = repository.findById(playerId)
				.orElseThrow(() -> new PlayerNotFoundException("Player is Not present with this Id"));
		player.setDeleted(true);
		Player save = repository.save(player);
		return save.isDeleted();
	}

	@Override
	public List<PlayerDto> getAllPlayers() {

		List<Player> findAll = repository.findByIsDeleted(false);

		List<PlayerDto> listOfPlayerDto = new ArrayList<>();

		findAll.stream().forEach(i -> {
			PlayerDto player = new PlayerDto();
			BeanUtils.copyProperties(i, player);
			listOfPlayerDto.add(player);

			PlayerStats playerStats = i.getPlayerStats();
			PlayerStatsDto statsDto = new PlayerStatsDto();
			BeanUtils.copyProperties(playerStats, statsDto);
			player.setPlayerStats(statsDto);

			List<Leagues> listOfLeague = i.getLeagues();
			List<LeaguesDto> ListOfLeaguesDto = new ArrayList<>();
			listOfLeague.forEach(j -> {
				LeaguesDto dto = new LeaguesDto();
				BeanUtils.copyProperties(j, dto);
				ListOfLeaguesDto.add(dto);

			});
			player.setLeagues(ListOfLeaguesDto);

		});
		return listOfPlayerDto;

	}

	@Override
	public List<Player> getPlayerByName(String playerName) {
		List<Player> player = repository.findByIsDeletedAndPlayerNameIgnoreCaseContaining(false, playerName);

		return player;

	}

}
