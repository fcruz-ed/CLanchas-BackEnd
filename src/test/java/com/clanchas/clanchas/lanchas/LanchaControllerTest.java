package com.clanchas.clanchas.lanchas;

import com.clanchas.clanchas.controller.LanchaController;
import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.service.LanchaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:db-test.properties", "classpath:test-mysql.sql"})
public class LanchaControllerTest {

    @MockBean
    private LanchaService lanchaService;

    @Autowired
    private LanchaController lanchaController;

    @Before
    public void init() {
        given(this.lanchaService.findAll()).willReturn(
                Arrays.asList(
                        new Lancha(4, "4", (short) 0),
                        new Lancha(5, "5", (short) 0)
                )
        );
    }

    @Test
    public void TestLanchaControllerNotNull() {
        assertNotNull(lanchaController);
    }

    @Test
    public void TestLanchaGetAll() {
        List<Lancha> lanchas = lanchaController.findAll().getBody();
        assertEquals(4, Objects.requireNonNull(lanchas).get(0).getNumero());
        assertEquals("4", Objects.requireNonNull(lanchas).get(0).getNombre());
        assertEquals(5, Objects.requireNonNull(lanchas).get(1).getNumero());
        assertEquals("5", Objects.requireNonNull(lanchas).get(1).getNombre());
    }



}
