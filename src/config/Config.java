package nikita.parsit.papki.com.config;

import java.util.Properties;

public class Config {
	private final Properties properties;
 
    // 1. Есть ли ограничения по размеру файла?
   
    // 3. Какой результат по поиску должен быть в итоге после сохранения данных в ES?
        

	public Config(Properties properties) {
		this.properties = properties;
	}

	public String getWorkingFolder() {

		return properties.getProperty("directory.work");

	}

	public String getArchiveFolderName() {
		return properties.getProperty("directory.archives");

	}

	public String getEmailFolderName() {
		return properties.getProperty("directory.emails");
		//return "emails";
	}

	public String getImageFolderName() {
		return properties.getProperty("directory.images");
		//return "images";
	}

	public String getTextFolderName() {
		return properties.getProperty("directory.texts");
		//return "texts";
	}
        
        public String getESHost() {
                return properties.getProperty("ES.host");
        } 
        
       public int getESPort() {
                return Integer.parseInt(properties.getProperty("ES.port"));
                
        }
       
       public String getESProtocol() {
                return properties.getProperty("ES.protocol");
        }
}