package org.sid.cinema.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categorie{
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(length=75)
	private String name;
	@OneToMany(mappedBy="categorie")
	// json format il faut pas chercher les films pour éviter de tomber dans une boucle infini
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Film> films;

}
