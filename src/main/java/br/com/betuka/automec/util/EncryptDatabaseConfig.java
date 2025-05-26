package br.com.betuka.automec.util;

public class EncryptDatabaseConfig {

	public static void main(String[] args) {
        try {
            EncryptionUtil.encryptFile("C:/Temp/db-automec.properties", "C:/Temp/db-automec.enc");
            System.out.println("Arquivo criptografado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
