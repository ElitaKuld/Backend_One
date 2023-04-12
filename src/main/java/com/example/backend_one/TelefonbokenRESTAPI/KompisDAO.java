package com.example.backend_one.TelefonbokenRESTAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KompisDAO {
    public List<Friend> getKompisList() {

        List<Friend> kompisList = new ArrayList<>();

        Friend f1 = new Friend(1,"Cooper", "Star", "07-07-1985", Arrays.asList("762217282", "761234569"), List.of("Interstellar 5"));
        Friend f2 = new Friend(2,"Oliver", "Quick", "08-08-1986", Arrays.asList("762217963", "761234456"), Arrays.asList("London 12", "Bridge 28"));
        Friend f3 = new Friend(3,"Matthew", "Blond", "09-09-1987", List.of("761237282"), List.of("Beach 7"));
        Friend f4 = new Friend(4,"Lilly", "Love", "10-10-1988", Arrays.asList("762126582", "768956569"), Arrays.asList("Woods 5", "Hogwarts 18"));
        Friend f5 = new Friend(5,"Jesse", "Brave", "11-11-1989", Arrays.asList("76456322", "761789659"), Arrays.asList("Lestat 22", "Talamasca 77"));

        kompisList.add(f1);
        kompisList.add(f2);
        kompisList.add(f3);
        kompisList.add(f4);
        kompisList.add(f5);

        return kompisList;

    }
}
