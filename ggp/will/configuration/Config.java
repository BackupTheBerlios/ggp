/*
 * Created on 18 nov. 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

/**
 * @author Nicolas
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Config {
		public static Properties config;

			// La langue et le pays du logiciel
		public static Locale locale;
		
		public static File fichierConfig;
		
		public static File fichierConfigDefaut;
	
		public Config(){
			if (config==null){
				config = new Properties();
				
				fichierConfig = new File("configuration/conf/config.conf");
				if (fichierConfig.exists()){
					try {
						config.load(new FileInputStream(fichierConfig));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println(fichierConfig.getAbsolutePath());
				locale = new Locale(config.getProperty("langue"),config.getProperty("pays"));
			}
		}
		
		public Properties getConfig() {
			return config;
		}
		
		public Locale getLocale() {
			return locale;
		}
		
		public File getFichierConfig() {
			return fichierConfig;
		}

}