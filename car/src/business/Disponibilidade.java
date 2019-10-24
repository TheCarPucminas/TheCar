package business;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.json.JSONObject;

public class Disponibilidade implements Serializable {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private static int idUnico = 0;
	private int id;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFinal;
	private double valorDaDiaria;
	
	//Construtores --------------------------------------------------------------------------------------------------------------------
	public Disponibilidade(LocalDateTime dataInicio, LocalDateTime dataFinal, double valorDaDiaria) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.valorDaDiaria = valorDaDiaria;
		this.setId(idUnico);
		Disponibilidade.idUnico++;
	}
	
	//Getters e Setters ---------------------------------------------------------------------------------------------------------------
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDateTime getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}
	public double getValorDaDiaria() {
		return valorDaDiaria;
	}
	public void setValorDaDiaria(double valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	//Métodos -------------------------------------------------------------------------------------------------------------------------
	public boolean consultaDisponibilidade(LocalDateTime data) {
		if(!data.isBefore(getDataInicio()) && !data.isAfter(getDataFinal()))
			return true;
		return false;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("dataInicio", this.getDataInicio());
		obj.put("dataFinal", this.getDataFinal());
		obj.put("valorDaDiaria", this.getValorDaDiaria());
		return obj;
	}
}
