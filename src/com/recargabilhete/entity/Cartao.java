package com.recargabilhete.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCartao;
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY) 
	private Usuario usuario = new Usuario();
	private String tipoCartao;
	@Temporal(TemporalType.DATE)
	private Date dataExpedicao = new Date();
	private String statusCartao;

	public long getIdCartao() {
		return idCartao;
	}

	public void setIdCartao(long idCartao) {
		this.idCartao = idCartao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getStatusCartao() {
		return statusCartao;
	}

	public void setStatusCartao(String statusCartao) {
		this.statusCartao = statusCartao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Cartao) {
			Cartao cartao = (Cartao) obj;
			return cartao.getIdCartao() == this.getIdCartao();
		}
		return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer sb = new StringBuffer();
		sb.append("IdCartao" + getIdCartao());
		sb.append("IdUsuario" + getUsuario());
		sb.append("DataExpedicao" + sdf.format(getDataExpedicao()));
		sb.append("TipoCartao" + getTipoCartao());
		sb.append("StatusCartao" + getStatusCartao());
		return sb.toString();
	}

}
