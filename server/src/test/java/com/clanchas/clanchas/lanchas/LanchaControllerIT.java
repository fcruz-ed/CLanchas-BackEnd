package com.clanchas.clanchas.lanchas;

import com.clanchas.clanchas.controller.LanchaController;
import com.clanchas.clanchas.model.Lancha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:db-test.properties", "classpath:test-mysql.sql"})
public class LanchaControllerIT {

    @Autowired
    private LanchaController lanchaController;

    @Test
    public void TestLanchaControllerNotNull() {
        assertNotNull(lanchaController);
    }

    @Test
    public void TestLanchaGetAllWhenStartSizeZero() {
        List<Lancha> lanchas = lanchaController.findAll().getBody();
        assertEquals(0, Objects.requireNonNull(lanchas).size());
    }

}
