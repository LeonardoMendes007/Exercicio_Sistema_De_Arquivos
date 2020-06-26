package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController {

	@Override
	public void verificaDirTemp() throws IOException {
		String path = "C://temp";
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {

		File fileCSV = new File("C://temp", arquivo);

		if (fileCSV.exists() && fileCSV.isFile()) {

			FileInputStream fluxo = new FileInputStream(fileCSV);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String line = buffer.readLine();
			Boolean exists = false;

			while (line != null) {

				String aux = line.substring(0, line.indexOf(";"));

				if (Integer.parseInt(aux) == codigo) {

					exists = true;
					break;

				}

				line = buffer.readLine();
			}

			fluxo.close();
			leitor.close();
			buffer.close();

			return exists;
		} else {
			throw new IOException("Diretorio inválido ou não existe o arquivo.");
		}

	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {

		File fileCSV = new File("C://temp", arquivo);

		if (fileCSV.exists() && fileCSV.isFile()) {

			if (verificaRegistro(arquivo, codigo)) {

				FileInputStream fluxo = new FileInputStream(fileCSV);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String line = buffer.readLine();

				while (line != null) {

					String aux = line.substring(0, line.indexOf(";"));

					if (Integer.parseInt(aux) == codigo) {

						String[] cutLine = line.split(";");

						System.out.println("Codigo: " + cutLine[0]);
						System.out.println("Nome: " + cutLine[1]);
						System.out.println("Email: " + cutLine[2]);

						break;

					}

					line = buffer.readLine();
				}

				fluxo.close();
				leitor.close();
				buffer.close();
			}

		} else {
			throw new IOException("Diretorio inválido ou não existe o arquivo.");
		}

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {

		File fileCSV = new File("C://temp", arquivo);

		if (fileCSV.exists() && fileCSV.isFile()) {

			if (!verificaRegistro(arquivo, codigo)) {

				FileWriter fileWriter = new FileWriter(fileCSV, true);
				PrintWriter print = new PrintWriter(fileWriter);
				print.write(codigo + ";" + nome + ";" + email + "\n");
				print.flush();
				print.close();
				fileWriter.close();

			} else {
				throw new IOException("O codigo já existe no arquivo.");
			}

		} else {
			throw new IOException("Diretorio inválido ou não existe o arquivo.");
		}
		
		System.out.println("Cadastro realizado com sucesso !!!");

	}

}
