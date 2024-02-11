import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import org.junit.jupiter.api.Test;

public class MusicalNoteTests {

    @Test
    public void noteCreationTest(){
        MusicalNote note = new MusicalNote("C1");
        assert note.getPitch() == 24;

        String[] letters = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

        int pitch = 0;
        for (int octave = -1; octave < 7; octave++) {
            for (String letter : letters) {
                MusicalNote n = new MusicalNote(letter + octave);
                assert n.getPitch() == pitch;
                pitch++;
            }
        }




    }


    @Test
    public void testIntervalCreation(){



        MusicalNote root = new MusicalNote("A");
        assert root.getPitch() == 21;


        Scale key = Scale.MAJOR.setRoot(root);
        MusicalNote m = key.doInterval(0);
        assert m.getPitch() == 21;

        m = key.doInterval(1);
        assert m.getPitch() == 23;

        m = key.doInterval(2);
        assert m.getPitch() == 25;
        m = key.doInterval(3);
        assert m.getPitch() == 26;





        int[] chords = new int[]{0, 2, 4};

        // IV of A0 is D - F# - A
        assert key.doInterval(3 + chords[0]).getPitch() == 26;
        assert key.doInterval(3 + chords[1]).getPitch() == 30;
        assert key.doInterval(3 + chords[2]).getPitch() == 33;



    }

    @Test
    public void intervalTest() {

    }
}
