package cburgosdev.java.Jobs;

import cburgosdev.java.Services.IRipleyService;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class MainJob {
    @Autowired
    private IRipleyService ripleyService;

    @Scheduled(cron = "0 */5 * ? * *")
    //@Scheduled(cron = "20 * * * * *")
    public void initJob() {
        try(WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            ripleyService.getCellphones(webClient);
        } catch (IOException e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR CONNECTING THE PAGE");
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS A GENERAL ERROR");
        }
    }
}
