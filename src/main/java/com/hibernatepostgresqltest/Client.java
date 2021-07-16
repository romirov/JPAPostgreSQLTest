package com.hibernatepostgresqltest;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * 
 * @author hanza
 * @param idClient - id клиента в системе
 * @param surname - фамилия клиента
 * @param name - имя клиента
 * @param apparatuses - сданное оборудование
 * CREATE TABLE IF NOT EXISTS client (
     id_client BIGSERIAL PRIMARY KEY,
     surname CHARACTER VARYING(30),
     name CHARACTER VARYING(30),
     id_apparatuses BIGINT
); 
 */
@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_client", unique = true, insertable = false, updatable = false)
	private Long idClient;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.MERGE, orphanRemoval = true)
	Set<Apparatuses> apparatuses = new HashSet<Apparatuses>();
		 
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Apparatuses> getApparatuses() {
		return apparatuses;
	}
	public void setApparatuses(Set<Apparatuses> apparatuses) {
		this.apparatuses = apparatuses;
	}
	
	public void addApparatuses(Apparatuses apparat) {
		apparat.setClient(this);
		apparatuses.add(apparat);
    }
    public void removeApparatuses(Apparatuses apparat) {
       	apparatuses.remove(apparat);
    }
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( "client : {\n" );
		stringBuilder.append( "\tid : " + idClient + ",\n" );
		stringBuilder.append( "\tsurname : " + surname + ",\n" );
		stringBuilder.append( "\tname : " + name + ",\n" );
		Iterator<Apparatuses> iterator = apparatuses.iterator();
		while(iterator.hasNext()) {
			String[] strings = iterator.next().toString().split("\n");
			for(String string : strings)
				stringBuilder.append( "\t" + string + "\n");
		}
		stringBuilder.append( "}\n" );
		return stringBuilder.toString();
	}
}