package com.android.rbac.ui.main;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.rbaclib.RBACHandler;
import com.android.rbaclib.db.enitity.Role;
import com.android.rbaclib.db.enitity.RoleWithPermissions;
import com.android.rbaclib.db.enitity.User;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private RBACHandler rbacHandler;
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void initialize(){
        rbacHandler = RBACHandler.getInstance(getApplication().getApplicationContext());
    }

    public void addUser(String name, int role_id){
        if(!TextUtils.isEmpty(name)) {
            rbacHandler.addUser(name, role_id);
        }
    }

    public void removeUser(int userId){
        rbacHandler.deleteUser(userId);
    }

    public void searchUser(String searchString){
        rbacHandler.searchUser(searchString);
    }

    public List<User> listAllUser(){
        return rbacHandler.allUsers();
    }

    public List<RoleWithPermissions> listRoles(){
        return rbacHandler.listRolesWithPermissions();
    }

    public boolean checkWebsitePermissionByUsername(String name, int websiteId){
        return rbacHandler.checkWebsitePermissionByUsername(name, websiteId);
    }

    public boolean checkWebsitePermissionByUsername(String name, String website){
        return rbacHandler.checkWebsitePermissionByUsername(name, website);
    }
}