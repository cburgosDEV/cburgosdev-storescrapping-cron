package cburgosdev.java.Services;

import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

public interface IOechsleService {
    void getSmartphones(WebClient webClient) throws IOException;
    void getToys(WebClient webClient) throws IOException;
}
