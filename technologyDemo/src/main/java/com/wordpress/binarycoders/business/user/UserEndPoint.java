package com.wordpress.binarycoders.business.user;

import com.wordpress.binarycoders.business.message.Message;
import com.wordpress.binarycoders.business.message.MessageDto;
import com.wordpress.binarycoders.business.message.MessageHateoas;
import com.wordpress.binarycoders.business.message.MessageService;
import com.wordpress.binarycoders.technical.endpoint.ApiUrl;
import com.wordpress.binarycoders.technical.endpoint.ResponseEntityFactory;
import com.wordpress.binarycoders.technical.exception.InvalidInputDataException;
import com.wordpress.binarycoders.technical.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.IsNewAwareAuditingHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = UserEndPoint.ROOT)
public class UserEndPoint {

	public static final String ROOT = ApiUrl.ROOT + "/user";
	public static final String GET = "/{id}";
	public static final String DELETE = "/{id}";
	public static final String UPDATE = "/{id}";
	public static final String GET_MESSAGES = "/{id}/messages";

	private final UserService userService;
	private final MessageService messageService;
	private final UserHateoas userHateoas;
	private final MessageHateoas messageHateoas;

	@Autowired
	public UserEndPoint(final UserService userService, final MessageService messageService) {
		this.userService = userService;
		this.messageService = messageService;
		this.userHateoas = new UserHateoas();
		this.messageHateoas = new MessageHateoas();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		final List<UserDto> users = this.userService.readAll();

		this.userHateoas.addLinks(users);

		return new ResponseEntityFactory<List<UserDto>>().buildOk(users);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody @Valid final UserDto userDto) {
		final Long userId;

		try {
			userId = this.userService.create(userDto);

			return new ResponseEntityFactory<Long>().buildCreate(userId);
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		}
	}

	@RequestMapping(value = UserEndPoint.GET, method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable final Long id) {
		final UserDto user;

		try {
			user = this.userService.read(id);

			this.userHateoas.addLinks(user);

			return new ResponseEntityFactory<UserDto>().buildOk(user);
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		}
	}

	@RequestMapping(value = UserEndPoint.UPDATE, method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable final Long id, @RequestBody @Valid final UserDto userDto) {
		try {
			this.userService.update(id, userDto);

			return new ResponseEntityFactory<Void>().buildOk();
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		}
	}

	@RequestMapping(value = UserEndPoint.DELETE, method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable final Long id) {
		try {
			this.userService.delete(id);

			return new ResponseEntityFactory<Void>().buildOk();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		}
	}

	@RequestMapping(value = UserEndPoint.GET_MESSAGES, method = RequestMethod.GET)
	public ResponseEntity getMessages(@PathVariable final Long id) {
		final List<MessageDto> messages;

		messages = this.messageService.findByUserId(id);
		this.messageHateoas.addLinks(messages);

		return new ResponseEntityFactory<List<MessageDto>>().buildOk(messages);
	}

}
