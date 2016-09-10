package com.wordpress.binarycoders.business.message;

import com.wordpress.binarycoders.technical.endpoint.ApiUrl;
import com.wordpress.binarycoders.technical.endpoint.ResponseEntityFactory;
import com.wordpress.binarycoders.technical.exception.InvalidInputDataException;
import com.wordpress.binarycoders.technical.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = MessageEndPoint.ROOT)
public class MessageEndPoint {

	public static final String ROOT = ApiUrl.ROOT + "/message";
	public static final String GET = "/{id}";
	public static final String DELETE = "/{id}";
	public static final String UPDATE = "/{id}";

	private final MessageService messageService;
	private final MessageHateoas messageHateoas;

	@Autowired
	public MessageEndPoint(final MessageService messageService) {
		this.messageService = messageService;
		this.messageHateoas = new MessageHateoas();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		final List<MessageDto> messages = messageService.readAll();

		this.messageHateoas.addLinks(messages);

		return new ResponseEntityFactory<List<MessageDto>>().buildOk(messages);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody @Valid final MessageDto messageDto) {
		final Long messageId;

		try {
			messageId = this.messageService.create(messageDto);

			return new ResponseEntityFactory<Long>().buildCreate(messageId);
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		}
	}

	@RequestMapping(value = MessageEndPoint.GET, method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable final Long id) {
		final MessageDto message;

		try {
			message = this.messageService.read(id);

			this.messageHateoas.addLinks(message);

			return new ResponseEntityFactory<MessageDto>().buildOk(message);
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		}
	}

	@RequestMapping(value = MessageEndPoint.UPDATE, method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable final Long id, @RequestBody @Valid final MessageDto messageDto) {
		try {
			this.messageService.update(id, messageDto);

			return new ResponseEntityFactory<Void>().buildOk();
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		}
	}

	@RequestMapping(value = MessageEndPoint.DELETE, method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable final Long id) {
		try {
			this.messageService.delete(id);

			return new ResponseEntityFactory<Void>().buildOk();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntityFactory<String>().buildNotFound(e.getMessage());
		} catch (InvalidInputDataException e) {
			return new ResponseEntityFactory<String>().buildBadRequest(e.getMessage());
		}
	}
}
