package me.gabrielsalvador.core;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefCallback;
import org.cef.handler.*;
import org.cef.misc.BoolRef;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.cef.network.CefURLRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomResourceHandler implements CefResourceRequestHandler {
    @Override
    public CefCookieAccessFilter getCookieAccessFilter(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest) {
        return null;
    }

    @Override
    public boolean onBeforeResourceLoad(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest) {
        return false;
    }

    @Override
    public CefResourceHandler getResourceHandler(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest) {
        String url = cefRequest.getURL();

        if(url.startsWith("devtools")) return null;


        if (url.endsWith("index.html/")) {
            Path path = Paths.get("src/main/frontend/dist/index.html").toAbsolutePath();
            return new FileResourceHandler("text/html", path.toString());
        } else if (url.endsWith(".js")) {

            String filename = url.substring(url.lastIndexOf("/"));

            Path path = Paths.get("src/main/frontend/dist" + filename).toAbsolutePath();

            return new FileResourceHandler("application/javascript", path.toString());
        }
        return null;
    }

    @Override
    public void onResourceRedirect(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, CefResponse cefResponse, StringRef stringRef) {

    }

    @Override
    public boolean onResourceResponse(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, CefResponse cefResponse) {
        return false;
    }

    @Override
    public void onResourceLoadComplete(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, CefResponse cefResponse, CefURLRequest.Status status, long l) {

    }

    @Override
    public void onProtocolExecution(CefBrowser cefBrowser, CefFrame cefFrame, CefRequest cefRequest, BoolRef boolRef) {

    }
}