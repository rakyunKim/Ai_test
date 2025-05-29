package com.sight.sk_test;

import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowSearchPlugin {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.stackexchange.com/2.3")
            .build();

    @DefineKernelFunction(name = "searchStackOverflow")
    public String searchStackOverflow(@KernelFunctionParameter(name = "query") String query) {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("order", "desc")
                        .queryParam("sort", "relevance")
                        .queryParam("intitle", query)
                        .queryParam("site", "stackoverflow")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
