package com.wordpress.binarycoders.business.message;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDto extends ResourceSupport {

	private Long messageId;

	@Size(min = 1, max = 30, message = "The message title must have between 1 and 30 characters.")
	private String title;

	@Size(min = 1, max = 500, message = "The message content must have between 1 and 500 characters.")
	private String content;

	@NotNull
	private Long userId;

	public Long getMessageId() {
		return messageId;
	}

	public MessageDto setMessageId(final Long messageId) {
		this.messageId = messageId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public MessageDto setTitle(final String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public MessageDto setContent(final String content) {
		this.content = content;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public MessageDto setUserId(final Long userId) {
		this.userId = userId;
		return this;
	}
}
