package collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.Endereco;
import dao.EnderecoDAO;

public class ListaEndereco {
	static EnderecoDAO enderecoDAO;
	List<Endereco> enderecos = new ArrayList<Endereco>();
    
    public ListaEndereco() throws Exception{
    	enderecoDAO = new EnderecoDAO("endereco.bin");
    	enderecos = enderecoDAO.getAll();
    }
                           
    public boolean add(Endereco endereco){
    	for(Endereco v : enderecos) {
    		if(endereco.equals(v)) {
    			return false;
    		}
    	}
    	enderecos.add(endereco);
    	enderecoDAO.add(endereco);
    	return true;
    }
    
    public Endereco get(String placa) {
    	return enderecoDAO.getEndereco(placa);
    }
    
    public boolean remove(Endereco endereco) throws IOException {
    	if (enderecoDAO.remove(endereco)){
    		enderecos.remove(endereco);
    		return true;
    	}
    	return false;
    }
    
    public boolean update(Endereco endereco) {
    	if(enderecoDAO.update(endereco)) {
    		enderecos.remove(endereco);
    		enderecos.add(endereco);
    		return true;
    	}
    	return false;
    }
    
    public List<Endereco> getAll(){
    	return enderecos;
    }
  
	public List<Endereco> procuraPorBairro(String bairro) {
		List<Endereco> filtrado = new ArrayList<Endereco>();
		enderecos.stream().filter(endereco -> endereco.getBairro().contains(bairro.toUpperCase())).forEach(endereco -> filtrado.add(endereco));

		return filtrado;
	}
}