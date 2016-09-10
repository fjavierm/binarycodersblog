package com.wordpress.binarycoders.business.user;

import com.wordpress.binarycoders.technical.hateoas.Hateoas;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserHateoas implements Hateoas<UserDto> {

	@Override
	public void addLinks(final List<UserDto> users) {
		if (!CollectionUtils.isEmpty(users)) {
			users.forEach(this::addLinks);
		}
	}

	@Override
	public void addLinks(final UserDto user) {
		addSelfLink(user);
		addMessagesLink(user);
	}

	private void addSelfLink(UserDto user) {
		user.add(linkTo(methodOn(UserEndPoint.class).get(user.getUserId())).withSelfRel());
	}

	private void addMessagesLink(final UserDto user) {
		user.add(linkTo(methodOn(UserEndPoint.class).get(user.getUserId())).withRel("messages"));
	}
}
