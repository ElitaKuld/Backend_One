package com.example.backend_one.TelefonbokenRESTAPI;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class FriendHATEOASController {

    KompisDAO kompisDao = new KompisDAO();
    List<Friend> kompisList = kompisDao.getKompisList();


    @GetMapping("/friendsHATEOAS/{id}")
    EntityModel<Friend> one(@PathVariable Long id) {

        Friend friend = kompisList.stream().filter(k -> k.getId() == id).findFirst().orElse(null);

        return EntityModel.of(friend,
                linkTo(methodOn(FriendHATEOASController.class).one(id)).withSelfRel(),
                linkTo(methodOn(FriendController.class).getAllFriends()).withRel("friends"));
    }

    @GetMapping("/friendsHATEOAS")
    CollectionModel<EntityModel<Friend>> all() {

        List<EntityModel<Friend>> friends = kompisList.stream()
                .map(friend -> EntityModel.of(friend,
                        linkTo(methodOn(FriendHATEOASController.class).one((long) friend.getId())).withSelfRel(),
                        linkTo(methodOn(FriendHATEOASController.class).all()).withRel("friends"))).toList();

        return CollectionModel.of(friends, linkTo(methodOn(FriendHATEOASController.class).all()).withSelfRel());
    }

    //skapa kompis:
    //curl localhost:8080/friendsHATEOAS/add -H "Content-Type:application/json" -d "{\"id\":6, \"namn\":\"Robin\", \"smeknamn\":\"Hood\", \"birthday\":\"12-12-1990\", \"telefonnummerLista\": [\"762654897\", \"762365894\"], \"adressLista\": [\"Sherwood Forest 10\"]}" -v
    @PostMapping("/friendsHATEOAS/add")
    ResponseEntity<?> newFriend(@RequestBody Friend newFriend) {

        EntityModel<Friend> entityModel = EntityModel.of(newFriend,
                linkTo(methodOn(FriendHATEOASController.class).one((long) newFriend.getId())).withSelfRel(),
                linkTo(methodOn(FriendHATEOASController.class).all()).withRel("employees"));

        kompisList.add(newFriend);

        return ResponseEntity //visar den skapade vännen
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    //uppdatera kompis:
    //curl -X PUT http://localhost:8080/friendsHATEOAS/update -H "Content-Type:application/json" -d "{\"id\":6, \"namn\":\"Samwise\", \"smeknamn\":\"Gamgee\", \"birthday\":\"01-01-2001\", \"telefonnummerLista\": [\"762654897\", \"762365894\"], \"adressLista\": [\"The Shire\"]}" -v
    @PutMapping("/friendsHATEOAS/update")
    ResponseEntity<?> updateFriend(@RequestBody Friend newFriend) {

        Friend friendToUpdate = kompisList.stream()
                .filter(friend -> friend.getId() == newFriend.getId()).findFirst().orElse(null);
        if (friendToUpdate == null) {
            kompisList.add(newFriend);
        } else {
            friendToUpdate.setNamn(newFriend.getNamn());
            friendToUpdate.setSmeknamn(newFriend.getSmeknamn());
            friendToUpdate.setBirthday(newFriend.getBirthday());
            friendToUpdate.setTelefonnummerLista(newFriend.getTelefonnummerLista());
            friendToUpdate.setAdressLista(newFriend.getAdressLista());
        }

        EntityModel<Friend> entityModel = EntityModel.of(friendToUpdate,
                linkTo(methodOn(FriendHATEOASController.class).one((long) friendToUpdate.getId())).withSelfRel(),
                linkTo(methodOn(FriendHATEOASController.class).all()).withRel("employees"));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    //ta bort kompis:
    //curl -v -X DELETE localhost:8080/friendsHATEOAS/6/delete
    @DeleteMapping("/friendsHATEOAS/{id}/delete")
    ResponseEntity<?> deleteFriend(@PathVariable Long id) {
        kompisList.removeIf(k -> k.getId() == id);
        return ResponseEntity.noContent().build();
    }
}

/*
Uppgift 8 – Implementera HATEOAS i din Kompis-service
• Lägg till funktionalitet så att du får länkar i dina response-objekt
• Länka till varje enskilt objekt och till ”alla objekt”
• Du kan läsa mer om detta på https://spring.io/guides/tutorials/rest/
• Notera dock att det system som byggs i tutorialen går mot en databas, vilket vi inte
gått igenom än, så ignorera datahämtningen, i tutorialen, för nu och koncentrera dig på
HATEOAS:en i denna uppgift
• I mån av tid, implementera länkar till de övriga tjänsterna vi har i vårt REST-API (skapa
kompis, uppdatera kompis och ta bort kompis
 */