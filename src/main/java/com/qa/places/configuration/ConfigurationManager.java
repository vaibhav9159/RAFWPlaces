package com.qa.places.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.qa.places.APIFrameWorkException.APIFrameWorkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream fis;

	public Properties readPropFile()
	{
		prop = new Properties();
		String envName =  System.getProperty("env"); // for passing values via maven 
		
	/////////////// for passing values via prop file manually	
//		Properties prop1 = new Properties();
//		try {
//			FileInputStream fis1 = new FileInputStream("./src/test/resources/config/Environments");
//			try {
//				prop1.load(fis1);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e1) {
//		
//			e1.printStackTrace();
//		}
//		String envName = prop1.getProperty("env");
		
///////////////	
		try {
		if(envName==null)
		{
			fis = new FileInputStream("./src/test/resources/config/QAEnvData");
			System.out.println("running tests on default env QA");
		}
		else {
			System.out.println("running tests on env " + envName);
		
		switch (envName.toLowerCase()) {
		case "qa": fis = new FileInputStream("./src/test/resources/config/QAEnvData");
			System.out.println("running tests on " + envName);
			break;
		case "prod": fis = new FileInputStream("./src/test/resources/config/ProdEnvData");
			System.out.println("running tests on " + envName);
			break;
		default: System.out.println("plz select valid env value");
		throw new APIFrameWorkException("Invalid env value custom exception message");
		
			}
		}	
	}
		
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		/////load file 
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	
}

}