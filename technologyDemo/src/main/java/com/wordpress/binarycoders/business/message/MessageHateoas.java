package com.wordpress.binarycoders.business.message;

import com.wordpress.binarycoders.business.user.UserEndPoint;
import com.wordpress.binarycoders.technical.hateoas.Hateoas;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MessageHateoas implements Hateoas<MessageDto> {

	@Override
	public void addLinks(final List<MessageDto> messages) {
		if (!CollectionUtils.isEmpty(messages)) {
			messages.forEach(this::addLinks);
		}
	}

	@Override
	public void addLinks(final MessageDto message) {
		addSelfLink(message);
		addUserLink(message);
	}

	private void addSelfLink(MessageDto message) {
		message.add(linkTo(methodOn(MessageEndPoint.class).get(message.getMessageId())).withSelfRel());
	}

	private void addUserLink(final MessageDto message) {
		message.add(linkTo(methodOn(UserEndPoint.class).get(message.getUserId())).withRel("user"));
	}
}
