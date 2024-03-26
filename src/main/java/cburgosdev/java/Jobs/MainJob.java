package cburgosdev.java.Jobs;

import cburgosdev.java.Services.IOechsleService;
import cburgosdev.java.Services.IProductRecordService;
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

    @Autowired
    private IOechsleService oechsleService;
    @Autowired
    private IProductRecordService productRecordService;

    //@Scheduled(cron = "0 */5 * ? * *") //Cada 5 min empezando en minutos multiplos de 5
    //@Scheduled(cron = "20 * * * * *") //Cada 20 segundos
    @Scheduled(cron = "0 0 * * * *") //Cada hora
    public void initJobProductsRipley() {
        try(WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            //RIPLEY
            ripleyService.getSmartphones(webClient);
            ripleyService.getToys(webClient);
            ripleyService.getLaptops(webClient);

            System.out.println("==============>>>>>>>>>>> PROCESS FINISHED -- initJobProductsRipley <<<<<<<<<<<==============");

        } catch (IOException e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR CONNECTING THE PAGE");
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS A GENERAL ERROR IN initJobProductsRipley");
        }
    }
    //@Scheduled(cron = "0 */5 * ? * *") //Cada 5 min empezando en minutos multiplos de 5
    //@Scheduled(cron = "20 * * * * *") //Cada 20 segundos
    @Scheduled(cron = "0 0 * * * *") //Cada hora
    public void initJobProductsOechsle() {
        try(WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            //OECHSLE
            oechsleService.getSmartphones(webClient);
            oechsleService.getToys(webClient);

            System.out.println("==============>>>>>>>>>>> PROCESS FINISHED -- initJobProductsOechsle <<<<<<<<<<<==============");

        } catch (IOException e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR CONNECTING THE PAGE");
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS A GENERAL ERROR IN initJobProductsOechsle");
        }
    }
    @Scheduled(cron = "0 0 0 * * ?") //A las 00 horas
    //@Scheduled(cron = "20 * * * * *")
    public void initJobRecords() {
        try{
            productRecordService.findDetailForRecord();

            System.out.println("==============>>>>>>>>>>> PROCESS FINISHED -- initJobRecords<<<<<<<<<<<==============");
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS A GENERAL ERROR IN initJobRecords");
        }
    }
}
