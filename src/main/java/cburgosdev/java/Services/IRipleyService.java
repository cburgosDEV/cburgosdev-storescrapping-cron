package cburgosdev.java.Services;

import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

public interface IRipleyService {
    void getCellphones(WebClient webClient) throws IOException;
}
