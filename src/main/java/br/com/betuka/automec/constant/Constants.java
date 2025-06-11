package br.com.betuka.automec.constant;

public class Constants {
	
	/* Situação cadastral */
	
	public static final String SITUACAO_ATIVO = "A";
	public static final String SITUACAO_INATIVO = "I";
	public static final String SITUACAO_SUSPENSO = "S";
	public static final String SITUACAO_CANCELADO = "C";
	
	public static final String EXECUTADO_COM_SUCESSO = "Executado com sucesso.";
	public static final String OCORREU_ERRO = "Ocorreu erro: ";
	
	/*
	 * Constants Fabricante
	 */
	public static final String FABRICANTE_INEXISTENTE = "Fabricante inexistente.";
	public static final String FABRICANTE_DESCRICAO_OBRIGATORIA = "Descrição deve ser informada.";
	
	/*
	 * Constants Grupo de produtos e serviços
	 */
	public static final String GRUPO_INEXISTENTE = "Grupo inexistente.";
	public static final String GRUPO_DESCRICAO_OBRIGATORIA = "Descrição deve ser informada.";
	
	/*
	 * Constants Cliente
	 */
	public static final String CLIENTE_INEXISTENTE = "Cliente inexistente.";
	public static final String CLIENTE_NOME_OBRIGATORIA = "Nome deve ser informado.";
	public static final String CLIENTE_CELULAR_OBRIGATORIA = "Nro do celular deve ser informado.";
	public static final String CLIENTE_CPF_OBRIGATORIA = "Nro do CPF deve ser informado.";
	public static final String CLIENTE_CPF_INEXISTENTE = "Nro do CPF inexistente.";
	public static final String CLIENTE_CPF_CADASTRADA = "Nro do CPF já cadastrado para outro cliente.";
	
	/*
	 * Constants Mecanico
	 */
	public static final String MECANICO_INEXISTENTE = "Mecanico inexistente.";
	public static final String MECANICO_NOME_OBRIGATORIA = "Nome deve ser informado.";
	public static final String MECANICO_CELULAR_OBRIGATORIA = "Nro do celular deve ser informado.";
	
	/*
	 * Constants Veículo
	 */
	public static final String VEICULO_INEXISTENTE = "Veículo inexistente.";
	public static final String VEICULO_PLACA_OBRIGATORIA = "Número da placa deve ser informado.";
	public static final String VEICULO_PLACA_INEXISTENTE = "Número da placa inexistente.";
	public static final String VEICULO_PLACA_CADASTRADA = "Veículo já cadastrado com este número de placa.";
	
	/*
	 * Constants Marca, Modelo
	 */
	public static final String MARCA_MODELO_INEXISTENTE = "Marca e modelo inexistente.";
	public static final String MARCA_MODELO_CADASTRO = "Modelo já cadastrado para esta marca.";
	
	/*
	 * Constants Modelo
	 */
	public static final String MODELO_INEXISTENTE = "Modelo inexistente.";
	public static final String MODELO_DESCRICAO_OBRIGATORIA = "Descrição deve ser informada.";
	
	public static final String MODELO_CODIGO_N_INFRORMADO = "Informe o código do modelo.";
	
	/*
	 * Constants Marca
	 */
	public static final String MARCA_INEXISTENTE = "Marca inexistente.";
	public static final String MARCA_DESCRICAO_OBRIGATORIA = "Descrição deve ser informada.";
	public static final String MARCA_JA_CADASTRADO = "Marca já cadastrada.";
	public static final String MARCAR_CODIGO_N_INFRORMADO = "Informe o código da marca.";
	public static final String MARCA_UTILIZADA = "Não foi possível excluir a marca. Existem registros relacionados.";
	
	/*
	 * Constants Recurso
	 */
	public static final String RECURSO_INEXISTENTE = "Recurso inexistente.";
	public static final String RECURSO_DESCRICAO_OBRIGATORIA = "Descrição deve ser informada.";
	public static final String RECURSO_SITUACAO_INEXISTENTE = "Situação não condiz com o esperado: (A, C).";
	public static final String RECURSO_JA_CADASTRADO = "Recurso já cadastrado.";
	
	/*
	 * Constants Perfil
	 */
	public static final String PERFIL_INEXISTENTE = "Perfil inexistente.";
	public static final String PERFIL_DESCRICAO_OBRIGATORIA = "Descrição deve ser informada.";
	public static final String PERFIL_JA_CADASTRADO = "Perfil já cadastrado.";
	public static final String PERFIL_UTILIZADO = "Não foi possível excluir o perfil. Existem registros relacionados.";
	
	/*
	 * Constants Usuário
	 */
	public static final String USUARIO_INEXISTENTE = "Usuário inexistente.";
	public static final String USUARIO_NOME_OBRIGATORIO = "Nome deve ser informado.";
	public static final String USUARIO_NOME_CADASTRADO = "Nome já cadastrado.";
	public static final String USUARIO_EMAIL_OBRIGATORIO = "E-mail deve ser informado.";
	public static final String USUARIO_EMAIL_INCONSISTENTE = "E-mail inconsistente.";
	
	public static final String USUARIO_LOGIN_OBRIGATORIO = "Login deve ser informado.";
	public static final String USUARIO_LOGIN_TAMANHO_MINIMO = "Login deve ter no mínimo 6 caracteres.";
	public static final String USUARIO_LOGIN_INEXISTENTE = "Login inexistente.";
	public static final String USUARIO_LOGIN_CADASTRADO = "Login já cadastrado.";
	
	public static final String USUARIO_SENHA_OBRIGATORIA = "Senha deve ser informada.";
	public static final String USUARIO_SENHA_TAMANHO_MINIMO = "Senha deve ter no mínimo 6 caracteres.";
	public static final String USUARIO_SITUACAO_INEXISTENTE = "Situação não condiz com o esperado: (A, I).";
	
	/*
	 * Constants Autenticação
	 */
	public static final String AUTENTICACAO_LOGIN_INEXISTEN = "Login inexistente.";
	public static final String AUTENTICACAO_LOGIN_NAO_ATIVO = "Login não está ativo.";
	public static final String AUTENTICACAO_LOGIN_AUTENTICADO = "Autenticação valida.";
	
}

