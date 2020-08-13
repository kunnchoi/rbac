package com.android.rbac.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.rbac.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private EditText edtUserId, etRoleId, etUserName, etUserNameToSearch;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Button btnListUsers = getView().findViewById(R.id.btnListUsers);
        Button btnDeleteUser = getView().findViewById(R.id.btnDeleteUser);
        Button btnAddUser = getView().findViewById(R.id.btnAddUser);
        Button btnSearchUser = getView().findViewById(R.id.btnSearchUser);
        Button btnRoleWithPermissions = getView().findViewById(R.id.btnRoleWithPermissions);
        Button btnCheck = getView().findViewById(R.id.btnCheck);
        Button btnCheck2 = getView().findViewById(R.id.btnCheck2);

        final EditText etCheckPermissionName = getView().findViewById(R.id.etName);
        final EditText etCheckPermissionName2 = getView().findViewById(R.id.etName2);
        final EditText etPermission = getView().findViewById(R.id.etPermission);
        final EditText etPermission2 = getView().findViewById(R.id.etPermission2);

        edtUserId = (EditText) getView().findViewById(R.id.etUserIdToDelete);
        etUserName = (EditText) getView().findViewById(R.id.etUserName);
        etRoleId = (EditText) getView().findViewById(R.id.etRoleId);
        etUserNameToSearch = (EditText) getView().findViewById(R.id.etUserNmeToSearch);
        mViewModel.initialize();

        btnListUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.listAllUser();
            }
        });

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = edtUserId.getText().toString();
                if (!TextUtils.isEmpty(userId)) {
                    mViewModel.removeUser(Integer.parseInt(userId));
                }
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.addUser(etUserName.getText().toString(), Integer.parseInt(etRoleId.getText().toString()
                ));
            }
        });

        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.searchUser(etUserNameToSearch.getText().toString());
            }
        });
        btnRoleWithPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.listRoles();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String permissionId = etPermission.getText().toString();
                if (!TextUtils.isEmpty(permissionId)) {
                    mViewModel.checkWebsitePermissionByUsername(
                            etCheckPermissionName.getText().toString(),
                            Integer.parseInt(etPermission.getText().toString())
                    );
                }
            }
        });

        btnCheck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String permission = etPermission2.getText().toString();
                if (!TextUtils.isEmpty(permission)) {
                    mViewModel.checkWebsitePermissionByUsername(
                            etCheckPermissionName2.getText().toString(),
                            etPermission2.getText().toString()
                    );
                }
            }
        });
    }

}