package com.wordpress.binarycoders.business.message;

import com.wordpress.binarycoders.business.user.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@AttributeOverride(name = "id", column = @Column(name = "message_id"))
public class Message extends AbstractPersistable<Long> {

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "created", nullable = false)
	private LocalDateTime created;

	@Column(name = "updated", nullable = false)
	private LocalDateTime updated;

	@PrePersist
	public void onCreate() {
		this.created = this.updated = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdate() {
		this.updated = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", this.getId())
				.append("title", this.getTitle())
				.append("content", getContent())
				.append("created", this.getCreated())
				.append("updated", this.getUpdated())
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(this.getId())
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final Message message;

		if (!(obj instanceof Message)) {
			return Boolean.FALSE;
		}

		message = (Message) obj;

		return new EqualsBuilder()
				.append(this.getId(), message.getId())
				.isEquals();
	}

	public String getTitle() {
		return title;
	}

	public Message setTitle(final String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Message setContent(final String content) {
		this.content = content;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Message setUser(final User user) {
		this.user = user;
		return this;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}
}
