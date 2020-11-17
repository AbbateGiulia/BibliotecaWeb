package it.bibliotecaweb.model;

public enum Genere {

	STORICO("storico"),
	NARRATIVA("narrativa"),
	ROSA("rosa"),
	YOUNG_ADULT("youngadult"),
	GIALLO("giallo"),
	THRILLER("thriller"),
	NOIR("noir"),
	PER_RAGAZZI("perRagazzi"),
	PER_BAMBINI("perBambini"),
	RACCONTI("racconti"),
	AVVENTURA("avventura"),
	FANTASCIENZA("fantascienza"), 
	FANTASY("fantasy"),
	EROTICO("erotico"),
	REALISMO_MAGICO("realismoMagico"),
	CLASSICI("classici"),
	BIOGRAFIA("biografia"),
	AUTOAIUTO("autoaiuto"),
	FILOSOFIA("filosofia"), 
	LETTERATURA("letteratura"),
	LINGUE_STRANIERE("linguestraniere"),
	LINGUISTICA("linguistica"),
	VIAGGIO("viaggio"),
	STORIA("storia"),
	GUIDA("guida"),
	FOLKLORE("folklore"),
	RICETTARIO("ricettario"),
	EPISTOLE("epistole"),
	SOCIETA("societa"),
	ATTUALITA("attualita"),
	RELIGIONE("religione"),
	SOCIOLOGIA("sociologia"),
	CARTOMANZIA("cartomanzia"),
	MARKETING("marketing"),
	BENESSERE("benessere"),
	ARCHITETTURA("architettura"),
	PSICOLOGIA("psicologia"),
	POLITICA("politica"),
	ARTE("arte"),
	REPORTAGE("reportage"),
	EDUCAZIONE("educazione"),
	PUERICULTURA("puericultura"),
	ECOLOGIA("ecologia"),
	ESSAY("essay"),
	BOTANICA("botanica"),
	TECNOLOGIA("tecnologia"),
	PROGRAMMAZIONE("programmazione"),
	TRAINING("training"),
	ETOLOGIA("etologia"),
	SPORT("sport"),
	ANTROPOLOGIA("antropologia"),
	MEDICINA("medicina"),
	MUSICA("musica"),
	CINEMA("cinema"),
	CULTURA("cultura"),
	DIETA("dieta"),
	ESOTERISMO("esoterismo"),
	MITOLOGIA("mitologia");
	
	private String stringaGenere;
	
	public String toString() {
		return stringaGenere;
	}
	
	Genere(String stringaGenere) {
		this.stringaGenere=stringaGenere;
	}
}
