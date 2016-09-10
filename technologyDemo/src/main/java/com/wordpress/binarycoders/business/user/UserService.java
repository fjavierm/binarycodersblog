package com.wordpress.binarycoders.business.user;

import com.wordpress.binarycoders.technical.domain.Transformer;
import com.wordpress.binarycoders.technical.exception.InvalidInputDataException;
import com.wordpress.binarycoders.technical.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

	private final UserDao userDao;
	private final Transformer<UserDto, User> userTransformer;

	@Autowired
	public UserService(final UserDao userDao) {
		this.userDao = userDao;
		this.userTransformer = new UserTransformer();
	}

	public Long create(final UserDto userDto) throws InvalidInputDataException {
		final User created;
		final User user;

		if (Objects.isNull(userDto)) {
			throw new InvalidInputDataException();
		}

		user = userTransformer.toEntity(userDto);

		created = userDao.save(user);

		return created.getId();
	}

	public UserDto read(final Long id) throws InvalidInputDataException, ResourceNotFoundException {
		final User user;

		if (Objects.isNull(id)) {
			throw new InvalidInputDataException();
		}

		user = userDao.findOne(id);

		if (Objects.isNull(user)) {
			throw new ResourceNotFoundException();
		}

		return userTransformer.toDto(user);
	}

	public List<UserDto> readAll() {
		final List<User> users = userDao.findAll();

		return users.stream()
				.map(userTransformer::toDto)
				.collect(Collectors.toList());
	}

	public void update(final Long id, final UserDto userDto) throws InvalidInputDataException, ResourceNotFoundException {
		final User toUpdate;

		if (Objects.isNull(userDto) || Objects.isNull(id)
				|| Objects.isNull(userDto.getId()) || !id.equals(userDto.getId())) {
			throw new InvalidInputDataException();
		}

		toUpdate = userDao.findOne(id);

		if (Objects.isNull(toUpdate)) {
			throw new ResourceNotFoundException();
		}

		toUpdate.setEmail(userDto.getEmail());
		toUpdate.setPassword(userDto.getPassword());
		toUpdate.setUsername(userDto.getUsername());

		userDao.save(toUpdate);
	}

	public void delete(final Long id) throws InvalidInputDataException, ResourceNotFoundException {
		final User toDelete;

		if (Objects.isNull(id)) {
			throw new InvalidInputDataException();
		}

		toDelete = userDao.findOne(id);

		if (Objects.isNull(toDelete)) {
			throw new ResourceNotFoundException();
		}

		userDao.delete(toDelete);
	}
}
