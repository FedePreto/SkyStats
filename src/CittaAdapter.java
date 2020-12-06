import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class CittaAdapter  extends TypeAdapter<Citta>{

    @Override
    public Citta read(JsonReader reader) throws IOException {
        Citta citta = new Citta();
        reader.beginObject();
        String field = null;

        while(reader.hasNext()){
            JsonToken token = reader.peek();

            if(token.equals(JsonToken.NAME)){
                field = reader.nextName();
            }
            if("id".equals(field)){
                token = reader.peek();
                citta.setId(Integer.parseInt(reader.nextString()));
            }
            if("name".equals(field)){
                token = reader.peek();
                citta.setNome(reader.nextString());
            }
            if("humidity".equals(field)){
                token = reader.peek();
                citta.setUmidita(Double.parseDouble(reader.nextString()));
            }
            if("pression".equals(field)){
                token = reader.peek();
                citta.setPressione(Double.parseDouble(reader.nextString()));
            }
        }
        reader.endObject();
        return citta;
    }

    @Override
    public void write(JsonWriter writer, Citta citta) throws IOException{
        writer.beginObject();
        writer.name("id");
        writer.value(citta.getId());
        writer.name("name");
        writer.value(citta.getNome());
        writer.name("pression");
        writer.value(citta.getPressione());
        writer.name("humidity");
        writer.value(citta.getUmidita());
        writer.name("temp");
        writer.value(citta.getTemperatura());
        writer.endObject();
    }
}
