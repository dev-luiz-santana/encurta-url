package com.zweihander.encurta_url.service;

import java.util.UUID;

public class Codigos {
    public static String geraCodigo(){
        return UUID.randomUUID().toString().replace("-","").substring(0,16);
    }
}
