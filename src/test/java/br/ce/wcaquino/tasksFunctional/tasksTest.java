package br.ce.wcaquino.tasksFunctional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class tasksTest {

	public WebDriver acessarAplicação() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User01\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicação();
		try {
			// clicar em addTodo
			driver.findElement(By.id("addTodo")).click();

			// escrever a descricao
			driver.findElement(By.id("task")).sendKeys("teste via selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			// fechar o driver
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicação();
		try {
			// clicar em addTodo
			driver.findElement(By.id("addTodo")).click();

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// fechar o driver
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicação();
		try {
			//clicar em addTodo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descricao
			driver.findElement(By.id("task")).sendKeys("teste via selenium");
			
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			//fechar o driver
			driver.quit();
		}
	}

		@Test
		public void naoDeveSalvarTarefaComDataPassada() {
			WebDriver driver = acessarAplicação();
			try {
				//clicar em addTodo
				driver.findElement(By.id("addTodo")).click();
				
				//escrever a descricao
				driver.findElement(By.id("task")).sendKeys("teste via selenium");
				
				//escrever a data
				driver.findElement(By.id("dueDate")).sendKeys("10/10/2013");
				
				//clicar em salvar
				driver.findElement(By.id("saveButton")).click();
				
				//validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Due date must not be in past", message);
			} finally {
				//fechar o driver
				driver.quit();
			}
		}
}
