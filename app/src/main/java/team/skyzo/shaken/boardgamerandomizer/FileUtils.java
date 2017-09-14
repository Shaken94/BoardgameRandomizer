package team.skyzo.shaken.boardgamerandomizer;

import android.util.JsonReader;
import android.util.JsonWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEROYJ on 26/07/2017.
 */

public final class FileUtils {

    public static List<CharacterModel> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readCharacterModelsArray(reader);
        }
    }

    private static List<CharacterModel> readCharacterModelsArray(JsonReader reader) throws IOException {
        List<CharacterModel> characterModels = new ArrayList<>();

//        reader.beginObject();
//        String name = reader.nextName();
//        if (name.equals("characters")) {
            reader.beginArray();
            while (reader.hasNext()) {
                characterModels.add(readCharacterModel(reader));
            }
            reader.endArray();
//        }
        return characterModels;
    }

    private static CharacterModel readCharacterModel(JsonReader reader) throws IOException {
        String nameCharacter = null;
        String gender = null;
        String origin = null;
        String type = null;
        String image = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case Constantes.CHARACTER_NAME:
                    nameCharacter = reader.nextString();
                    break;
                case Constantes.CHARACTER_GENDER:
                    gender = reader.nextString();
                    break;
                case Constantes.CHARACTER_ORIGIN:
                    origin = reader.nextString();
                    break;
                case Constantes.CHARACTER_TYPE:
                    type = reader.nextString();
                    break;
                case Constantes.CHARACTER_IMAGE:
                    image = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new CharacterModel(nameCharacter, gender, origin, type, image);
    }

    public static void writeJsonStream(OutputStream out, List<CharacterModel> characterModels) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writeCharacterModelsArray(writer, characterModels);
        writer.close();
    }

    private static void writeCharacterModelsArray(JsonWriter writer, List<CharacterModel> characterModels) throws IOException {
        writer.beginArray();
        for (CharacterModel characterModel : characterModels) {
            writeCharacterModel(writer, characterModel);
        }
        writer.endArray();
    }

    private static void writeCharacterModel(JsonWriter writer, CharacterModel characterModel) throws IOException {
        writer.beginObject();
        writer.name(Constantes.CHARACTER_NAME).value(characterModel.getName());
        writer.name(Constantes.CHARACTER_GENDER).value(characterModel.getGender());
        writer.name(Constantes.CHARACTER_ORIGIN).value(characterModel.getOrigin());
        writer.name(Constantes.CHARACTER_TYPE).value(characterModel.getType());
        writer.name(Constantes.CHARACTER_IMAGE).value(characterModel.getImage());
        writer.endObject();
    }

    public static int countInJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return getJsonArraySize(reader);
        }
    }

    private static int getJsonArraySize(JsonReader reader) throws IOException {
        int count = 0;

        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals(Constantes.CHARACTER_NAME)) {
                    count++;
                    reader.nextString();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        }
        reader.endArray();
        return count;
    }
}
