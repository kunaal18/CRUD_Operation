package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLAYER_INFO")
public class Player extends Audit {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playerId;

	@Column(name = "PLAYER_NAME")

	private String playerName;
	private int age;
	private String jerseyNumber;
	private String country;
	private boolean isDeleted;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PlayerStats playerStats;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
	@JsonManagedReference
	private List<Leagues> leagues;
}
