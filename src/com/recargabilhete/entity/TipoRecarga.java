package com.recargabilhete.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TipoRecarga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTipoRecarga;
	private String nomeTipoRecarga;
	private int tipoRecarga;
	private int tipoTarifacao;
	private int tipoIntegracao;
	@Temporal(TemporalType.DATE)
	private Date validadeRecarga = new Date();

	public long getIdTipoRecarga() {
		return idTipoRecarga;
	}

	public void setIdTipoRecarga(long idTipoRecarga) {
		this.idTipoRecarga = idTipoRecarga;
	}

	public String getNomeTipoRecarga() {
		return nomeTipoRecarga;
	}

	public void setNomeTipoRecarga(String nomeTipoRecarga) {
		this.nomeTipoRecarga = nomeTipoRecarga;
	}

	public int getTipoRecarga() {
		return tipoRecarga;
	}

	public void setTipoRecarga(int tipoRecarga) {
		this.tipoRecarga = tipoRecarga;
	}

	public int getTipoTarifacao() {
		return tipoTarifacao;
	}

	public void setTipoTarifacao(int tipoTarifacao) {
		this.tipoTarifacao = tipoTarifacao;
	}

	public int getTipoIntegracao() {
		return tipoIntegracao;
	}

	public void setTipoIntegracao(int tipoIntegracao) {
		this.tipoIntegracao = tipoIntegracao;
	}

	public Date getValidadeRecarga() {
		return validadeRecarga;
	}

	public void setValidadeRecarga(Date validadeRecarga) {
		this.validadeRecarga = validadeRecarga;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof TipoRecarga) {
			TipoRecarga tipoRecarga = (TipoRecarga) obj;
			return tipoRecarga.getIdTipoRecarga() == this.getIdTipoRecarga();
		}
		return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer sb = new StringBuffer();
		sb.append("IdTipoRecarga" + getIdTipoRecarga());
		sb.append("NomeTipoRecarga" + getNomeTipoRecarga());
		sb.append("TipoRecarga" + getTipoRecarga());
		sb.append("TipoTarifacao" + getTipoTarifacao());
		sb.append("TipoIntegracao" + getTipoIntegracao());
		sb.append("ValidadeRecarga" + sdf.format(getValidadeRecarga()));
		return sb.toString();
	}

}
