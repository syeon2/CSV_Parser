package dto;

public class DBConnectDTO {

	public static final String DB_KEY = "DB";
	public static final String URL_KEY = "URL";
	public static final String USER_KEY = "USER";
	public static final String PASSWORD_KEY = "PASSWORD";

	private final String URL;
	private final String USER;
	private final String PASSWORD;

	public DBConnectDTO(String URL, String USER, String PASSWORD) {
		this.URL = URL;
		this.USER = USER;
		this.PASSWORD = PASSWORD;
	}

	public String getURL() {
		return URL;
	}

	public String getUSER() {
		return USER;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}
}
