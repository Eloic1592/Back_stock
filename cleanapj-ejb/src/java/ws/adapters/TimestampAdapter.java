package ws.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampAdapter implements JsonSerializer<Timestamp> {
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    @Override
    public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
        /*JsonObject jsonObject = gson.toJsonTree(src).getAsJsonObject();
        jsonObject.addProperty("imageAfficher", Constante.URL_ASSETS_VV + src.getImageAfficher());
        JsonArray array = new JsonArray();
        for(String image:src.tabImage()){
            array.add(new JsonPrimitive(Constante.URL_ASSETS_VV+image));
        }
        jsonObject.add("tabImages",array);
        // Add any additional properties you want to serialize here
        return jsonObject;*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return new JsonPrimitive(dateFormat.format(src));

    }
}
