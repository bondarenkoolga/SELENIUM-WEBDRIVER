package com.gmail.page;

//import appmanager.ApplicationMenager;
import org.junit.After;
import org.junit.Before;

public class TestBase {
    protected final ApplicationMenager app = new ApplicationMenager();

    @Before
    public void setUp() throws Exception  {
        app.init();


    }

    @After
    public void tearDown(){
        app.stop();
    }


    public ApplicationMenager getApp() {
        return app;
    }
}
