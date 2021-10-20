package com.kingsley.authproviderdemo.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class IMUserAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "auth_user_id")
	private Integer auth_user_id;
	
	@Column(name = "name")
	private String authority;

	@ManyToOne(targetEntity = IMUser.class)
	@JoinColumn(name = "user_id")
	private IMUser imUser;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuth_user_id() {
		return auth_user_id;
	}
	public void setAuth_user_id(Integer auth_user_id) {
		this.auth_user_id = auth_user_id;
	}
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public IMUser getImUser() {
		return imUser;
	}
	public void setImUser(IMUser imUser) {
		this.imUser = imUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imUser == null) ? 0 : imUser.hashCode());
		result = prime * result + ((auth_user_id == null) ? 0 : auth_user_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IMUserAuthority other = (IMUserAuthority) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imUser == null) {
			if (other.imUser != null)
				return false;
		} else if (!imUser.equals(other.imUser))
			return false;
		if (auth_user_id == null) {
			if (other.auth_user_id != null)
				return false;
		} else if (!auth_user_id.equals(other.auth_user_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IMUserAuthority [id=" + id + ", userid=" + auth_user_id + ", authority=" + authority + ", imUser=" + imUser
				+ "]";
	}
	
	
}
