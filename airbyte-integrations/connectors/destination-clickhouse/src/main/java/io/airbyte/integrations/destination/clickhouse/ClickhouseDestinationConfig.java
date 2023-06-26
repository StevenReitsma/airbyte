/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.clickhouse;

import com.fasterxml.jackson.databind.JsonNode;

// This configuration class does not include the default JDBC configuration parameters.
public record ClickhouseDestinationConfig(String engine,
                                          Integer ttl_days,
                                          ClickhouseDestinationDeployTypeConfig deploy_config) {

  public final static String DEFAULT_ENGINE = "MergeTree";
  public final static int NO_TTL = 0;

  public static ClickhouseDestinationConfig get(final JsonNode config) {
    return new ClickhouseDestinationConfig(
        config.has("engine") ? config.get("engine").asText() : DEFAULT_ENGINE,
        config.has("ttl_days") ? config.get("ttl_days").asInt() : NO_TTL,
        config.has("deploy_type") ? ClickhouseDestinationDeployTypeConfig.get(config.get("deploy_type"))
            : ClickhouseDestinationDeployTypeConfig.defaultConfig());
  }

}
