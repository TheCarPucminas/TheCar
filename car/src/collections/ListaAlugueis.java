package collections;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import business.Aluguel;
import business.Veiculo;
import dao.AluguelDAO;

public class ListaAlugueis {
	static AluguelDAO aluguelDAO;
	List<Aluguel> alugueis = new ArrayList<Aluguel>();
    
    public ListaAlugueis() throws Exception{
    	aluguelDAO = new AluguelDAO("aluguel.bin");
    	alugueis = aluguelDAO.getAll();
    }
                           
    public boolean add(Aluguel aluguel){
    	for(Aluguel v : alugueis) {
    		if(aluguel.equals(v)) {
    			return false;
    		}
    	}
    	alugueis.add(aluguel);
    	aluguelDAO.add(aluguel);
    	return true;
    }
    
    public boolean remove(Aluguel aluguel) throws IOException {
    	if (aluguelDAO.remove(aluguel)){
    		alugueis.remove(aluguel);
    		return true;
    	}
    	return false;
    }
    
    public boolean update(Aluguel aluguel) {
    	if(aluguelDAO.update(aluguel)) {
    		alugueis.remove(aluguel);
    		alugueis.add(aluguel);
    		return true;
    	}
    	return false;
    }
    
    public List<Aluguel> getAll(){
    	return alugueis;
    }
    
    public Aluguel getPorId(int id) {
    	List<Aluguel> filtrado = new ArrayList<Aluguel>();
		alugueis.stream().filter(aluguel -> aluguel.getId() == id).forEach(aluguel -> filtrado.add(aluguel));
		return filtrado.get(0);
    }
	
	public List<Aluguel> getAlugueisPorVeiculo(int idVeiculo) {
		List<Aluguel> filtrado = new ArrayList<Aluguel>();
		alugueis.stream().filter(aluguel -> aluguel.getIdVeiculo() == idVeiculo).forEach(aluguel -> filtrado.add(aluguel));

		return filtrado;
	}
	
	public List<Aluguel> getAlugueisPorLocatario(int idLocatario) {
		List<Aluguel> filtrado = new ArrayList<Aluguel>();
		alugueis.stream().filter(aluguel -> aluguel.getIdLocatario() == idLocatario).forEach(aluguel -> filtrado.add(aluguel));

		return filtrado;
	}
}