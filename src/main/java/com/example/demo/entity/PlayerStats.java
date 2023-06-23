package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStats extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playerStatsId;

	private int odiRuns;
	private int testRuns;
	private int t20Runs;
	private String ranking;

	@OneToOne(cascade = CascadeType.ALL)
	private Player player;

}
