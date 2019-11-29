package business;

import java.io.*;
import java.util.InputMismatchException;

import collections.ListaPessoa;
import error.ExcecaoGeral;
import org.json.JSONObject;
public class Documentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static int idUnico = 0;
	private int id;
	private String cpf;
	private String rg;
	private String cnh;
	private File cpfImg;
	private File rgImg;
	private File cnhImg;
	private File certificadoBonsAntecedentesImg;
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	/*public Documentacao(String cpf, String rg, String cnh, File cpfImg, File cnhImg,
		File certificadoBonsAntecedentesImg) {
		super();
		this.setCpf(cpf);
		this.setRg(rg);
		this.setCnh(cnh);
		this.setCpfImg(cpfImg);
		this.setCnhImg(cnhImg);
		this.setCertificadoBonsAntecedentesImg(certificadoBonsAntecedentesImg);
		this.setId(idUnico);
		Documentacao.idUnico++;
	}	*/
	
	//Getters e Setters ---------------------------------------------------------------------------------------------------------------
	
	public String getCpf() {
		return cpf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCpf(String cpf) throws Exception {
		ListaPessoa pessoas = new ListaPessoa();
		Pessoa p = pessoas.getPessoaCpf(cpf);
		if(!eCpfValido(cpf))
			throw new ExcecaoGeral("O CPF informado e invalido");
		else if (p != null)
			throw new ExcecaoGeral("O CPF informado ja esta registrado");
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) throws ExcecaoGeral {
		this.cnh = cnh;
	}
	public File getCpfImg() {
		return cpfImg;
	}
	public void setCpfImg(File cpfImg) {
		this.cpfImg = cpfImg;
	}public File getRgImg() {
		return rgImg;
	}
	public void setRgImg(File rgImg) {
		this.rgImg = rgImg;
	}
	public File getCnhImg() {
		return cnhImg;
	}
	public void setCnhImg(File cnhImg) {
		this.cnhImg = cnhImg;
	}
	public File getCertificadoBonsAntecedentesImg() {
		return certificadoBonsAntecedentesImg;
	}
	public void setCertificadoBonsAntecedentesImg(File certificadoBonsAntecedentesImg) {
		this.certificadoBonsAntecedentesImg = certificadoBonsAntecedentesImg;
	}
	
	//Métodos -------------------------------------------------------------------------------------------------------------------------
	public boolean eCpfValido(String cpf) {
		//Removendo possíveis caracteres especiais que podem invalidar o cpf correto
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		cpf = cpf.replace(" ", "");
		cpf = cpf.replace("/", "");
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||(cpf.length() != 11)) return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
        
        try {
        	sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {                      
	            num = (int)(cpf.charAt(i) - 48); 
	            sm = sm + (num * peso);
	            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) dig10 = '0';
            else dig10 = (char)(r + 48);
            
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
	            num = (int)(cpf.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) dig11 = '0';
            else dig11 = (char)(r + 48);
            
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) return(true);
            else return(false);
        }
        catch (InputMismatchException erro) { 
        	return(false);
        }
	}
	
	public static boolean eCnhValida(String cnh) {
		char char1 = cnh.charAt(0);

		if (cnh.replaceAll("\\D+", "").length() != 11
				|| String.format("%0" + 11 + "d", 0).replace('0', char1).equals(cnh)) {
			return false;
		}

		long v = 0, j = 9;

		for (int i = 0; i < 9; ++i, --j) {
			v += ((cnh.charAt(i) - 48) * j);
		}

		long dsc = 0, vl1 = v % 11;

		if (vl1 >= 10) {
			vl1 = 0;
			dsc = 2;
		}

		v = 0;
		j = 1;

		for (int i = 0; i < 9; ++i, ++j) {
			v += ((cnh.charAt(i) - 48) * j);
		}

		long x = v % 11;
		long vl2 = (x >= 10) ? 0 : x - dsc;

		return (String.valueOf(vl1) + String.valueOf(vl2)).equals(cnh.substring(cnh.length() - 2));

	}

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("cpf", this.getCpf());
		obj.put("rg", this.getRg());
		obj.put("cnh", this.getCnh());
		obj.put("cpfImg", this.getCpfImg());
		obj.put("rgImg", this.getRgImg());
		obj.put("cnhImg", this.getCnhImg());
		obj.put("certificadoBonsAntecedentesImg", this.getCertificadoBonsAntecedentesImg());
		return obj;
	}
}
