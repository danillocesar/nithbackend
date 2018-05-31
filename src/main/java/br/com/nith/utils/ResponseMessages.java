package br.com.nith.utils;

public enum ResponseMessages {
	ERRO_INTERNO("Erro interno."), SUCESSO("Dado enviado com sucesso."), PROIBIDO("Acesso negado."), NAO_AUTORIZADO("Acesso negado."), 
	SENHA_INVALIDA("Senha inválida."), USUARIO_INVALIDO("Usuário não encontrado."), 
	USUARIO_EXISTENTE("Email já está em uso.");
	
	private String descricao;
	ResponseMessages(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return this.descricao;
    }
}
