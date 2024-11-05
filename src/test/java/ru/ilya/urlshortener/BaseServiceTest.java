package ru.ilya.urlshortener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilya.urlshortener.service.BaseService;

@SpringBootTest
public class BaseServiceTest {
    @Autowired
    private BaseService baseService;

    @Test
    public void encodeAndDecodeTest() {
        Assertions.assertEquals(baseService.decode(baseService.encode(1234L)), 1234L);
    }

}
