package com.inditex.catalog_api_mcp_server;


import com.inditex.catalog_api_mcp_server.service.EcommerceService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PCApiMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PCApiMcpServerApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider weatherTools(EcommerceService scheduleService) {
		return MethodToolCallbackProvider.builder().toolObjects(scheduleService).build();
	}
}
