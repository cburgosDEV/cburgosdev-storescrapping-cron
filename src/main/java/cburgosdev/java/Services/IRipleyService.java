package cburgosdev.java.Services;

import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

public interface IRipleyService {
    void getSmartphones(WebClient webClient) throws IOException;
    void getToys(WebClient webClient) throws IOException;
    void getLaptops(WebClient webClient) throws IOException;
}
