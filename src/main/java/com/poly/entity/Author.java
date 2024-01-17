package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Authors")
public class Author implements Serializable{
	
	@Id
	String id;
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author")
	List<Bookauthor> bookauthors;
}
