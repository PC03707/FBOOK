package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Bookauthors", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Authorid", "Bookid"})
})
public class Bookauthor implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
	@JoinColumn(name = "Bookid")
	Book book;
	
	@ManyToOne
	@JoinColumn(name = "Authorid")
	Author author;
	
	
}
