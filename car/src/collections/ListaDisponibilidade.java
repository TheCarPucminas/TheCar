package collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.Disponibilidade;
import dao.DisponibilidadeDAO;

public class ListaDisponibilidade {
	static DisponibilidadeDAO disponibilidadeDAO;
	List<Disponibilidade> disponibilidades = new ArrayList<Disponibilidade>();
    
    public ListaDisponibilidade() throws Exception{
    	disponibilidadeDAO = new DisponibilidadeDAO("disponibilidade.bin");
    	disponibilidades = disponibilidadeDAO.getAll();
    }
                           
    public boolean add(Disponibilidade disponibilidade){
    	for(Disponibilidade v : disponibilidades) {
    		if(disponibilidade.equals(v)) {
    			return false;
    		}
    	}
    	disponibilidades.add(disponibilidade);
    	disponibilidadeDAO.add(disponibilidade);
    	return true;
    }
    
    public boolean remove(Disponibilidade disponibilidade) throws IOException {
    	if (disponibilidadeDAO.remove(disponibilidade)){
    		disponibilidades.remove(disponibilidade);
    		return true;
    	}
    	return false;
    }
    
    public boolean update(Disponibilidade disponibilidade) {
    	if(disponibilidadeDAO.update(disponibilidade)) {
    		disponibilidades.remove(disponibilidade);
    		disponibilidades.add(disponibilidade);
    		return true;
    	}
    	return false;
    }
    
    public List<Disponibilidade> getAll(){
    	return disponibilidades;
    }
    
    public Disponibilidade getPorId(int id) {
    	List<Disponibilidade> filtrado = new ArrayList<Disponibilidade>();
		disponibilidades.stream().filter(disponibilidade -> disponibilidade.getId() == id).forEach(disponibilidade -> filtrado.add(disponibilidade));
		return filtrado.get(0);
    }
	
	public List<Disponibilidade> getdisponibilidadesPorVeiculo(int idVeiculo) {
		List<Disponibilidade> filtrado = new ArrayList<Disponibilidade>();
		disponibilidades.stream().filter(disponibilidade -> disponibilidade.getIdVeiculo() == idVeiculo).forEach(disponibilidade -> filtrado.add(disponibilidade));

		return filtrado;
	}
}