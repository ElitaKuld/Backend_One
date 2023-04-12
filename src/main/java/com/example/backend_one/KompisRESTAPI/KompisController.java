package com.example.backend_one.KompisRESTAPI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KompisController {
    Kompis kompis = new Kompis(1, "Jane", "Wuthering Heights", "761234567");

    @RequestMapping("/kompis")
    public Kompis getKompis() {
        return kompis;
    }
}

/*
Gör POJO-klassen Kompis.java
• En kompis har:
• Id
• Namn
• Adress
• Telefonnummer
• Gör en metod som returnerar ett objekt av typen Kompis med JSON (objektet kan vara
hårdkodat nu, vi ska jobba vidare med det sen, detta är bara för att se att det funkar att
returnera ett objekt)
• http://localhost:8080/kompis
• Kolla att kompisen visas fint i din browser
• Använd gärna Lombok
 */
