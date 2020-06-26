package view;

import java.io.IOException;

import controller.ArquivosController;

public class Principal {

	public static void main(String[] args) {
		
		ArquivosController arq = new ArquivosController();
		
		try {
			arq.insereCadastro("arquivo.csv", 12, "Leonardo", "leonardo@gmail.com");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}

}
