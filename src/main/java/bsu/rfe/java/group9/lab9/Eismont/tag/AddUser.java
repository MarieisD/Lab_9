package bsu.rfe.java.group9.lab9.Eismont.tag;

import bsu.rfe.java.group9.lab9.Eismont.entity.User;
import bsu.rfe.java.group9.lab9.Eismont.entity.UserList;
import bsu.rfe.java.group9.lab9.Eismont.entity.UserListHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class AddUser extends SimpleTagSupport {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);

        if (user.getLogin()==null || user.getLogin().equals("")) {
            errorMessage = "Login cannot be empty!";

        } else {
            if (user.getName()==null || user.getName().equals("")) {
                errorMessage = "Username cannot be empty!";
            }
        }

        if (errorMessage==null) {
            try {
                userList.addUser(user);
                UserListHelper.saveUserList(userList);
            } catch (UserList.UserExistsException e) {
                errorMessage = "A user with this login already exists!";
            }
        }

        getJspContext().setAttribute("errorMessage", errorMessage,PageContext.SESSION_SCOPE);
    }

}