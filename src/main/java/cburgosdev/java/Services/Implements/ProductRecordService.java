package cburgosdev.java.Services.Implements;

import cburgosdev.java.Models.ProductRecord;
import cburgosdev.java.Repositories.IProductDetailRepository;
import cburgosdev.java.Repositories.IProductRecordRepository;
import cburgosdev.java.Services.IProductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ProductRecordService implements IProductRecordService {
    @Autowired
    private IProductDetailRepository productDetailRepository;
    @Autowired
    private IProductRecordRepository productRecordRepository;
    @Override
    public void findDetailForRecord() {
        System.out.println("==============>>>>>>>>>>> INIT METHOD findDetailForRecord()  <<<<<<<<<<<==============");

        try {
            LocalDate today = LocalDate.now();
            LocalDate dateToSearch = today.minusDays(1);

            List<Object[]> records = productDetailRepository.findDetailForRecord(dateToSearch);

            for(Object[] record : records) {
                ProductRecord productRecord = new ProductRecord();
                productRecord.setProductId((int) record[0]);
                productRecord.setPrice((Double) record[1]);
                productRecord.setDate((Date) record[2]);
                System.out.println("=>>>>>>>>>>> PRODUCT TO SAVE: " + productRecord);
                productRecordRepository.save(productRecord);
            }
        } catch (Exception e) {
            System.out.println("=>>>>>>>>>>> THERE WAS AN ERROR ON RECORDS");
        }
    }
}
