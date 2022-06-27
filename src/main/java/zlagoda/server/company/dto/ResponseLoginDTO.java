package zlagoda.server.company.dto;

public class ResponseLoginDTO
{
	private String login;
	private String password;

	public ResponseLoginDTO(final String login, final String password)
	{
		this.login = login;
		this.password = password;
	}

	public ResponseLoginDTO()
	{
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(final String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}
}
