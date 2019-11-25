package business;

import java.io.Serializable;

import org.json.JSONObject;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private String cep;
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
		return bairro.toUpperCase();
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
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("rua", this.getRua());
		obj.put("numero", this.getNumero());
		obj.put("bairro", this.getBairro());
		obj.put("cidade", this.getCidade());
		obj.put("estado", this.getEstado());
		return obj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
