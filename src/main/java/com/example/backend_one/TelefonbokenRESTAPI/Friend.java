package com.example.backend_one.TelefonbokenRESTAPI;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {
    protected int id;
    protected String namn;
    protected String smeknamn; //nick
    protected String birthday;
    protected List<String> telefonnummerLista;
    protected List<String> adressLista;
}