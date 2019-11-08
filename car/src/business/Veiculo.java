package business;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Veiculo implements Serializable {
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private static int idUnico = 0;
	private int id;
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
		this.setId(idUnico);
		Veiculo.idUnico++;
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
		else System.out.println("Chassi inv�lido");
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
	
	//M�todos -------------------------------------------------------------------------------------------------------------------------
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("placa", this.getPlaca());
		obj.put("cor", this.getCor());
		obj.put("anoFabricacao", this.getAnoFabricacao());
		obj.put("anoModelo", this.getAnoModelo());
		obj.put("chassi", this.getChassi());
		obj.put("renavam", this.getRenavam());
		obj.put("marca", this.getMarca());
		obj.put("modelo", this.getModelo());
		obj.put("numeroPortas", this.getNumeroPortas());
		obj.put("quilometragem", this.getQuilometragem());
		obj.put("combustivel", this.getCombustivel());

		return obj;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + "\nPlaca: " + this.getPlaca() + "\nCor: " + this.getCor() + "\nAno de fabrica��o: " + this.getAnoFabricacao() + "\nAno do modelo: " + this.getAnoModelo() + "\nChassi: " + this.getChassi() + "\nRenavam: " + this.getRenavam() + "\nMarca: " + this.getMarca() + "\nModelo: " + this.getModelo() + "\nN�mero de portas: " + this.getNumeroPortas() + "\nQuilometragem: " + this.getQuilometragem() + "\nCombust�vel: " + this.getCombustivel();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.placa == ((Veiculo) obj).getPlaca();
	}
}
