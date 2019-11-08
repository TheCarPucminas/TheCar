package business;

import java.io.*;
//import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONObject;


public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static int idUnico = 0;
	private int id;
	private String nome;
	private String email;
	private String senha;
	private Documentacao documentacao = new Documentacao();
	private String telefone;
	private String celular;
	private String cpf;
	private Endereco endereco = new Endereco();
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	public Pessoa(String nome, String email, String cpf, String rg, String cnh, String senha, String cep, String rua, int numero, String bairro, String cidade, String estado, String telefone, String celular
			 /*, File cpfImg, File rgImg, File cnhImg,File certificadoBonsAntecedentesImg*/) {
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setCpf(cpf);
		documentacao.setCpf(cpf);
		documentacao.setRg(rg);
		documentacao.setCnh(cnh);
//		documentacao.setCpfImg(cpfImg);
//		documentacao.setRgImg(rgImg);
//		documentacao.setCnhImg(cnhImg);
//		documentacao.setCertificadoBonsAntecedentesImg(certificadoBonsAntecedentesImg);
		this.setTelefone(telefone);
		this.setCelular(celular);
		endereco.setCep(cep);
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		this.setId(idUnico);
		Pessoa.idUnico++;
		
	}

	//Getters e Setters ---------------------------------------------------------------------------------------------------------------
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Documentacao getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(Documentacao documentacao) {
		this.documentacao = documentacao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}	
	
	//M�todos -----------------------------------------------------------------------------------------------------------------------
	
	public boolean eSenhaValida(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		criptografaSenha(senha);
		String senhaDB = " ";
		//Aqui ter� que comparar com a senha hash que existe no banco de dados
		if(senhaDB.equals(criptografaSenha(senha))) return true;
		return false;
	}
	
	private String criptografaSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {	     
		//Algoritmo que gera o Hash da senha
	    MessageDigest algoritmoHash = MessageDigest.getInstance("SHA-256");
	    byte messageDigestSenhaAdmin[] = algoritmoHash.digest(senha.getBytes("UTF-8"));
	    StringBuilder senhaString = new StringBuilder();
	    for (byte b : messageDigestSenhaAdmin) {
	    	senhaString.append(String.format("%02X", 0xFF & b));
	    }
	    String senhaHash = senhaString.toString();
		return senhaHash;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + "\nEmail: " + this.getEmail() + "\nNome: " + this.getNome() + "\nCPF: " + this.getDocumentacao().getCpf() + "\nCEP: " + this.getEndereco().getCep() + "\nRua: " + this.getEndereco().getRua() + "\nN�mero: " + this.getEndereco().getNumero() + "\nBairro: " + this.getEndereco().getBairro() + "\nCidade: " + this.getEndereco().getCidade() + "\nEstado: " + this.getEndereco().getEstado() + "\nTelefone: " + this.getTelefone() + "\nCelular: " + this.getCelular();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Pessoa) obj).getId();
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("email", this.getEmail());
		obj.put("nome", this.getNome());
		obj.put("cpf", this.getCpf());
		obj.put("senha", this.getSenha());
		obj.put("cep", this.getEndereco().getCep());
		obj.put("rua", this.getEndereco().getRua());
		obj.put("numero", this.getEndereco().getNumero());
		obj.put("bairro", this.getEndereco().getBairro());
		obj.put("cidade", this.getEndereco().getCidade());
		obj.put("estado", this.getEndereco().getEstado());
		obj.put("telefone", this.getTelefone());
		obj.put("celular", this.getCelular());
		
		return obj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}

