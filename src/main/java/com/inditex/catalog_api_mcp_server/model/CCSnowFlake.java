package com.inditex.catalog_api_mcp_server.model;

import java.time.Instant;

public record CCSnowFlake(String id, CC cc, Instant receivedAt) {
}

