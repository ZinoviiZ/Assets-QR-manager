package sand_box.tables.login;

/**
 * Created by Zinoviy on 9/5/16.
 */
public enum User_Role {
    EDITOR, ADMIN;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
