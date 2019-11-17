package dao;
import java.io.IOException;
import java.util.List;

import business.Aluguel;
import business.Disponibilidade;
import business.Documentacao;
import business.Endereco;
import business.Pessoa;
import business.Veiculo;

public interface DAO<T, K> {
	public Pessoa get(int i);
	public Aluguel getAluguel(K chave);
	public Disponibilidade getDisponibilidade(K chave);
	public Documentacao getDocumentacao(K chave);
	public Endereco getEndereco(K chave);
	public Veiculo getVeiculo(K chave);
	public void add(T p);
	public boolean update(T p);
	public boolean remove(T p) throws IOException;
	public List<T> getAll();
}