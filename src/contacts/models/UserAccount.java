package contacts.models;

public class UserAccount {
	private int idUser;
	private String userFirstname;
	private String userLastname;
	private String username;
	private String password;
	
	private String operation;
	
	public UserAccount() {


	}

	public UserAccount(int idUser, String userFirstname, String userLastname, String username, String password, String operation) {
		super();
		this.idUser = idUser;
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.username = username;
		this.password = password;
		this.operation = operation;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
