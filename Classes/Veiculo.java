package theCar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private String placa;
	private String cor;
	private int anoFabricacao;
	private int anoModelo;
	private String chassi;
	private String renavam;
	private String marca;
	private String modelo;
	private int numeroPortas;
	private long quilometragem;
	private String combustivel;
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	public Veiculo(String placa, String cor, int anoFabricacao, int anoModelo, String chassi, String renavam,
			String marca, String modelo, int numeroPortas, long quilometragem, String combustivel) {
		super();
		this.setPlaca(placa);
		this.setCor(cor);
		this.setAnoFabricacao(anoFabricacao);
		this.setAnoModelo(anoModelo);
		this.setChassi(chassi);
		this.setRenavam(renavam);
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setNumeroPortas(numeroPortas);
		this.setQuilometragem(quilometragem);
		this.setCombustivel(combustivel);
	}
	
	//Getters e Setters ---------------------------------------------------------------------------------------------------------------
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		if(eChassiValido(chassi)) this.chassi = chassi;
		else System.out.println("Chassi inválido");
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumeroPortas() {
		return numeroPortas;
	}

	public void setNumeroPortas(int numeroPortas) {
		this.numeroPortas = numeroPortas;
	}

	public long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	
	//Métodos -------------------------------------------------------------------------------------------------------------------------
	private boolean eChassiValido(String chassi) {
		if (chassi.trim().length() != 17) return false;

	    Pattern zeroNoPrimeiroDigito = Pattern.compile ("^0"); 
	    Matcher matcherZero  = zeroNoPrimeiroDigito.matcher(chassi);

	    Pattern espacoNoChassi = Pattern.compile (" ");  
	    Matcher matcherEspaco = espacoNoChassi.matcher(chassi);

	    Pattern repeticaoMaisDe6Vezes = Pattern.compile ("^.{4,}([0-9A-Z])\\1{5,}");  
	    Matcher matcherRepetir = repeticaoMaisDe6Vezes.matcher(chassi);

	    Pattern caracteresiIoOqQ = Pattern.compile ("[iIoOqQ]");  
	    Matcher matcherCaract = caracteresiIoOqQ.matcher(chassi);

	    Pattern ultimos6Numericos = Pattern.compile ("[0-9]{6}$"); 
	    Matcher matcherUltimos = ultimos6Numericos.matcher(chassi);

	    if (matcherZero.find() || matcherEspaco.find() || matcherRepetir.find() || matcherCaract.find() || !matcherUltimos.find()) return false;
	    return true;
	  }
}
