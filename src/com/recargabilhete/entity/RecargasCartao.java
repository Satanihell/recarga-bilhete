package com.recargabilhete.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RecargasCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRecargaCartao;
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)  
	private TipoRecarga tipoRecarga = new TipoRecarga();
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY) 
	private Cartao cartao = new Cartao();
	private double valor;
	private int integracao;
	@Temporal(TemporalType.DATE)
	private Date ultimaBilhetagem = new Date();
	
	public long getIdRecargaCartao() {
		return idRecargaCartao;
	}

	public void setIdRecargaCartao(long idRecargaCartao) {
		this.idRecargaCartao = idRecargaCartao;
	}

	public TipoRecarga getTipoRecarga() {
		return tipoRecarga;
	}

	public void setTipoRecarga(TipoRecarga tipoRecarga) {
		this.tipoRecarga = tipoRecarga;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIntegracao() {
		return integracao;
	}

	public void setIntegracao(int integracao) {
		this.integracao = integracao;
	}

	public Date getUltimaBilhetagem() {
		return ultimaBilhetagem;
	}

	public void setUltimaBilhetagem(Date ultimaBilhetagem) {
		this.ultimaBilhetagem = ultimaBilhetagem;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof RecargasCartao) {
			RecargasCartao recargasCartao = (RecargasCartao) obj;
			return recargasCartao.getIdRecargaCartao() == this.getIdRecargaCartao();
		}
		return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer sb = new StringBuffer();
		sb.append("idRecargaCartao;" + getIdRecargaCartao());
		sb.append("idTipoRecarga;" + getTipoRecarga());
		sb.append("idCartao;" + getCartao());
		sb.append("valor;" + getValor());
		sb.append("integracao;;" + getIntegracao());
		sb.append("ultimaBilhetagem ;" + sdf.format(getUltimaBilhetagem()));
		return sb.toString();
	}

}
