package com.sight.sk_test;

import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.FunctionResult;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.InvocationReturnMode;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAIChatService {
    private final Kernel kernel;

    public OpenAIChatService(Kernel kernel) {
        this.kernel = kernel;
    }

    public String ask(String userInput) {
        try {
            ChatCompletionService chat = kernel.getService(ChatCompletionService.class);
            ChatHistory history = new ChatHistory();
            history.addUserMessage(userInput);

            InvocationContext ctx = new InvocationContext.Builder()
                    .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                    .withReturnMode(InvocationReturnMode.LAST_MESSAGE_ONLY)
                    .build();

            List<ChatMessageContent<?>> result = chat
                    .getChatMessageContentsAsync(history, kernel, ctx)
                    .block();

            for (ChatMessageContent<?> msg : result) {
                if (msg.getAuthorRole() == AuthorRole.ASSISTANT && msg.getContent() != null) {
                    System.out.println("=========================");
                    System.out.println("질문 : " + userInput);
                    System.out.println("=========================");
                    System.out.println("=========================");
                    System.out.println("AI 응답: " + msg.getContent());
                    System.out.println("=========================");
                    return msg.getContent();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "오류가 발생했습니다." + e.getMessage();
        }
        return "응답이 생성되지 않았습니다.";
    }
}
