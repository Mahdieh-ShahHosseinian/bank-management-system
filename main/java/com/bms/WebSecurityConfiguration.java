package com.bms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.bms.model.Client;
import com.bms.model.Employee;
import com.bms.service.ClientService;
import com.bms.service.EmployeeService;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

		for (Employee emp : employeeService.getAll()) {
			auth.inMemoryAuthentication().withUser(emp.getEmployee_id() + "").password(emp.getFirstname().toLowerCase())
					.roles("EMPLOYEE");
		}

		for (Client client : clientService.getAll()) {
			auth.inMemoryAuthentication().withUser(client.getClient_id() + "")
					.password(client.getFirstname().toLowerCase()).roles("CLIENT");
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT").antMatchers("/branch/**")
				.hasRole("ADMIN").antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/client/**")
				.hasRole("CLIENT").anyRequest().fullyAuthenticated().and().formLogin();
	}

	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
