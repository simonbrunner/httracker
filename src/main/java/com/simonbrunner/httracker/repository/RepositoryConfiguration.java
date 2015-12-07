package com.simonbrunner.httracker.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.simonbrunner.httracker.repository")
public class RepositoryConfiguration {
}
