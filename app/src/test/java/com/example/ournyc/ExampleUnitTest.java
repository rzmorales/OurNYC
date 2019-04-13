package com.example.ournyc;

import com.example.ournyc.data.model.ProgramModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private ProgramModel opportunityProgram;
    private ProgramModel NYCProgram;
    private ProgramModel appleProgram;
    private ProgramModel myProgram;
    List<ProgramModel> programModelList = new ArrayList<>();

    MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {

        opportunityProgram = new ProgramModel();
        opportunityProgram.setProgram_name("opportunityProgram");
        opportunityProgram.setDate("2019-01-09T08:18:10.000");
        programModelList.add(opportunityProgram);

        NYCProgram = new ProgramModel();
        NYCProgram.setProgram_name("NYCProgram");
        NYCProgram.setDate("2019-02-09T08:18:10.000");
        programModelList.add(NYCProgram);

        appleProgram = new ProgramModel();
        appleProgram.setProgram_name("appleProgram");
        appleProgram.setDate("2019-01-10T08:18:10.000");
        programModelList.add(appleProgram);

        myProgram = new ProgramModel();
        myProgram.setProgram_name("myProgram");
        myProgram.setDate("2018-01-09T08:18:10.000");
        programModelList.add(myProgram);


        mainActivity = new MainActivity();

    }

    @Test
    public void check_sortByDate() {

        String expectedFirstOutput = "NYCProgram";
        mainActivity.sortByDate(programModelList);

        assertEquals(programModelList.get(0).getProgram_name(), expectedFirstOutput);

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}