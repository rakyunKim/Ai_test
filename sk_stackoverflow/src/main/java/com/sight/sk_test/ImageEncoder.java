package com.sight.sk_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageEncoder {
    public String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        FileInputStream imageInFile = new FileInputStream(file);
        byte[] imageData =  imageInFile.readAllBytes();
        imageInFile.close();

        return Base64.getEncoder().encodeToString(imageData);
    }
}
