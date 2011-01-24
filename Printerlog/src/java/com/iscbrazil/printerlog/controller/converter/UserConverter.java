package  controller.converter;

import  model.factory.Factory;
import  model.interfaces.UserDAO;
import  controller.pojo.User;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        UserDAO userDAO = Factory.createUserDAO();
        User user = userDAO.find(string);
        return user;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        User user = new User();
        user = (User) o;
        return user.getName();
    }
}
