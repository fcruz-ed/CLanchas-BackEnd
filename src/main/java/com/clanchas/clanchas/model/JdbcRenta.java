package com.clanchas.clanchas.model;

import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.model.Renta;

public class JdbcRenta extends Renta {
    private Lancha lancha;

    public Lancha getLancha() {
        return lancha;
    }

    public void setLancha(Lancha lancha) {
        this.lancha = lancha;
    }
}
