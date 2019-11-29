package collections;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import business.Disponibilidade;
import business.Veiculo;
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
	
	public List<Veiculo> consultaDisponibilidade(LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception {
		ListaVeiculo listaVeiculos = new ListaVeiculo();
		List<Veiculo> veiculosDisponiveis = new ArrayList<Veiculo>();
		
		for (Disponibilidade d : disponibilidades) {
			if ((d.getDataInicio().isBefore(dataInicial) || d.getDataInicio().equals(dataInicial)) && (d.getDataFinal().isAfter(dataFinal) || d.getDataFinal().equals(dataFinal))) {	
				Veiculo v = listaVeiculos.getPorId(d.getIdVeiculo());
				veiculosDisponiveis.add(v);
			}
		}
		return veiculosDisponiveis;
	}

	public Disponibilidade getDisponibilidade (LocalDateTime dataInicial, LocalDateTime dataFinal, int idVeiculo) {

		for (Disponibilidade d : disponibilidades) {
			if ((d.getDataInicio().isBefore(dataInicial)
					|| d.getDataInicio().equals(dataInicial))
					&& (d.getDataFinal().isAfter(dataFinal)
					|| d.getDataFinal().equals(dataFinal))
					&& d.getIdVeiculo() == idVeiculo) {
				return d;
			}
		}

		return null;
	}
}