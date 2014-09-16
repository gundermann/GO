package de.nordakademie.wpk.tasklist.core.server.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.tasks.Tasks;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class GoogleConnection {

	private static String CLIENT_ID = "74768510313-9k4hpid661bkectrpsi9k0q7uu1l0em7.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "vTRK57P1JPAZJYmw0uo5uv0g";

	  private static final String APPLICATION_NAME = "Test";

	  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private ProviderSetting setting;
	  
	public GoogleConnection(ProviderSetting setting) {
		this.setting = setting;
	}

	public Tasks getTasksService() {
		Tasks service = null;
		try {
			  
		      Credential credential = authorize(setting.getUserName());
		      service  = new Tasks.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
		          .setApplicationName(APPLICATION_NAME).build();
		    } catch (IOException e) {
		      System.out.println(e.getMessage());
		    } catch (Throwable t) {
		      t.printStackTrace();
		    }
//		HttpTransport httpTransport = new NetHttpTransport();
//		JsonFactory jsonFactory = new JacksonFactory();
//
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//				httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET,
//				Arrays.asList(TasksScopes.TASKS)).setAccessType("online")
//				.setApprovalPrompt("auto").build();
//
//		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI)
//				.build();
//
//		System.out
//				.println("Please open the following URL in your browser then type the authorization code:");
//		System.out.println("  " + url);
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String code = null;
//		try {
//			code = br.readLine();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		GoogleTokenResponse response = null;
//		try {
//			response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI)
//					.execute();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		GoogleCredential credential = new GoogleCredential()
//				.setFromTokenResponse(response);
//
//		// Create a new authorized API client
//		Tasks service = new Tasks.Builder(httpTransport, jsonFactory,
//				credential).setApplicationName("Test").build();
//
//		TaskLists execute = null;
//		try {
//			execute = service.tasklists().list().execute();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<TaskList> items = execute.getItems();
//		for (TaskList task : items) {
//			System.out.println(task.getTitle());
//		}

		return service;

	}
	
	  /** Authorizes the installed application to access user's protected data. 
	 * @param user */
	  private static Credential authorize(String user) throws Exception {
	    GoogleClientSecrets.Details installedDetails = new GoogleClientSecrets.Details();
	    installedDetails.setClientId(CLIENT_ID);
	    installedDetails.setClientSecret(CLIENT_SECRET);

	    GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
	    clientSecrets.setInstalled(installedDetails);

	    FileDataStoreFactory credentialStore = new FileDataStoreFactory(
	        new File(System.getProperty("user.home"), ".credentials/tasksengine.json"));

	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT,
	        JSON_FACTORY,
	        clientSecrets,
	        Collections.singleton("https://www.googleapis.com/auth/tasks"))
	          .setDataStoreFactory(credentialStore)
	          .build();

	    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
	        .authorize(user);
	  }


}
