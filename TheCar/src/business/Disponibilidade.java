package business;
import java.time.LocalDateTime;

public class Disponibilidade {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private LocalDateTime dataInicio;
	private LocalDateTime dataFinal;
	private double valorDaDiaria;
	
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
	
	//Métodos -------------------------------------------------------------------------------------------------------------------------
	public boolean consultaDisponibilidade(LocalDateTime data) {
		if(!data.isBefore(getDataInicio()) && !data.isAfter(getDataFinal()))
			return true;
		return false;
	}
}
