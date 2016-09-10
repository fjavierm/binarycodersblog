package com.wordpress.binarycoders.business.user;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;

import com.wordpress.binarycoders.business.message.Message;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractPersistable<Long> {

	public final static String PASSWORD_LITERAL = "********";

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created", nullable = false)
	private LocalDateTime created;

	@Column(name = "updated", nullable = false)
	private LocalDateTime updated;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Message> messages;

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
				.append("username", this.getUsername())
				.append("password", PASSWORD_LITERAL)
				.append("created", this.getCreated())
				.append("updated", this.getUpdated())
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(this.getEmail())
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final User user;

		if (!(obj instanceof User)) {
			return Boolean.FALSE;
		}

		user = (User) obj;

		return new EqualsBuilder()
				.append(this.getEmail(), user.getEmail())
				.isEquals();
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(final String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(final String password) {
		this.password = password;
		return this;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public User setMessages(final Set<Message> messages) {
		this.messages = messages;
		return this;
	}
}
