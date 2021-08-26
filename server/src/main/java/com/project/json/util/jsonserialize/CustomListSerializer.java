package com.project.json.util.jsonserialize;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomListSerializer<T> extends StdSerializer<List<T>> {

    public CustomListSerializer(){
        this(null);
    }

    protected CustomListSerializer(Class<List<T>> t) {
        super(t);
    }

    @Override
    public void serialize(List<T> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if(value.size() == 1){
            if(value.get(0) instanceof String)
            gen.writeString((String) value.get(0));
            else gen.writeBoolean((Boolean) value.get(0));
        }else{
            gen.writeStartArray();
            value.forEach(v -> {
                try {
                    if(v instanceof String)
                    gen.writeString((String) v);
                    else
                    gen.writeBoolean((Boolean) v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            gen.writeEndArray();
        }
    }

}
