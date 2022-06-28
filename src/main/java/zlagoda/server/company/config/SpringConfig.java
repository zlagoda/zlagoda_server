package zlagoda.server.company.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SpringConfig
{
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
