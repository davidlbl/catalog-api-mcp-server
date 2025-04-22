package com.inditex.catalog_api_mcp_server.service;

import com.inditex.catalog_api_mcp_server.model.*;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EcommerceService {

    private final List<Product> products;
    private final List<CC> ccs;
    
    private final List<ProductSnowFlake> productsInSnowFlake;
    private final List<CCSnowFlake> ccsInSnowFlake;
    
    public EcommerceService() {
        
        this.products = initProducts();
        this.ccs = initCCs();

        this.productsInSnowFlake = initProductsInSnowFlake();
        this.ccsInSnowFlake = initCCsInSnowFlake();
    }
    
    @Tool(description = "get the product details by product (product identifier) and store (store identifier). This is know as product or product service")
    public Product getProductDetails(@ToolParam(description = "Product identifier, also known as product.") String productId, @ToolParam(description = "Store identifier, also know as store o storeId") String storeId) {
        return products.stream().filter(product -> product.id().equals(productId)).findFirst().orElse(null);
    }
    
    @Tool(description = "get the CCId (Comercial component id) details by CC (comercial component identifier) and store (store identifier). This is know as grid or grids service")
    public CC getCCDetails(@ToolParam(description = "Commercial component identifier, also known as CC, CCId, styling, or product.") String ccId, @ToolParam(description = "Store identifier, also know as store o storeId") String storeId) {
        return ccs.stream().filter(cc -> cc.id().equals(ccId)).findFirst().orElse(null);
    }

    @Tool(description = "get the CC details in snowflake by CCId (Comercial component id) and store (store identifier). This is know as grid or grids service")
    public ProductSnowFlake getCCDetailsInSnowflake(@ToolParam(description = "Commercial component identifier, also known as CC, CCId, styling, or product.") String ccId, @ToolParam(description = "Store identifier, also know as store o storeId") String storeId) {
        return productsInSnowFlake.stream().filter(product -> product.product().id().equals(ccId)).findFirst().orElse(null);
    }

    @Tool(description = "Get the product details in snowflake by product (product identifier) and store (store identifier). This is know as product or product service")
    public CCSnowFlake getProductDetailsInSnowflake(@ToolParam(description = "Product identifier, also known as product.") String productId, @ToolParam(description = "Store identifier, also know as store o storeId") String storeId) {
        return ccsInSnowFlake.stream().filter(product -> product.cc().id().equals(productId)).findFirst().orElse(null);
    }

    private List<Product> initProducts() {
        return List.of(
                new Product("1", "10701", Status.ACTIVE, List.of(new Xmedia("1", "image1", "description1", "url"))),
                new Product("2", "10702", Status.INACTIVE, List.of(new Xmedia("2", "image2", "description2", "url"))),
                new Product("3", "10701", Status.COMING_SOON, List.of(new Xmedia("3", "image3", "description3", "url")))
        );
    }

    private List<CC> initCCs() {
        return List.of(
                new CC("1", "10701", Status.ACTIVE, List.of(new Xmedia("1", "image1", "description1", "url"))),
                new CC("2", "10702", Status.COMING_SOON, List.of(new Xmedia("2", "image2", "description2", "url"))),
                new CC("3", "10701", Status.INACTIVE, List.of(new Xmedia("3", "image3", "description3", "url")))
        );
    }

    private List<CCSnowFlake> initCCsInSnowFlake() {
        return List.of(
                new CCSnowFlake("1", ccs.stream().findAny().get(), Instant.now()),
                new CCSnowFlake("2", ccs.stream().findAny().get(), Instant.now()),
                new CCSnowFlake("3", ccs.stream().findAny().get(), Instant.now())
        );
    }

    private List<ProductSnowFlake> initProductsInSnowFlake() {
        return List.of(
                new ProductSnowFlake("1", products.stream().findAny().get(), Instant.now()),
                new ProductSnowFlake("2", products.stream().findAny().get(), Instant.now()),
                new ProductSnowFlake("3", products.stream().findAny().get(), Instant.now())
        );
    }
}
