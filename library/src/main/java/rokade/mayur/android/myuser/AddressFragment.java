package rokade.mayur.android.myuser;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;import rokade.mayur.android.myuser.R;

/**
 * Created by mayur on 10/11/16.
 */

public class AddressFragment extends Fragment {
    public static final String TAG  = "AddressFragment";
    private NextClickListener mNextClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_address, container, false);
    }

    public void setNextClickListener(Activity activity) {
        mNextClickListener = (NextClickListener) activity;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnNext = (Button) view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNextClickListener.fragNextClick(TAG);
            }
        });
    }
}
