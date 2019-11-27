package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import business.Aluguel;
import business.Disponibilidade;
import business.Documentacao;
import business.Endereco;
import business.Pessoa;
import business.Veiculo;


public class AluguelDAO implements DAO<Aluguel, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public AluguelDAO(String filename) throws IOException {
		file = new File(filename);
		boolean append = file.exists();
		fos = new FileOutputStream(file, append); 
		outputFile = new AppendableObjectOutputStream(fos, append);
	}

	public void add(Aluguel aluguel) {
		try {
			outputFile.writeObject(aluguel);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + aluguel.getId() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Aluguel getAluguel(String chave) {
		Aluguel aluguel = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				aluguel = (Aluguel) inputFile.readObject();

				if (chave.contentEquals(chave)) {
					return aluguel;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o aluguel '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Aluguel> getAll() {
		List<Aluguel> alugueis = new ArrayList<Aluguel>();
		Aluguel aluguel = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				aluguel = (Aluguel) inputFile.readObject();
				alugueis.add(aluguel);
				
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pessoa no disco!");
			e.printStackTrace();
		}
		return alugueis;
	}

	public boolean update(Aluguel a) {
		List<Aluguel> alugueis = getAll();
		int index = alugueis.indexOf(a);

		if (index != -1) {
			alugueis.set(index, a);
			return true;
		}
		saveToFile(alugueis);
		return false;
	}

	public boolean remove(Aluguel a) {
		List<Aluguel> alugueis = getAll();
		int index = alugueis.indexOf(a);
		if (index != -1) {
			alugueis.remove(index);
			return true;
		}
		saveToFile(alugueis);
		return false;
	}

	private void saveToFile(List<Aluguel> alugueis) {
		try {
			close();
			boolean append = file.exists();
			fos = new FileOutputStream(file, false); 
			outputFile = new AppendableObjectOutputStream(fos, append);

			for (Aluguel aluguel : alugueis) {
				outputFile.writeObject(aluguel);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
	}
	
	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
	}

	@Override
	public Disponibilidade getDisponibilidade(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documentacao getDocumentacao(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco getEndereco(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Veiculo getVeiculo(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
