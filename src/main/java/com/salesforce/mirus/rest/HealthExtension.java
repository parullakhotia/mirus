package com.salesforce.mirus.rest;

import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.rest.ConnectRestExtension;
import org.apache.kafka.connect.rest.ConnectRestExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HealthExtension implements ConnectRestExtension {

    private Map<String, ?> configs;

    private static final Logger log = LoggerFactory.getLogger(HealthExtension.class);

    @Override
    public void register(ConnectRestExtensionContext restPluginContext) {
        log.info("registering custom endpoint");
        restPluginContext
                .configurable()
                .register(new HealthCheckResource(this.configs, restPluginContext.clusterState()));
    }

    @Override
    public void close() {
        log.info("closing");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("configure extension with configs {}", configs);
        this.configs = configs;
    }

    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }
}
