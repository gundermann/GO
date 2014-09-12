package de.nordakademie.wpk.todolist.core.api;

public class NoSettingFoundException extends Exception {

	private static final long serialVersionUID = 3L;
	private Provider provider;
	
	public NoSettingFoundException(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String getMessage() {
		return "No setting found for this provider: "+provider.toString();
	}

}
