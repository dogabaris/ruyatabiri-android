package com.bigapps.ruyatabirleri;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by shadyfade on 14.09.2016.
 */
public class UnixTimeConverter implements JsonDeserializer {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
        JsonParseException {
            String s = json.getAsJsonPrimitive().getAsString();
            long l = Long.parseLong(s);
            Date d = new Date(l);
            return d;
        }

}
