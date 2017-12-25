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

	private static final String DEFAULT_CONFIG_FILE = "conf/server.config.yml";

	public ConfigurationManager() {
		configProvider = new YAMLConfigProvider<>(ServerConfiguration.class);
	}

	public ServerConfiguration loadConfiguration() throws ConfigurationException {
		ServerConfiguration config = null;
			config = configProvider
					.readConfig(DEFAULT_CONFIG_FILE);
		return config;
	}

	static InputStream getClasspathResourceAsStream(String resourceName) {
		return ClassLoaderUtils.getClassLoader(Configurations.class)
				.getResourceAsStream(resourceName);
	}
}
