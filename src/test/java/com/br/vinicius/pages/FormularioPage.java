package com.br.vinicius.pages;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;



public class FormularioPage {
	
	private WebDriver driver = new ChromeDriver();

	public void inicializa() {
		driver.get("http://aprendendotestar.com.br/treinar-automacao.php");
	}
	
	public void finaliza() {
		driver.close();
		driver.quit();
	}
	
	public void clicaAlert() { // clica no alert n vezes
		int n = 2;
		for(int i=0; i<n; i++) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("Clicou no alert");
		}
	}
	
	private String getUsuario() {
		return driver.findElement(By.xpath("//form//td[contains(text(), 'Usuário')]")).getText();
	}
	
	private String getSenha() {
		return driver.findElement(By.xpath("//form//td[contains(text(), 'Senha')]")).getText();
	}
	
	private String getNome() {
		return driver.findElement(By.xpath("//form//td[contains(text(), 'Nome')]")).getText();
	}
		
	private void getInputUsuario(String usuario) {
		driver.findElement(By.name("form_usuario")).sendKeys(usuario);
		System.out.println("Preencheu o usuário com: " + usuario); 
	}
	
	private void getInputSenha(String senha) {
		driver.findElement(By.name("form_senha")).sendKeys(senha);
		System.out.println("Preencheu a senha com: " + senha); 
	}
	
	private void getInputNome(String nome) {
		driver.findElement(By.name("form_nome")).sendKeys(nome);
		System.out.println("Preencheu o nome com: " + nome); 
	}
	
	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,500)");
	}
	
	private String getNomeTabela(String nome) {
		return driver.findElement(By.xpath("//td[contains(text(), '319')]/../../tr[2]/td[contains(text(), '"+nome+"')]")).getText();
	}
	
	private String getUsuarioTabela(String usuario) {
		return driver.findElement(By.xpath("//td[contains(text(), '319')]/../../tr[2]/td[contains(text(), '"+usuario+"')]")).getText();
	}
	
	private String getSenhaTabela(String senha) {
		return driver.findElement(By.xpath("//td[contains(text(), '319')]/../../tr[2]/td[contains(text(), '"+senha+"')]")).getText();
	}

	private String getTitulo() {
		return driver.findElement(By.tagName("h1")).getText();
	}
	
	private String getErroCadastroSemNome() {
		return driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(), 'Existem campos em branco.')]")).getText();
	}
	
	public void clicarAtualizar() {
		driver.findElement(By.linkText("Clique aqui")).click();
	}
	
	public void clicarApagar(String nome) {
		driver.findElement(By.xpath("//td[contains(text(), '"+nome+"')]/following-sibling::td/a[contains(text(), 'Apagar')]")).click();
		System.out.println("Registro apagado.");
	}
	
	public void clicarBtnEnviar() {
		driver.findElement(By.xpath("//form//input[@type='submit']")).click();
	}
	
	public void preencherInputUsuario(String usuario) {
		getInputUsuario(usuario);
	}
	
	public void preencherInputSenha(String senha) {
		getInputSenha(senha);
	}
	
	public void preencherInputNome(String nome) {
		getInputNome(nome);
	}
	
	public void validarLabelsForm() throws Throwable {
		Assert.assertEquals("Usuário", getUsuario());
		Assert.assertEquals("Senha", getSenha());
		Assert.assertEquals("Nome:", getNome());
		System.out.println("Labels Form validado.");
	}
	
	public void validarTabela(String nome, String usuario, String senha) {
		String nomeSite = getNomeTabela(nome);
		String usuarioSite = getUsuarioTabela(usuario);
		String senhaSite = getSenhaTabela(senha);
		
		assertEquals(nome, nomeSite);
		System.out.println("Nome Validado");
		assertEquals(usuario, usuarioSite);
		System.out.println("Usuário Validado");
		assertEquals(senha, senhaSite);
		System.out.println("Senha Validado");
	}
	
	public void validarTitulo(String titulo) {
		assertEquals(titulo, getTitulo());
	}
	
	public void validarErroCadastroSemNome(String erro) {
		assertEquals(erro, getErroCadastroSemNome());
		System.out.println("Erro aparece na tela");
	}
	
	public String capturaTela() {
		String path;
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("ddMMyyyy HHmmss").format(dataHoraAtual);

		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			String nomeCompletoDoArquivo = source.getPath();
			String extSource = FilenameUtils.getExtension(nomeCompletoDoArquivo);
			String name = data + "." + extSource;
			path = "./target/screenshots/" + name;
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Falha na captura da tela: " + e.getMessage();
		}
		return path;
	}
}