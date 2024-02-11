import me.gabrielsalvador.utils.Interval;
import me.gabrielsalvador.utils.MusicalNote;
import me.gabrielsalvador.utils.Scale;
import org.junit.jupiter.api.Test;

public class MusicalNoteTests {

    @Test
    public void noteCreationTest(){
        MusicalNote note = new MusicalNote("C1");
        assert note.getPitch() == 24;

        note = new MusicalNote("Cb");
        assert note.getPitch() == 11;

        note = new MusicalNote("C#3");
        assert note.getPitch() == 49;

        note = new MusicalNote("D2");
        assert note.getPitch() == 38;

        note = new MusicalNote("D#2");
        assert note.getPitch() == 39;

        note = new MusicalNote("C-1");
        assert note.getPitch() == 0;
    }


    @Test
    public void test(){

        Interval second = Interval.SECOND.octave(0);
        int semitones = second.toSemitones(Scale.MAJOR);
        assert semitones == 2;


        second = Interval.SECOND.octave(1);
        semitones = second.toSemitones(Scale.MAJOR);
        assert semitones == 14;

        Interval seventh = Interval.SEVENTH.octave(-1);
        semitones = seventh.toSemitones(Scale.MAJOR);
        assert semitones == -1;



    }

    @Test
    public void intervalTest() {
        Interval second = Interval.SECOND.octave(1);
    }
}
