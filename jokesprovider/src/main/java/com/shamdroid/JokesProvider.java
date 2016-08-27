package com.shamdroid;

import java.util.Random;

public class JokesProvider {

    private static String [] jokes = {"Just a joke !" , "Just a joke 2 !" , "Just a joke 3 !" , "Just a joke 4 !" };

    public static String tellMeJoke(){
        int rand = new Random().nextInt(jokes.length);

        return jokes[rand];
    }


}
