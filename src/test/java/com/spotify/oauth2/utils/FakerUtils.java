package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    private static Faker faker;

    static {
       faker=new Faker();
    }

	public static String generateName()
	{
		return "Playlist "+faker.regexify("[A-Za-z0-9 ,_-]{10}");
	}
	
	public static String generateDescription()
	{
		return "Description "+faker.regexify("[A-Za-z0-9 ,_@./#&+-]{10}");
	}
}
