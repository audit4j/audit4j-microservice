package org.audit4j.microservice;

import java.io.InputStream;

import org.audit4j.core.ConfigProvider;
import org.audit4j.core.Configurations;
import org.audit4j.core.YAMLConfigProvider;
import org.audit4j.core.exception.ConfigurationException;
import org.audit4j.core.util.ClassLoaderUtils;

public class ConfigurationManager {

	private ConfigProvider<ServerConfiguration> configProvider;

	private static final String DEFAULT_CONFIG_FILE = "server.config.yaml";

	public ConfigurationManager() {
		configProvider = new YAMLConfigProvider<>(ServerConfiguration.class);
	}

	ServerConfiguration loadConfiguration() throws ConfigurationException {
		ServerConfiguration config = null;
		if (getClasspathResourceAsStream(DEFAULT_CONFIG_FILE) != null) {
			config = configProvider
					.readConfig(getClasspathResourceAsStream(DEFAULT_CONFIG_FILE));
		}
		return config;
	}

	static InputStream getClasspathResourceAsStream(String resourceName) {
		return ClassLoaderUtils.getClassLoader(Configurations.class)
				.getResourceAsStream(resourceName);
	}
}
