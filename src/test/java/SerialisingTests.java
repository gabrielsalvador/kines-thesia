import controlP5.ControlP5;
import me.gabrielsalvador.core.AppState;
import me.gabrielsalvador.core.Sinesthesia;
import me.gabrielsalvador.pobject.PMetronome;
import me.gabrielsalvador.pobject.PObject;
import me.gabrielsalvador.sequencing.SequencerController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.io.*;

import static org.mockito.Mockito.mock;

public class SerialisingTests {

    static AppState appState;
    static ControlP5 mockCp5;

    @BeforeAll
    public static void setup() {
        appState = AppState.getInstance();
        mockCp5 = mock(ControlP5.class);
        PApplet applet  = Sinesthesia.getInstance();
    }


    @Test
    public void SerialisedAndDeserialisedObjectsAreEqual() {

        SequencerController sequencerController = new SequencerController(Sinesthesia.getInstance().getCP5(), "SequencerController");
        appState.addDevice(sequencerController);

        //serialise
        try (FileOutputStream fileOut = new FileOutputStream("appState-test.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(AppState.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //deserialise
        AppState deserialisedAppState = null;
        try (FileInputStream fileIn = new FileInputStream("appState-test.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            deserialisedAppState = (AppState) in.readObject();
            AppState myState = AppState.getInstance();

            for (PObject pObject : deserialisedAppState.getPObjects()) {
                myState.addPObject(pObject);
            }
            myState.loadSequencerState(deserialisedAppState.getSequencerState());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        assert appState.getPObjects().size() == deserialisedAppState.getPObjects().size();


    }
}
