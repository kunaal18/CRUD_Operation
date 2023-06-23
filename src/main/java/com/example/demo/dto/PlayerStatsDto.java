package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatsDto {
	
	private int odiRuns;
	private int testRuns;
	private int t20Runs;
	private String ranking;

	// private Player player;
}
