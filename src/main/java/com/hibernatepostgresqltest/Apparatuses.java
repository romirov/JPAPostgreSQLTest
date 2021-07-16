package com.hibernatepostgresqltest;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * @author hanza
 * Класс описывает устройство, принятое для починки
 * @pamam id - id устройства в crm
 * @param recNumber - артикуль устройства в crm
 * @param defect - дефект устройства
 * CREATE TABLE IF NOT EXISTS apparatuses (
     id_apparatuses BIGSERIAL PRIMARY KEY,
     recnumber CHARACTER VARYING(30),
     defect TEXT,
     id_client BIGINT
);
 */
@Entity
@Table(name = "apparatuses")
public class Apparatuses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_apparatuses", unique = true, insertable = false, updatable = false)
	private Long idApparatuses;
	
	@Column(name = "recnumber")
	private String recNumber;
	
	@Column(name = "defect")
	private String defect;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client")
	private Client client;
	
	public Long getIdApparatuses() {
		return idApparatuses;
	}
	public void setIdApparatuses(Long idApparatuses) {
		this.idApparatuses = idApparatuses;
	}
	public String getRecNumber() {
		return recNumber;
	}
	public void setRecNumber(String recNumber) {
		this.recNumber = recNumber;
	}	
	public String getDefect() {
		return defect;
	}
	public void setDefect(String defect) {
		this.defect = defect;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("apparatuses : {\n");
		stringBuilder.append("\tid : " + idApparatuses + ",\n");
		stringBuilder.append("\trecNumber : " + recNumber + ",\n");
		stringBuilder.append("\tdefect : " + defect + ",\n");
		stringBuilder.append("}\n");
		return stringBuilder.toString();
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}