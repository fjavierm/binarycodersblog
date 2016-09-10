package com.wordpress.binarycoders.business.message;

import com.wordpress.binarycoders.technical.domain.Transformer;

import java.util.Objects;

public class MessageTransformer implements Transformer<MessageDto, Message> {

	@Override
	public MessageDto toDto(final Message entity) {
		final MessageDto dto = new MessageDto();

		if (Objects.isNull(entity)) {
			return dto;
		}

		dto.setMessageId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());

		if (Objects.nonNull(entity.getUser())) {
			dto.setUserId(entity.getUser().getId());
		}

		return dto;
	}

	@Override
	public Message toEntity(final MessageDto dto) {
		final Message entity = new Message();

		if (Objects.isNull(dto)) {
			return entity;
		}

		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());

		return entity;
	}
}
