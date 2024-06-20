import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MusicalNoteTests {

    @Test
    public void noteCreationTest(){
        MusicalNote note = new MusicalNote("C1");
        assert note.getPitch() == 24;

        String[] letters = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

        int pitch = 0;
        for (int octave = -1; octave < 7; octave++) {
            for (String letter : letters) {
                MusicalNote n = new MusicalNote(letter + octave);
                assertEquals(n.getPitch(), pitch);
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

        m = key.doInterval(-1);
        assert m.getPitch() == 20;

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
    public void intervalBetweenTest() throws Exception {

        MusicalNote note1 = new MusicalNote("C1");
        MusicalNote note2 = new MusicalNote("D1");
        assert Scale.MAJOR.intervalBetween(note1, note2) == 1;

        MusicalNote note3 = new MusicalNote("C1");
        MusicalNote note4 = new MusicalNote("C#1");
        assertThrows(Exception.class, () -> Scale.MAJOR.intervalBetween(note3, note4));

        MusicalNote note5 = new MusicalNote("C1");
        MusicalNote note6 = new MusicalNote("B0");
        assert Scale.MAJOR.intervalBetween(note5, note6) == -1;

        MusicalNote note7 = new MusicalNote("C2");
        MusicalNote note8 = new MusicalNote("C0");
        assert Scale.HIRAJOSHI.intervalBetween(note7, note8) == -10;
    }
}
