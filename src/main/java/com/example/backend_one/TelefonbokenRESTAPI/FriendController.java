package com.example.backend_one.TelefonbokenRESTAPI;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class FriendController {
    KompisDAO kompisDao = new KompisDAO();
    List<Friend> kompisList = kompisDao.getKompisList();

    @RequestMapping("/friends")
    public List<Friend> getAllFriends() {
        return kompisList;
    }

    @RequestMapping("/friends/{smeknamn}")
    public Friend getFriendByNick(@PathVariable String smeknamn) {
        return kompisList.stream().filter(ko -> Objects.equals(ko.getSmeknamn(), smeknamn)).findFirst().orElse(null);
    }

    // Kan inte ha båda två metoderna samtidigt
/*
    @RequestMapping("/friends/{id}")
    public Friend getFriendById(@PathVariable int id) {
        return kompisList.stream().filter(kom -> kom.getId() == id).findFirst().orElse(null);
    }*/

}

/*
Uppgift 3 – Telefonboken som Web Service (lista alla kompisar)
• Gör en web service där du kan lista dina vänners namn och telefonnummer på JSON
• Dina vänner ska lagras i en KompisDAO-klass
• Det ska gå att anropa web servicen från en webbläsare och alla vänners namn och
telefonnummer ska skrivas ut på JSON
• För er som blir klara snabbt, bygg ut Kompis-modellen till att även innehålla nick, kunna ha
flera telefonnummer, ha en födelsedag och kunna ha flera adresser.
 */

/*
Uppgift 4 – Telefonboken som Web Service (slå upp kompis)
• Lägg till en funktion getFriendByID som tar en int som inparameter och skriver ut datat för
den av dina vänner som har det id:t
• Ex: Http://localhost:8080/friend/5
• Ska returnera kompisen med id=5
• Om du inte har instansvariabeln id sedan innan i din Kompis-klass, lägg till den nu
• I mån av tid, gör en getFriendByName som tar ett namn, säker igenom Kompis-listan och
skriver ut alla uppgifter för den Kompis som har angivet namn
 */
