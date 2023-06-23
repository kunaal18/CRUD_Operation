package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	public List<Player> findByIsDeleted(boolean b);

	public List<Player> findByIsDeletedAndPlayerNameIgnoreCaseContaining(boolean b, String playerName);
}
