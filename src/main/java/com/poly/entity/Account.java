package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	
	@Id
	String username;
	String password;
	String fullname;
	String email;	
	String photo;
	
	String phone;
	
	String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	List<Bill> bills;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@JsonIgnore
	List<Cart> carts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@JsonIgnore
	List<Favorites> favourites;
	
}
