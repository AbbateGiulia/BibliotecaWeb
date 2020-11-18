package it.bibliotecaweb.model;

import java.util.EnumSet;

public enum StatoUtente {
	ATTIVO,DISABILITATO;
	
	public static final EnumSet<StatoUtente> allStato = EnumSet.of(ATTIVO, DISABILITATO);

}

