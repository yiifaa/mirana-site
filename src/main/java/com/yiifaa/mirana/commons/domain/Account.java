package com.yiifaa.mirana.commons.domain;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.yiifaa.mirana.persistence.Identifiable;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 上午11:27:18
 */
@Entity
@Table(name = "T_SECURITY_ACCOUNT")
public class Account implements Identifiable<String>, UserDetails {

	private static final long serialVersionUID = -2808295406882008960L;

	@Id
	@Column(name = "PK", length = 40)
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	@Column(name = "USER_NAME", length = 40, unique = true)
	private String username;

	@Column(name = "REMARK_NAME", length = 40)
	private String remarkName;

	// 密码
	@Column(name = "A_PASS", length = 128)
	@JsonIgnore
	private String password;

	// 确认密码
	@Transient
	@JsonIgnore
	private String confirmPassword;

	/* 创建时间 */
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/* 帐户是否已激活 */
	@Column(name = "ACCOUNT_ENABLED")
	private boolean enabled = true;

	/* 帐户是否已过期 */
	@Column(name = "ACCOUNT_EXPIRED")
	private boolean accountExpired = false;

	/* 帐户是否已锁定 */
	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountLocked = false;

	/* 密码是否已过期 */
	@Column(name = "CREDENTIALS_EXPIRED")
	private boolean credentialsExpired = false;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinTable(name = "T_SECURITY_CONN_AR", joinColumns = {
			@JoinColumn(name = "ACCOUNT_ID") }, inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@OrderBy("order asc")
	@BatchSize(size = 15)
	private List<Role> roles;

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = Lists.newArrayList();
		}
		this.roles.add(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.cloud.persistence.Identifiable#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.cloud.persistence.Identifiable#setId(java.lang.Object)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the remarkName
	 */
	public String getRemarkName() {
		return remarkName;
	}

	/**
	 * @param remarkName
	 *            the remarkName to set
	 */
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired
	 *            the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked
	 *            the accountLocked to set
	 */
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @return the credentialsExpired
	 */
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @param credentialsExpired
	 *            the credentialsExpired to set
	 */
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
