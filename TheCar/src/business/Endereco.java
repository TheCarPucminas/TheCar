package business;

import java.io.Serializable;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	
	//Getters e Setters ---------------------------------------------------------------------------------------------------------------
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
