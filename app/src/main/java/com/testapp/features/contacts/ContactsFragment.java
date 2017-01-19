package com.testapp.features.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testapp.R;
import com.testapp.tools.fragments.BackStackRootFragment;

/**
 * Created on 17.01.2017.
 */

public class ContactsFragment extends BackStackRootFragment {

    private ContactsController contactsController;
    private ContactsView contactsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.contacts_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.contacts);

        contactsView = new ContactsView(view, getChildFragmentManager());

        contactsController = new ContactsController(contactsView);
        contactsController.bind(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contactsController.unbind();
    }
}
