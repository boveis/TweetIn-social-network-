package controller;

import context.Context;

import java.security.PublicKey;

public class IdController {


    public int nextId(String string){
          return Context.getId(string);
    }

}
