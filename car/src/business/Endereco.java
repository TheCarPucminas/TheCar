package business;

import java.io.Serializable;

import error.ExcecaoGeral;
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

	public void setRua(String rua) throws ExcecaoGeral {
		if (rua.length() < 3 || rua == null)
			throw new ExcecaoGeral("A rua informada nao e valida");
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

	public void setBairro(String bairro) throws ExcecaoGeral {
		if (bairro.length() < 3 || bairro == null)
			throw new ExcecaoGeral("O bairro informado e invalido");
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) throws ExcecaoGeral {
		if (cidade == null || cidade.length() < 3)
			throw new ExcecaoGeral("A cidade informada e invalida");
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) throws ExcecaoGeral {
		if (estado == null || estado.length() < 2)
			throw new ExcecaoGeral("O estado informado e invalido");
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
