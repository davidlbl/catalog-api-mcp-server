package com.inditex.catalog_api_mcp_server.model;

import java.util.List;

public record Product(String id, String storeId, Status status, List<Xmedia> xmedias) {
}
