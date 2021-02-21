package com.br.vinicius.steps;

import com.br.vinicius.pages.FormularioPage;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class FormularioSteps {
	FormularioPage formularioPage = new FormularioPage();
	
	String titulo = "INICIANDO NA AUTOMAÇÃO DE TESTES.";
	String erro = "Existem campos em branco.";

	@Dado("^que eu preenchi os campos \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
	public void queEuPreenchiOsCamposE(String usuario, String senha, String nome) throws Throwable {
		formularioPage.inicializa();
		formularioPage.clicaAlert();
		formularioPage.validarLabelsForm();
		formularioPage.preencherInputUsuario(usuario);
		formularioPage.preencherInputSenha(senha);
		formularioPage.preencherInputNome(nome);
		formularioPage.capturaTela();
	}
	
	@Quando("^clicar em enviar$")
	public void clicarEmEnviar() {
		formularioPage.clicarBtnEnviar();
		formularioPage.clicaAlert();
		System.out.println("Botão enviar clicado");
		formularioPage.capturaTela();
	}

	
	@Então("^verifico que as informações foram inseridas no banco de dados com os valores \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
	public void verificoQueAsInformaçõesForamInseridasNoBancoDeDadosComOsValoresE(String nome, String usuario, String senha) throws Throwable {
		formularioPage.validarTabela(nome, usuario, senha);
		formularioPage.clicarApagar(nome);
		formularioPage.clicaAlert();
		formularioPage.capturaTela();
		formularioPage.finaliza();
		System.out.println("OK");
	}
	
	@Dado("^que eu acesse a página$")
	public void queEuAcesseAPágina() throws Throwable {
		formularioPage.inicializa();
		formularioPage.clicaAlert();
		formularioPage.capturaTela();
		System.out.println("Página acessada");
	}

	@Quando("^clicar em clique aqui para atualizar$")
	public void clicarEmCliqueAquiParaAtualizar() throws Throwable {
		formularioPage.clicarAtualizar();
	}

	@Então("^a página é atualizada$")
	public void aPáginaÉAtualizada() throws Throwable {
		formularioPage.clicaAlert();
		formularioPage.validarTitulo(titulo);
		formularioPage.capturaTela();
		System.out.println("Página atualizada.");
		formularioPage.finaliza();
	}
	
	@Dado("^que eu preenchi os campos \"([^\"]*)\" e \"([^\"]*)\"$")
	public void queEuPreenchiOsCamposE(String usuario, String senha) throws Throwable {
		formularioPage.inicializa();
		formularioPage.clicaAlert();
		formularioPage.validarLabelsForm();
		formularioPage.preencherInputUsuario(usuario);
		formularioPage.preencherInputSenha(senha);
		formularioPage.capturaTela();
	}

	@Então("^verifico a mensagem de erro na tela$")
	public void verificoAMensagemDeErroNaTela() throws Throwable {
		formularioPage.validarErroCadastroSemNome(erro);
		formularioPage.ScrollDown();
		formularioPage.capturaTela();
		formularioPage.finaliza();
	}
}