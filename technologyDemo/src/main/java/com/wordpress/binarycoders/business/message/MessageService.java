package com.wordpress.binarycoders.business.message;

import com.wordpress.binarycoders.business.user.User;
import com.wordpress.binarycoders.business.user.UserDao;
import com.wordpress.binarycoders.technical.domain.Transformer;
import com.wordpress.binarycoders.technical.exception.InvalidInputDataException;
import com.wordpress.binarycoders.technical.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MessageService {

	private final MessageDao messageDao;
	private final UserDao userDao;
	private final Transformer<MessageDto, Message> messageTransformer;

	@Autowired
	public MessageService(final MessageDao messageDao, final UserDao userDao) {
		this.messageDao = messageDao;
		this.userDao = userDao;
		this.messageTransformer = new MessageTransformer();
	}

	public Long create(final MessageDto messageDto) throws InvalidInputDataException, ResourceNotFoundException {
		final Message created;
		final Message message;
		final User user;

		if (Objects.isNull(messageDto) || Objects.isNull(messageDto.getUserId())) {
			throw new InvalidInputDataException();
		}

		user = userDao.findOne(messageDto.getUserId());

		if (Objects.isNull(user)) {
			throw new ResourceNotFoundException();
		}

		message = messageTransformer.toEntity(messageDto);
		message.setUser(user);

		created = messageDao.save(message);

		return created.getId();
	}

	public MessageDto read(final Long id) throws InvalidInputDataException, ResourceNotFoundException {
		final Message message;

		if (Objects.isNull(id)) {
			throw new InvalidInputDataException();
		}

		message = messageDao.findOne(id);

		if (Objects.isNull(message)) {
			throw new ResourceNotFoundException();
		}

		return messageTransformer.toDto(message);
	}

	public List<MessageDto> readAll() {
		final List<Message> messages = messageDao.findAll();

		return messages.stream()
				.map(messageTransformer::toDto)
				.collect(Collectors.toList());
	}

	public void update(final Long id, final MessageDto messageDto) throws InvalidInputDataException, ResourceNotFoundException {
		final Message toUpdate;

		if (Objects.isNull(messageDto) || Objects.isNull(id)
				|| Objects.isNull(messageDto.getId()) || !id.equals(messageDto.getId())) {
			throw new InvalidInputDataException();
		}

		toUpdate = messageDao.findOne(id);

		if (Objects.isNull(toUpdate)) {
			throw new ResourceNotFoundException();
		}

		toUpdate.setTitle(messageDto.getTitle());
		toUpdate.setContent(messageDto.getContent());

		messageDao.save(toUpdate);
	}

	public void delete(final Long id) throws InvalidInputDataException, ResourceNotFoundException {
		final Message toDelete;

		if (Objects.isNull(id)) {
			throw new InvalidInputDataException();
		}

		toDelete = messageDao.findOne(id);

		if (Objects.isNull(toDelete)) {
			throw new ResourceNotFoundException();
		}

		messageDao.delete(toDelete);
	}

	public List<MessageDto> findByUserId(final Long userId) {
		final List<Message> messages = messageDao.findByUserId(userId);

		return messages.stream().map(messageTransformer::toDto).collect(Collectors.toList());
	}
}
