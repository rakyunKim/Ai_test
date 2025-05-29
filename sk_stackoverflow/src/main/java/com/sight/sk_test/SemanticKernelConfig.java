package com.sight.sk_test;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.semanticfunctions.KernelPromptTemplateFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SemanticKernelConfig {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Bean
    public Kernel semanticKernel() {
        AzureKeyCredential openAIClientCredentials = new AzureKeyCredential(openaiApiKey);

        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .credential(openAIClientCredentials)
                .buildAsyncClient();

        ChatCompletionService openAIChatCompletion = OpenAIChatCompletion.builder()
                .withOpenAIAsyncClient(client)
                .withModelId("gpt-4")
                .build();

        KernelPlugin plugin = KernelPluginFactory.createFromObject(new StackOverflowSearchPlugin(), "StackOverflowPlugin");

        return Kernel.builder()
                .withAIService(ChatCompletionService.class, openAIChatCompletion)
                .withPlugin(plugin)
                .build();
    }

    @Bean
    public KernelPromptTemplateFactory kernelPromptTemplateFactory() {
        return new KernelPromptTemplateFactory();
    }
}
