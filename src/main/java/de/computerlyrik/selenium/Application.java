package de.computerlyrik.selenium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application implements EnvironmentAware{
	
	private static Environment env;
	
	public static String getPropertyVal(String val){
	retutn env.getProperty(val);
	}

	public static void main(String[] args) {
		JTextField user= new JTextField();
		JTextField pass= new JPasswordField();
		
		Object[] loginpop={"Enter UserName", user ,"Enter Passowrd:", pass};
		JOptionPane.showConfirmDialog(null, loginpop,"Login",0);
		
		String userNameEntered= user.getText();
		String passwordEntered= pass.getText();
		
		
		SpringApplication.run(Application.class,args);
		//Launching Chrome
		
		ChromeOptions chromeOptions= new ChromeOptions();
		chromeOptions.addArguments(AutomationConstants.DEBUG_PORT);
		chromeOptions.addArguments(AutomationConstants.CHROME_OPTION_TO_MAXIMIZE_WINDOW);
		chromeOptions.addArguments(AutomationConstants.NO_SANDBOX);
		
		WebDriverManager.chromedriver().setup();
		WebDriver webdriver= new ChromeDriver(chromeOptions);
		
		//URL TO LAUNCH ON CHROME
		webdriver.get(AutomationConstants.LOGIN_URL);
		
		WebElement userName= webdriver.findElement(By.id(AutomationConstants.USERNAME_ID));
	        WebElement psswrd= webdriver.findElement(By.id(AutomationConstants.PASSWORD_ID));
		
		try{
		Thread.sleep(1500);
		}catch(InterruptedException e){
		e.printStackTrace();
		}
		
		userName.sendKeys(userNameEntered);
		psswrd.sendKeys(passwordEntered);
		
		try{
		Thread.sleep(1000);
		}catch(InterruptedException e){
		e.printStackTrace();
		}
		
		WebElement loginButton= webdriver.findElement(By.xpath(AutomationConstants.LOGIN_BUTTON_XPATH));
		loginButton.click();
		
		
		try{
		Thread.sleep(20000);
		}catch(InterruptedException e){
		e.printStackTrace();
		}
		webdriver.findElement(By.xpath(AutomationConstants.QUICK_EDIT_XPATH)).click();
                webdriver.findElement(By.xpath(AutomationConstants.CREATE_BUTTON_XPATH)).click();
		
		try{
		Thread.sleep(5000);
		}catch(InterruptedException e){
		e.printStackTrace();
		}
		
               webdriver.switchTO().frame(AutomationConstants.FRAME);
		
		try{
		Thread.sleep(2000);
		}catch(InterruptedException e){
		e.printStackTrace();
		}
		
           webdriver.findElement(By.linkText(AutomationConstants.COMMAND)).click();
	   webdriver.switchTO().parentFrame();
		
		try{
		Thread.sleep(1000);
		}catch(InterruptedException e){
		e.printStackTrace();
		}
		
		//get INPUTS from the user for the JOb to RUN 
		
		JTextField jobName= new JTextField();
		jobName.setText(getPropertyVal("JOB_NAME_XPATH"));
		JTextField machineName= new JTextField();
         	machineName.setText(getPropertyVal("MACHINE_NAME_XPATH"));
		
		Object[] jobDetails={ "Job Name:", jobName, "Machine:", machineName};
		
		JOptionPane.showConfirmDialog(null, jobDetails,"Enter the Job Details", 0);
		
              webdriver.findElement(By.id(AutomationConstants.JOB_NAME_XPATH))sendKeys(jobName.getText());
              webdriver.findElement(By.id(AutomationConstants.MACHINE_NAME_XPATH))sendKeys(machineName.getText());
		
		//close button XPATH 
		//  webdriver.findElement(By.id(AutomationConstants.MACHINE_NAME_XPATH))sendKeys(machineName.getText());
		
		@Override
		public void setEnvironment(final Environment env){
			this.env=env;
		}
		


		



		
		
	}

}
