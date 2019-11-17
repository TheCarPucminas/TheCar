package collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.Pessoa;
import dao.PessoaDAO;

public class ListaPessoa {
	static PessoaDAO pessoaDAO;
	List<Pessoa> pessoas = new ArrayList<Pessoa>();
    
    public ListaPessoa() throws Exception{
    	pessoaDAO = new PessoaDAO("pessoa.bin");
    	pessoas = pessoaDAO.getAll();
    }
                           
    public boolean add(Pessoa pessoa){
    	for(Pessoa v : pessoas) {
    		if(pessoa.equals(v)) {
    			return false;
    		}
    	}
    	pessoas.add(pessoa);
    	pessoaDAO.add(pessoa);
    	return true;
    }
    
    public Pessoa get(int id) {
    	return pessoaDAO.get(id);
    }
    
    public boolean remove(Pessoa veiculo) throws IOException {
    	if (pessoaDAO.remove(veiculo)){
    		pessoas.remove(veiculo);
    		return true;
    	}
    	return false;
    }
    
    public boolean update(Pessoa veiculo) {
    	if(pessoaDAO.update(veiculo)) {
    		pessoas.remove(veiculo);
    		pessoas.add(veiculo);
    		return true;
    	}
    	return false;
    }
    
    public List<Pessoa> getAll(){
    	return pessoas;
    }
    
    //Buscas por nome, bairro, cidade
    public List<Pessoa> getPessoaNome(String nome){
    	List<Pessoa> filtrado = new ArrayList<Pessoa>();
    	pessoas.stream().filter(pessoa -> pessoa.getNome().contentEquals(nome));
    	return filtrado;
    }
    
    public List<Pessoa> getPessoaBairro(String bairro){
    	List<Pessoa> filtrado = new ArrayList<Pessoa>();
    	pessoas.stream().filter(pessoa -> pessoa.getEndereco().getBairro().contentEquals(bairro));
    	return filtrado;
    }
    
    public List<Pessoa> getPessoaCidade(String cidade){
    	List<Pessoa> filtrado = new ArrayList<Pessoa>();
    	pessoas.stream().filter(pessoa -> pessoa.getEndereco().getCidade().contentEquals(cidade));
    	return filtrado;
    }
}
