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
    
    @Tool(
            description = """
              Get the details of a product in snowflake using its unique product identifier (productId) and the store identifier (storeId).

              Also known as:
              This method may be referred to as product, product service, product core, product reader or reader.
            """)
    public Product getProductDetails(
            @ToolParam(
                    description = """
                      Identifier of the product, which may also be referred to as product.
                      Rules:
                        - It must follow a numeric format.
                    """
            ) final String productId,
            @ToolParam(
                    description = """
                      Identifier of the store, also known as store or storeId.
                      Rules:
                        - It must follow a numeric format.
                        - The only valid values are: 10701 and 10702.
                        - If no value is provided, the default value will be 10701.
                    """
            ) final String storeId) {
        return products.stream().filter(product -> product.id().equals(productId)).findFirst().orElse(null);
    }
    
    @Tool(
            description = """
             Get the details of a Commercial Component (CC) using its unique identifier (ccId) and the store identifier (storeId).

             Common aliases:
             This method may also be referred to as grid, grids, or gridCore.
            """
    )
    public CC getCCDetails(
            @ToolParam(
                    description = """
                      Identifier of the Commercial Component, which may also be referred to as CC, ccId, styling, or product.
                      Rules:
                        - It must follow a numeric format.
                    """
            ) final String ccId, 
            @ToolParam(
                    description = """
                      Identifier of the store, also known as store or storeId.
                      Rules:
                        - It must follow a numeric format.
                        - The only valid values are: 10701 and 10702.
                        - If no value is provided, the default value will be 10701.
                    """
            ) final String storeId) {
        return ccs.stream().filter(cc -> cc.id().equals(ccId)).findFirst().orElse(null);
    }

    @Tool(
            description = """
             Retrieves the details of a Commercial Component (CC) in snowflake using its unique identifier (ccId) and the store identifier (storeId).

             Common aliases:
             This method may also be referred to as grid, grids, or gridCore.
            """)
    public CCSnowFlake getCCDetailsInSnowflake(
            @ToolParam(
                    description = """
                      Identifier of the Commercial Component, which may also be referred to as CC, ccId, styling, or product.
                      Rules:
                        - It must follow a numeric format.
                    """
            ) final String ccId,
            @ToolParam(
                    description = """
                      Identifier of the store, also known as store or storeId.
                      Rules:
                        - It must follow a numeric format.
                        - The only valid values are: 10701 and 10702.
                        - If no value is provided, the default value will be 10701.
                    """
            ) final String storeId) {
       
        return ccsInSnowFlake.stream().filter(item -> item.cc().id().equals(ccId)).findFirst().orElse(null);
    }

    @Tool(
            description = """
             Get the details of a product in snowflake using its unique product identifier (productId) and the store identifier (storeId).

             Common aliases:
                    This method may be referred to as product, product service, product core, product reader or reader.
            """
    )
    public ProductSnowFlake getProductDetailsInSnowflake(
            @ToolParam(
                    description = """
                      Identifier of the product, which may also be referred to as product.
                      Rules:
                        - It must follow a numeric format.
                    """
            ) final String productId,
            @ToolParam(
                    description = """
                      Identifier of the store, also known as store or storeId.
                      Rules:
                        - It must follow a numeric format.
                        - The only valid values are: 10701 and 10702.
                        - If no value is provided, the default value will be 10701.
                    """
            ) final String storeId) {
        return productsInSnowFlake.stream().filter(item -> item.product().id().equals(productId)).findFirst().orElse(null);
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
