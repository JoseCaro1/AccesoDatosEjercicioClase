package ejercicio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {

    protected MyObjectOutputStream() throws IOException, SecurityException {
        super();
        // TODO Auto-generated constructor stub
    }

    protected MyObjectOutputStream(OutputStream out) throws IOException, SecurityException {
        super(out);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void writeStreamHeader() throws IOException {

    }
}
