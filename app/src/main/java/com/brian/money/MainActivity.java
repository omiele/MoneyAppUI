package com.brian.money;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.android.iconify.Iconify;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  private static final String STATE_RETENTION_FRAGMENT = "STATE_RETENTION_FRAGMENT";
  private DrawerLayout drawer_layout;
  TabLayout tabLayout;
  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
   * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
   * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best to
   * switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  private SectionsPagerAdapter mSectionsPagerAdapter;
  Context c;

  /**
   * The {@link android.support.v4.view.ViewPager} that will host the section contents.
   */
  private ViewPager mViewPager;
  private NavigationView navigationView;
  private StateRetentionFragment stateRetentionFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    c = this;
//    Iconify
//            .with(new FontAwesomeModule());

    android.app.FragmentManager fm = getFragmentManager();
    stateRetentionFragment = (StateRetentionFragment) fm.findFragmentByTag(STATE_RETENTION_FRAGMENT);

    // If the Fragment is non-null, then it is currently being
    // retained across a configuration change.
    if (stateRetentionFragment == null) {
      stateRetentionFragment = new StateRetentionFragment();
      fm.beginTransaction().add(stateRetentionFragment, STATE_RETENTION_FRAGMENT).commit();
    }

    setContentView(R.layout.activity_main);
    c = this;
    ActionBar bar = getSupportActionBar();
    bar.hide();

    final Toolbar toolbar = (Toolbar) findViewById(R.id.mainActivityBar);
        /*final Toolbar tabBar =(Toolbar) findViewById(R.id.tabBar);*/
//    setSupportActionBar(toolbar);

    drawer_layout = (DrawerLayout) findViewById(R.id.main_drawer_layout);


    tabLayout = (TabLayout) findViewById(R.id.tabs);


    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);

    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
      public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // When the tab is selected, switch to the
        // corresponding page in the ViewPager.


        // mViewPager.setCurrentItem(tab.getPosition());


      }

      public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // hide the given tab


      }

      public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // probably ignore this event

      }
    };

    mViewPager.setAdapter(mSectionsPagerAdapter);
    mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.setupWithViewPager(mViewPager);

    //Initializing NavigationView
    navigationView = (NavigationView) findViewById(R.id.main_navigation_view);
    int[][] states = new int[][]{
            new int[]{android.R.attr.state_selected}, // selected
            new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},  // pressed
            new int[]{}, // default
    };


    int[] colors = new int[]{
            Color.BLACK,
            Color.WHITE,
            Color.WHITE

    };

    ColorStateList state = new ColorStateList(states, colors);
    navigationView.setItemTextColor(state);

    TextView userName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username);
    userName.setText("Okoth Omachi");


    // Initializing Drawer Layout and ActionBarToggle

    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name) {

      @Override
      public void onDrawerClosed(View drawerView) {
        // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
        super.onDrawerClosed(drawerView);
      }

      @Override
      public void onDrawerOpened(View drawerView) {
        // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

        super.onDrawerOpened(drawerView);
      }
    };

    //Setting the actionbarToggle to drawer layout
    drawer_layout.setDrawerListener(actionBarDrawerToggle);

    //calling sync state is necessay or else your hamburger icon wont show up
    actionBarDrawerToggle.syncState();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.brakas_menu, menu);
    for (int m = 0; m < menu.size(); m++) {
      SpannableString s = new SpannableString(menu.getItem(m).getTitle());
      s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, s.length(), 0);
      menu.getItem(m).setTitle(s);
    }

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  private void d(String s) {
    try {
      Log.d("MainActivity", s);
    } catch (NullPointerException ex) {
      Log.d("MainActivity", "msg is null");

    }
  }

  /**
   * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fm;

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
      this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.

      d("position clicked is? " + position);
      switch (position) {

        case 0:
          HomeFragment homeFragment = HomeFragment.newInstance();
          return homeFragment;

        case 1:
          UserFragment userFragment = UserFragment.newInstance();
          return userFragment;

        case 2:
          ChatFragment chatFragment = ChatFragment.newInstance();
          return chatFragment;

        case 3:
          TransactionFragment transactionFragment = TransactionFragment.newInstance();
          return transactionFragment;
        case 4:
          BalanceFragment balanceFragment = BalanceFragment.newInstance();
          return balanceFragment;
      }
      return null;
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 5;
    }

    Drawable myDrawable;

    @Override
    public CharSequence getPageTitle(int position) {
      Locale l = Locale.getDefault();
      String title = "";
      switch (position) {
        case 0:
          title = "";
          myDrawable = getResources().getDrawable(R.mipmap.ic_home_black_36dp);
          break;
        case 1:
          title = "";
          myDrawable = getResources().getDrawable(R.mipmap.ic_people_black_36dp);
          break;
        case 2:
          title = "";
          myDrawable = getResources().getDrawable(R.mipmap.ic_chat_bubble_black_36dp);
          break;
        case 3:
          title = "";
          myDrawable = getResources().getDrawable(R.mipmap.ic_swap_horiz_black_36dp);
          break;
        case 4:
          title = "";
          myDrawable = getResources().getDrawable(R.mipmap.ic_account_balance_wallet_black_36dp);
          break;
        default:
          break;
      }
      SpannableStringBuilder sb = new SpannableStringBuilder(" " + title); // space added before text for convenience
      try {
        myDrawable.setBounds(5, 5, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      } catch (Exception e) {
        // TODO: handle exception
      }
      return sb;
    }
  }
}

