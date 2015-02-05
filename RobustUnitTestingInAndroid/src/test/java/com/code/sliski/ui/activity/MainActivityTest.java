package com.code.sliski.ui.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.code.sliski.ui.R;
import com.code.sliski.ui.fragment.UserIdFragment;
import com.code.sliski.ui.fragment.UserInfoFragment;
import com.code.sliski.ui.preference.PrefManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private PrefManager mPrefManager;

    @Before
    public void setUp() throws Exception {
        Context mApplicationContext = Robolectric.application.getApplicationContext();
        mPrefManager = new PrefManager(mApplicationContext.getSharedPreferences(mApplicationContext.getString(R.string.preferences), Context.MODE_PRIVATE));
    }

    @Test
    public void activityShouldAddUserIdFragment() throws Exception {
        mPrefManager.userId().remove().commit();

        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
        Fragment fragmentById = mainActivity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertTrue(
                "added fragment should be instance of UserIdFragment, but it does not",
                fragmentById instanceof UserIdFragment
        );
    }

    @Test
    public void activityShouldAddUserInfoFragment() throws Exception {
        mPrefManager.userId().put(123l).commit();

        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().visible().get();
        Fragment fragmentById = mainActivity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertTrue(
                "added fragment should be instance of UserInfoFragment, but it does not",
                fragmentById instanceof UserInfoFragment
        );
    }
}