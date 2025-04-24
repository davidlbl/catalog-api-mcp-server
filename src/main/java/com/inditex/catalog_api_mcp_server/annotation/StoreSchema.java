package com.inditex.catalog_api_mcp_server.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Schema(allowableValues= {"10701", "10702"})
public @interface StoreSchema {

}
