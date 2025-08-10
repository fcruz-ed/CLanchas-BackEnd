package com.clanchas.clanchas;

import com.clanchas.clanchas.repository.LanchaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest
class ClanchasApplicationTests {

    @Autowired
    private LanchaRepository lanchaRepository;

    @Test
    void testFindAllLanchas() throws Exception {
        lanchaRepository.findAll();
    }

}
