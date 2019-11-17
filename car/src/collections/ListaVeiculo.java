package collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.Veiculo;
import dao.VeiculoDAO;

public class ListaVeiculo {
	static VeiculoDAO veiculoDAO;
	List<Veiculo> veiculos = new ArrayList<Veiculo>();
    
    public ListaVeiculo() throws Exception{
    	veiculoDAO = new VeiculoDAO("veiculo.bin");
    	veiculos = veiculoDAO.getAll();
    }
                           
    public boolean add(Veiculo veiculo){
    	for(Veiculo v : veiculos) {
    		if(veiculo.equals(v)) {
    			return false;
    		}
    	}
    	veiculos.add(veiculo);
    	veiculoDAO.add(veiculo);
    	return true;
    }
    
    public Veiculo get(String placa) {
    	return veiculoDAO.getVeiculo(placa);
    }
    
    public boolean remove(Veiculo veiculo) throws IOException {
    	if (veiculoDAO.remove(veiculo)){
    		veiculos.remove(veiculo);
    		return true;
    	}
    	return false;
    }
    
    public boolean update(Veiculo veiculo) {
    	if(veiculoDAO.update(veiculo)) {
    		veiculos.remove(veiculo);
    		veiculos.add(veiculo);
    		return true;
    	}
    	return false;
    }
    
    public List<Veiculo> getAll(){
    	return veiculos;
    }
    
    public List<Veiculo> getVeiculosAnoFabricacao(int ano){
    	List<Veiculo> filtrado = new ArrayList<Veiculo>();
    	veiculos.stream().filter(veiculo -> veiculo.getAnoFabricacao() >= ano).forEach(veiculo -> filtrado.add(veiculo));
    	return filtrado;
    }
    
  
	public List<Veiculo> search(String placa) {
		List<Veiculo> filtrado = new ArrayList<Veiculo>();
		veiculos.stream().filter(veiculo -> veiculo.getPlaca().contains(placa.toUpperCase())).forEach(veiculo -> filtrado.add(veiculo));

		return filtrado;
	}
}