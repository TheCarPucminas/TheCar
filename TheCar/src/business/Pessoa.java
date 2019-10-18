package business;

import java.io.*;
//import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	//Atributos -----------------------------------------------------------------------------------------------------------------------
	private static int idUnico = 0;
	private int id;
	private String nome;
	private String senha;
	private Documentacao documentacao = new Documentacao();
	private String telefone;
	private String celular;
	private Endereco endereco = new Endereco();
	
	//Construtor ----------------------------------------------------------------------------------------------------------------------
	public Pessoa(String nome, String senha, String cpf, String rg, String cnh, /*File cpfImg, File rgImg, File cnhImg,
			File certificadoBonsAntecedentesImg,*/ String telefone, String celular, String rua, int numero, String bairro, String cidade,
			String estado) {
		this.setNome(nome);
		this.setSenha(senha);
		documentacao.setCpf(cpf);
		documentacao.setRg(rg);
		documentacao.setCnh(cnh);
//		documentacao.setCpfImg(cpfImg);
//		documentacao.setRgImg(rgImg);
//		documentacao.setCnhImg(cnhImg);
//		documentacao.setCertificadoBonsAntecedentesImg(certificadoBonsAntecedentesImg);
		this.setTelefone(telefone);
		this.setCelular(celular);
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

	public void setId(int id) {
		this.id = id;
	}	
	
	//Métodos -----------------------------------------------------------------------------------------------------------------------
	public boolean eSenhaValida(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		criptografaSenha(senha);
		String senhaDB = " ";
		//Aqui terá que comparar com a senha hash que existe no banco de dados
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

		return "Nome: " + this.getNome() + "\nCPF: " + this.getDocumentacao().getCpf() + "\nRG: "+ this.getDocumentacao().getRg() +"\nCNH: " + 
		this.getDocumentacao().getCnh() + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Pessoa) obj).getId();
	}
}

