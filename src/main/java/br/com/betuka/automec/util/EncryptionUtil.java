package br.com.betuka.automec.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
	private static final String SECRET_KEY = "MinhaSenhaSegura123@2025"; // Substitua por uma chave segura

	// Gera uma chave secreta AES a partir de uma senha
	private static SecretKey getSecretKey(String secret) throws Exception {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(keyBytes, ALGORITHM);
	}

	// Criptografa um texto
	public static String encrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(SECRET_KEY));
		byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	// Descriptografa um texto
	public static String decrypt(String encryptedData) throws Exception {
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, getSecretKey(SECRET_KEY));
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
		return new String(cipher.doFinal(decodedBytes), StandardCharsets.UTF_8);
	}

	// Criptografa um arquivo de propriedades
	public static void encryptFile(String inputFilePath, String outputFilePath) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(inputFilePath));	

		Properties encryptedProperties = new Properties();
		for (String key : properties.stringPropertyNames()) {
			String encryptedValue = encrypt(properties.getProperty(key));
			encryptedProperties.setProperty(key, encryptedValue);
		}

		try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
			encryptedProperties.store(fos, "Arquivo Criptografado");
		}
	}

	// Descriptografa um arquivo de propriedades
	public static Properties decryptFile(String encryptedFilePath) throws Exception {
		Properties encryptedProperties = new Properties();
		encryptedProperties.load(new FileInputStream(encryptedFilePath));

		Properties decryptedProperties = new Properties();
		for (String key : encryptedProperties.stringPropertyNames()) {
			String decryptedValue = decrypt(encryptedProperties.getProperty(key));
			decryptedProperties.setProperty(key, decryptedValue);
		}
		return decryptedProperties;
	}
}
