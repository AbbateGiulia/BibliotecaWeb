package it.bibliotecaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "libro")
public class Libro {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;
		@Column(name = "titolo")
		private String titolo;
		// se non uso questa annotation viene gestito come un intero
				@Enumerated(EnumType.STRING)
				private Genere genere;
		@Column(name = "trama")
		private String trama;
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "autore_id")
		private Autore autore;
		
		public Libro() {
			
		}

		public Libro(String titolo, Genere genere, String trama) {
			super();
			this.titolo = titolo;
			this.genere = genere;
			this.trama = trama;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitolo() {
			return titolo;
		}

		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}

		public Genere getGenere() {
			return genere;
		}

		public void setGenere(Genere genere) {
			this.genere = genere;
		}

		public String getTrama() {
			return trama;
		}

		public void setTrama(String trama) {
			this.trama = trama;
		}

		public Autore getAutore() {
			return autore;
		}

		public void setAutore(Autore autore) {
			this.autore = autore;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Libro) {
				Libro libro = (Libro) o;
				return trama.equals(libro.getTrama());
			} else {
				return this.equals(o);
			}
		}
		
}
