package de.nordakademie.wpk.tasklist.core.client;


import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

/**
 * Klasse zum Speichern von Einstellungen für alle Provider außer Google.
 * @author Kathrin Kurtz
 *
 */
public class SettingSaver {
	
	/**
	 * Speichert die Einstellungen für einen Provider in einer Datei die nach dem Provider benannt wird
	 * @param providerSetting
	 */
	public void saveSetting(ProviderSetting providerSetting){
		
		ObjectMapper mapper = new ObjectMapper();
		String directoryString = System.getProperty("User.home") +providerSetting.getProvider().toString() +".json";
		File jsonFile = new File(directoryString);
		try {
			jsonFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("ProviderSettings file konnte nicht erzeugt werden");
		}
		
		try {
			mapper.writeValue(jsonFile, providerSetting);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
