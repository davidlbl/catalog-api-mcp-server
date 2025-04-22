package com.inditex.catalog_api_mcp_server.model;

import java.time.Instant;

public record ProductSnowFlake(String id, Product product, Instant receivedAt) {
}

