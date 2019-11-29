package business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;

import collections.ListaAlugueis;
public class Aluguel implements Serializable {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private static int idUnico = 0;
	private int id;
	private LocalDateTime dataEmprestimo;
	private LocalDateTime dataDevolucao;
	private double valorAluguel;
	private boolean devolvido;
	private boolean pago;
	private int idVeiculo;
	private int idLocatario;
	
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	public Aluguel(LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, double valorAluguel, boolean devolvido,
			boolean pago, int idVeiculo, int idLocatario) throws Exception {
		super();
		this.setDataEmprestimo(dataEmprestimo);
		this.setDataDevolucao(dataDevolucao);
		this.setValorAluguel(valorAluguel);
		this.setDevolvido(devolvido);
		this.setPago(pago);
		this.setIdVeiculo(idVeiculo);
		this.setIdLocatario(idLocatario);
		ListaAlugueis listAlugueis = new ListaAlugueis();
		List<Aluguel> alugueis = listAlugueis.getAll();
		int lastId = alugueis.size() == 0 ? 0 : (alugueis.get(alugueis.size()-1).getId() + 1);
		this.setId(lastId);
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
	
	
	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public int getIdLocatario() {
		return idLocatario;
	}

	public void setIdLocatario(int idLocatario) {
		this.idLocatario = idLocatario;
	}

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("valor", this.getValorAluguel());
		obj.put("devolvido", this.isDevolvido());
		obj.put("pago", this.isPago());
		obj.put("dataEmprestimo", this.getDataEmprestimo());
		obj.put("dataDevolucao", this.getDataDevolucao());
		return obj;
	}
	//Métodos -------------------------------------------------------------------------------------------------------------------------
}
