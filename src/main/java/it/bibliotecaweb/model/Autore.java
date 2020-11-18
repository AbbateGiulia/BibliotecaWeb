package it.bibliotecaweb.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "autore")
public class Autore {
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;
		@Column(name = "nome")
		private String nome;
		@Column(name = "cognome")
		private String cognome;
		@Column(name = "data_nascita")
		private LocalDate dataNascita;
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "autore")
		private Set<Libro> libri = new HashSet<>();
		
		public Autore() {
			
		}

		public Autore(String nome, String cognome, LocalDate dataNascita) {
			super();
			this.nome = nome;
			this.cognome = cognome;
			this.dataNascita = dataNascita;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public LocalDate getDataNascita() {
			return dataNascita;
		}

		public void setDataNascita(LocalDate dataNascita) {
			this.dataNascita = dataNascita;
		}

		public Set<Libro> getLibri() {
			return libri;
		}

		public void setLibri(Set<Libro> libri) {
			this.libri = libri;
		}
		
		public String toString() {
			return nome;
		}

}
