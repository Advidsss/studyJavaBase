package testHelloWorld;

import org.junit.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by WangDi on 2017/7/26.
 */
public class HelloWorld {

    @Test
    public void run(){

        List list = mock(List.class);
        list.add(1);
        list.clear();

        verify(list).add(1);
        verify(list).clear();
    }

    @Test
    public void when_throw() throws IOException {

        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        //预设流抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }
}
