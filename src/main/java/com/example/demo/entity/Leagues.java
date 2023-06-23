package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Leagues extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leagueId;

	private String leagueName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Player player;

}
