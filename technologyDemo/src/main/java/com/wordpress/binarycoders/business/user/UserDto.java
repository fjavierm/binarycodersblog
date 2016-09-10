package com.wordpress.binarycoders.business.user;

import com.wordpress.binarycoders.business.message.MessageDto;
import com.wordpress.binarycoders.technical.validation.annotation.Email;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Size;
import java.util.List;

public class UserDto extends ResourceSupport {

	private Long userId;

	@Size(min = 4, max = 50, message = "The user name must have between 4 and 50 characters.")
	private String username;

	@Email
	private String email;

	private String password;

	private List<MessageDto> messages;

	public Long getUserId() {
		return userId;
	}

	public UserDto setUserId(final Long userId) {
		this.userId = userId;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserDto setUsername(final String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserDto setEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserDto setPassword(final String password) {
		this.password = password;
		return this;
	}

	public List<MessageDto> getMessages() {
		return messages;
	}

	public UserDto setMessages(final List<MessageDto> messages) {
		this.messages = messages;
		return this;
	}
}
