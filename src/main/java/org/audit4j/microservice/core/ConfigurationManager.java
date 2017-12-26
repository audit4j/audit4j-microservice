package org.audit4j.microservice.core;

import java.io.InputStream;

import org.audit4j.core.ConfigProvider;
import org.audit4j.core.Configurations;
import org.audit4j.core.YAMLConfigProvider;
import org.audit4j.core.exception.ConfigurationException;
import org.audit4j.core.util.ClassLoaderUtils;
import org.audit4j.microservice.ServerConfiguration;

public class ConfigurationManager {

	private ConfigProvider<ServerConfiguration> configProvider;

	private String configPath = "conf/server.config.yml";

	public ConfigurationManager(String configPath) {
	    this.configPath = configPath;
		configProvider = new YAMLConfigProvider<>(ServerConfiguration.class);
	}

	public ServerConfiguration loadConfiguration() throws ConfigurationException {
		ServerConfiguration config = null;
			config = configProvider
					.readConfig(configPath);
		return config;
	}

	static InputStream getClasspathResourceAsStream(String resourceName) {
		return ClassLoaderUtils.getClassLoader(Configurations.class)
				.getResourceAsStream(resourceName);
	}
}
