#language: pt
@todos
Funcionalidade: Preencher formulário
	Como usuário
	Eu quero preencher o formulário
	Para que eu possa verificar a inserção no banco de dados

@devePreencherFormulario
Esquema do Cenário: Deve preencher o formulário
	Dado que eu preenchi os campos "<usuario>", "<senha>" e "<nome>"
	Quando clicar em enviar
	Então verifico que as informações foram inseridas no banco de dados com os valores "<usuario>", "<senha>" e "<nome>"
	
	Exemplos:
	| usuario 				    | senha     | nome          |
	| email@email.com     |       123 |    Nome teste |
	| tore@empresatop.com |   /*$%)*! | Augusto Teles |
	| iujp@qsrfuopope.com |   !@$%&*( | Lorem Ipsum L |
	
@deveClicarBotaoAtualizar
Cenário: Deve clicar no link clique aqui para atualizar
	Dado que eu acesse a página
	Quando clicar em clique aqui para atualizar
	Então a página é atualizada
	
@devePreencherFormularioSemNome @erro
Esquema do Cenário: Deve preencher o formulário sem preencher o nome e verificar o erro
	Dado que eu preenchi os campos "<usuario>" e "<senha>"
	Quando clicar em enviar
	Então verifico a mensagem de erro na tela
	
	Exemplos:
	| usuario 				    | senha     |
	| tore@empresatop.com |   /*$%)*! |
	| iujp@qsrfuopope.com |   !@$%&*( |