package rokade.mayur.android.myuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;import rokade.mayur.android.myuser.R;

/**
 * Created by mayur on 10/11/16.
 */

public class EditProfileActivity
        extends AppCompatActivity
        implements NextClickListener {

    ProgressBar progressBar;
    ArrayList<String> fragments = new ArrayList<>();
    public HashMap<String, String> fragNext = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setTitle("Edit User Details");
        progressBar = (ProgressBar) findViewById(R.id.pbRegistrationProgress);

        fragments.add(NameFragment.TAG);
        fragments.add(ContactDetailsFragment.TAG);
        fragments.add(AddressFragment.TAG);
        fragments.add(SpouseDetailsFragment.TAG);
        fragments.add(NeighbourDetailsFragment.TAG);

        buildFragmentRouter();
        startFragments();

    }

    private void startFragments() {
        FragmentManager fm = getSupportFragmentManager();
        NameFragment nameFragment = new NameFragment();
        nameFragment.setNextClickListener(this);
        fm.beginTransaction()
                .replace(R.id.flContainer, nameFragment)
                .addToBackStack(NameFragment.TAG)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        progressBar.setProgress(20);
    }

    private void buildFragmentRouter() {
        for (int i=0; i<fragments.size(); i++) {
            if (i == fragments.size() - 1) {
                fragNext.put(fragments.get(i), null);
            } else {
                fragNext.put(fragments.get(i), fragments.get(i + 1));
            }
        }
    }

    public void goToNextFragment(String curFragmentTag) {
        String nextFragmentTag = fragNext.get(curFragmentTag);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (nextFragmentTag == null){
            return;
        }

        switch (nextFragmentTag) {
            case NameFragment.TAG:
                NameFragment nameFragment = new NameFragment();
                nameFragment.setNextClickListener(this);
                ft.replace(R.id.flContainer, nameFragment)
                .addToBackStack(NameFragment.TAG);
                progressBar.setProgress(20);
                break;
            case AddressFragment.TAG:
                AddressFragment addressFragment = new AddressFragment();
                addressFragment.setNextClickListener(this);
                ft.replace(R.id.flContainer, addressFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(AddressFragment.TAG);
                progressBar.incrementProgressBy(20);
                break;
            case ContactDetailsFragment.TAG:
                ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
                contactDetailsFragment.setNextClickListener(this);
                ft.replace(R.id.flContainer, contactDetailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .addToBackStack(ContactDetailsFragment.TAG);
                progressBar.incrementProgressBy(20);
                break;
            case SpouseDetailsFragment.TAG:
                SpouseDetailsFragment spouseDetailsFragment = new SpouseDetailsFragment();
                spouseDetailsFragment.setNextClickListener(this);
                ft.replace(R.id.flContainer, spouseDetailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_NONE)
                .addToBackStack(SpouseDetailsFragment.TAG);
                progressBar.incrementProgressBy(20);
                break;
            case NeighbourDetailsFragment.TAG:
                NeighbourDetailsFragment neighbourDetailsFragment = new NeighbourDetailsFragment();
                neighbourDetailsFragment.setNextClickListener(this);
                ft.replace(R.id.flContainer, neighbourDetailsFragment)
                .addToBackStack(NeighbourDetailsFragment.TAG);
                progressBar.incrementProgressBy(20);
                break;
            default:
                NameFragment f = new NameFragment();
                f.setNextClickListener(this);
                ft.replace(R.id.flContainer, f)
                .addToBackStack(NameFragment.TAG);
        }

        ft.commit();
        Log.i("EDFRAGCOUNT", Integer.toString(getSupportFragmentManager().getBackStackEntryCount()));
    }

    @Override
    public void fragNextClick(String tag) {
        goToNextFragment(tag);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
        }
    }
}
