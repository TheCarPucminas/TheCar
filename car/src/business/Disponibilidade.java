package business;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import collections.ListaDisponibilidade;
import collections.ListaVeiculo;

public class Disponibilidade implements Serializable {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private int id;
	private int idVeiculo;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFinal;
	private double valorDaDiaria;
	
	//Construtores --------------------------------------------------------------------------------------------------------------------
	public Disponibilidade(int idVeiculo, LocalDateTime dataInicio, LocalDateTime dataFinal, double valorDaDiaria) throws Exception {
		setIdVeiculo(idVeiculo);
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.valorDaDiaria = valorDaDiaria;
		ListaDisponibilidade listDisponibilidade = new ListaDisponibilidade();
		List<Disponibilidade> disponibilidades = listDisponibilidade.getAll();
		int lastId = disponibilidades.size() == 0 ? 0 : (disponibilidades.get(disponibilidades.size()-1).getId() + 1);
		this.setId(lastId);
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
	
	public int getIdVeiculo() {
		return idVeiculo;
	}
	private void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		obj.put("idVeiculo", this.getIdVeiculo());
		obj.put("dataInicio", this.getDataInicio());
		obj.put("dataFinal", this.getDataFinal());
		obj.put("valorDaDiaria", this.getValorDaDiaria());
		return obj;
	}
	
	@Override
	public String toString() {
		return  "\nData de inicio: " + this.getDataInicio() + "\nData de final: " + this.getDataFinal() + "\nValor da diária: R$" + this.getValorDaDiaria();
	}

	@Override
	public boolean equals(Object obj) {
		return this.getId() == ((Disponibilidade) obj).getId();
	}
}
