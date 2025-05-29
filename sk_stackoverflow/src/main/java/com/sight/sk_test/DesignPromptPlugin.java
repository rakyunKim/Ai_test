package com.sight.sk_test;

import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;

public class DesignPromptPlugin {
//    private static final String BASE64_IMAGE;
//    private static final ImageEncoder imageEncoder = new ImageEncoder();
//
//    static {
//        try {
//            BASE64_IMAGE = imageEncoder.encodeImageToBase64("/Users/rock/Desktop/Programming/Project/sk_test/src/main/resources/mobile_card.png");
//        } catch (IOException e) {
//            throw new RuntimeException("Base64 인코딩 실패", e);
//        }
//    }


    @DefineKernelFunction(name = "designCard")
    public String designCard(@KernelFunctionParameter(name = "description") String description) {
        return """
                User Requirement:
                %s

                You must generate the design as an image and return it encoded in base64 format.

                ⚠️ Important:
                - Do **not** include any explanation, comment, or additional text.
                - Only return the **base64-encoded image string**.
                - If you are **unable to generate an image**, just reply with a clear message saying that you can't generate one.
                - always using korean.
                """.formatted(description);
    }
}
