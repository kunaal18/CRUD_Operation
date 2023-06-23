package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

	private String playerName;
	private int age;
	private String jerseyNumber;
	private String country;
	private boolean isDeleted;
	
	private PlayerStatsDto playerStats;

	private List<LeaguesDto> leagues;
}
