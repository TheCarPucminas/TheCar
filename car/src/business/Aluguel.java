package business;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.json.JSONObject;
public class Aluguel implements Serializable {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private static int idUnico = 0;
	private int id;
	private LocalDateTime dataEmprestimo;
	private LocalDateTime dataDevolucao;
	private double valorAluguel;
	private boolean devolvido;
	public boolean pago;
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	public Aluguel(LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, double valorAluguel, boolean devolvido,
			boolean pago) {
		super();
		this.setDataEmprestimo(dataEmprestimo);
		this.setDataDevolucao(dataDevolucao);
		this.setValorAluguel(valorAluguel);
		this.setDevolvido(devolvido);
		this.setPago(pago);
		this.setId(idUnico);
		Aluguel.idUnico++;
	}

	//Getters e Setters ---------------------------------------------------------------------------------------------------------------
	public LocalDateTime getDataEmprestimo() {
		return dataEmprestimo;
	}	
	public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public double getValorAluguel() {
		return valorAluguel;
	}
	public void setValorAluguel(double valorAlguel) {
		this.valorAluguel = valorAlguel;
	}
	public boolean isDevolvido() {
		return devolvido;
	}
	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}	
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("valor", this.getValorAluguel());
		obj.put("devolvido", this.isDevolvido());
		obj.put("pago", this.isPago());
		return obj;
	}
	//Métodos -------------------------------------------------------------------------------------------------------------------------
}
