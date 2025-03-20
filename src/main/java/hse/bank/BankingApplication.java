package hse.bank;

import hse.bank.facades.BankFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BankingApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BankingApplication.class, args);
        BankFacade mainFacade = context.getBean(BankFacade.class);

    }
}