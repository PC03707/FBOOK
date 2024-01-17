package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Status")
public class Status implements Serializable{
	@Id
	Integer id = 1;
	@JoinColumn(name = "name")
	String name;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	List<Bill> bills;
}
