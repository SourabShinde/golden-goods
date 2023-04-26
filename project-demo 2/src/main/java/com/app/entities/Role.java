package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor


public class Role extends BaseEntity{
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", length = 20)
	private UserRole roleName;

	@Override
	public String toString() {
		return "Role ID "+getId()+" [roleName=" + roleName + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roleName != other.roleName)
			return false;
		return true;
	}


	public Role(UserRole roleName) {
		super();
		this.roleName = roleName;
	}
}
