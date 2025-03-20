package hse.bank.services;

import hse.bank.cmd.CmdResult;
import hse.bank.enums.IOFormat;
import hse.bank.facades.BankFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IOTest {
    @Autowired
    BankFacade facade;

    @BeforeEach
    void setUp() {
        File f = new File("./test.csv");
        if (f.isFile()) {
            f.delete();
        }
    }

    @Test
    void exports() {
        facade.flush();
        facade.createAccount("Test", 100.0);
        facade.createCategory("Test cat", true);
        facade.createOperation(20.0, 0, false, 0);
        facade.exportData("./test.csv", IOFormat.CSV);
        Assertions.assertDoesNotThrow(() -> {
            File f = new File("./test.csv");
            assertTrue(f.isFile());
        });
    }

    @Test
    void imports() {
        exports();
        facade.importData("./test.csv", IOFormat.CSV);
        assertEquals(facade.getAccount(0).getBalance(), 120.0);
    }

}
