package com.example.backend_one.TelefonbokenRESTAPI;

import com.example.backend_one.BookRESTAPI.Book;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/friends/{id}/delete")
    public List<Friend> deleteFriendByID(@PathVariable int id) {
        //removeIf kan man använda direkt på listor för att ändra dem
        kompisList.removeIf(k -> k.getId() == id);
        return kompisList;
    }

    //curl http://localhost:8080/friends/add -H "Content-Type:application/json" -d "{\"id\":6, \"namn\":\"Robin\", \"smeknamn\":\"Hood\", \"birthday\":\"12-12-1990\", \"telefonnummerLista\": [\"762654897\", \"762365894\"], \"adressLista\": [\"Sherwood Forest 10\"]}" -v
    @PostMapping("/friends/add")
    public List<Friend> addFriendByPOST(@RequestBody Friend f) {
        kompisList.add(f);
        return kompisList;
    }

    //curl http://localhost:8080/friends/update -H "Content-Type:application/json" -d "{\"id\":7, \"namn\":\"Marius\", \"smeknamn\":\"Violin\", \"birthday\":\"01-01-1225\", \"telefonnummerLista\": [\"765623896\", \"762214853\"], \"adressLista\": [\"Mediterranean Island 100\"]}" -v
    @PutMapping("/books/update")
    public List<Friend> updateFriend(@RequestBody Friend f) {
        Friend friendToUpdate = kompisList.stream()
                .filter(friend -> friend.getId() == f.getId()).findFirst().orElse(null);
        if (friendToUpdate == null) {
            kompisList.add(f);
        } else {
            friendToUpdate.setNamn(f.getNamn());
            friendToUpdate.setSmeknamn(f.getSmeknamn());
            friendToUpdate.setBirthday(f.getBirthday());
            friendToUpdate.setTelefonnummerLista(f.getTelefonnummerLista());
            friendToUpdate.setAdressLista(f.getAdressLista());
        }
        return kompisList;
    }
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

/*
Uppgift 5 – Telefonboken som Web Service (radera kompis)
• Implementera deleteFriendByID i din applikation
• http://localhost:8080/kompis/5/delete
• Tar bort kompis med id=5
• Testa att angiven kompis verkligen tas bort
 */

/*
Uppgift 6 – Telefonboken som Web Service (lägg till kompis, post)
• Installera Postman (eller använd dig av curl-kommandot)
• Gör en POST-funktion till din KompisController för att lägga till fler vänner
• Kolla med curl att allt funkar som det ska
 */

/*
Uppgift 7 – Telefonboken som Web Service (update kompis, post)
• Skriv en ”update”-metod.
• Den ska ta emot ett Kompis-objekt.
• Om en Kompis med samma id som inparameterns id finns ska den befintliga Kompisen
uppdateras.
• Om inget objekt med samma id finns ska en ny Kompis skapas och läggas till i listan.
• Kolla att det funkar bra att posta in nya kompisar med Postman eller med curl
• Urlen kan vara …/kompis/update
 */
