package com.example.backend_one.KompisRESTAPI;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Kompis {
    protected int id;
    protected String namn;
    protected String adress;
    protected String telefonnummer;
}
