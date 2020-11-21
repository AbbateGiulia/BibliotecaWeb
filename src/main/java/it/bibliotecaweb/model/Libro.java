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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((autore == null) ? 0 : autore.hashCode());
			result = prime * result + ((genere == null) ? 0 : genere.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
			result = prime * result + ((trama == null) ? 0 : trama.hashCode());
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
			Libro other = (Libro) obj;
			if (autore == null) {
				if (other.autore != null)
					return false;
			} else if (!autore.equals(other.autore))
				return false;
			if (genere != other.genere)
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (titolo == null) {
				if (other.titolo != null)
					return false;
			} else if (!titolo.equals(other.titolo))
				return false;
			if (trama == null) {
				if (other.trama != null)
					return false;
			} else if (!trama.equals(other.trama))
				return false;
			return true;
		}
		
		
		
}
