package com.wordpress.binarycoders.business.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDao extends JpaRepository<Message, Long> {

	List<Message> findByUserId(Long userId);
}
