package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application>{


    public ApplicationTest() {
        super(Application.class);
    }

    public void testTask() throws Exception {
        String result = new MyAsyncTask().execute(getApplication()).get();
        assertTrue(!result.isEmpty());

    }
}