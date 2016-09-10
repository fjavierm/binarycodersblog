package com.wordpress.binarycoders.business.user;

import com.wordpress.binarycoders.business.message.Message;
import com.wordpress.binarycoders.business.message.MessageDto;
import com.wordpress.binarycoders.business.message.MessageTransformer;
import com.wordpress.binarycoders.technical.domain.Transformer;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserTransformer implements Transformer<UserDto, User> {

	private final Transformer<MessageDto, Message> messageTransformer;

	public UserTransformer() {
		this.messageTransformer = new MessageTransformer();
	}

	@Override
	public UserDto toDto(final User entity) {
		final UserDto dto = new UserDto();

		if (Objects.isNull(entity)) {
			return dto;
		}

		dto.setUserId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setPassword(User.PASSWORD_LITERAL);
		if (Objects.nonNull(entity.getMessages())) {
			dto.setMessages(entity.getMessages()
					.stream()
					.map(messageTransformer::toDto)
					.collect(Collectors.toList()));
		}

		return dto;
	}

	@Override
	public User toEntity(final UserDto dto) {
		final User entity = new User();

		if (Objects.isNull(dto)) {
			return entity;
		}

		entity.setUsername(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword()); // Todo: apply password security
		if (Objects.nonNull(dto.getMessages())) {
			entity.setMessages(dto.getMessages()
					.stream()
					.map(messageTransformer::toEntity)
					.collect(Collectors.toSet()));
		}

		return entity;
	}

}
