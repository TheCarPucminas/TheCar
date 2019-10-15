package theCar;
import java.time.LocalDateTime;
public class Aluguel {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private LocalDateTime dataEmprestimo;
	private LocalDateTime dataDevolucao;
	private double valorAlguel;
	private boolean devolvido;
	public boolean pago;
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	public Aluguel(LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao, double valorAlguel, boolean devolvido,
			boolean pago) {
		super();
		this.setDataEmprestimo(dataEmprestimo);
		this.setDataDevolucao(dataDevolucao);
		this.setValorAlguel(valorAlguel);
		this.setDevolvido(devolvido);
		this.setPago(pago);
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
	public double getValorAlguel() {
		return valorAlguel;
	}
	public void setValorAlguel(double valorAlguel) {
		this.valorAlguel = valorAlguel;
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
	
	//Métodos -------------------------------------------------------------------------------------------------------------------------
}
