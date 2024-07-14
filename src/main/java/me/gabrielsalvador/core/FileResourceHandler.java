package me.gabrielsalvador.core;

import org.cef.handler.CefResourceHandler;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.cef.callback.CefCallback;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileResourceHandler implements CefResourceHandler {
    private String mimeType;
    private String filePath;
    private InputStream inputStream;

    public FileResourceHandler(String mimeType, String filePath) {
        this.mimeType = mimeType;
        this.filePath = filePath;
    }

    @Override
    public boolean processRequest(CefRequest request, CefCallback callback) {
        try {
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            callback.Continue();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void getResponseHeaders(CefResponse response, org.cef.misc.IntRef response_length, org.cef.misc.StringRef redirectUrl) {
        try {
            response_length.set(inputStream.available());
        } catch (IOException e) {
            response_length.set(0);
        }
        response.setMimeType(mimeType);
        response.setStatus(200);
    }

    @Override
    public boolean readResponse(byte[] data_out, int bytes_to_read, org.cef.misc.IntRef bytes_read, CefCallback callback) {
        try {
            int available = inputStream.available();
            if (available == 0) {
                return false;
            }
            bytes_read.set(inputStream.read(data_out, 0, Math.min(bytes_to_read, available)));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void cancel() {
        try {
            inputStream.close();
        } catch (IOException e) {
            // Handle error
        }
    }
}