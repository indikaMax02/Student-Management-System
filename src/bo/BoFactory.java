package bo;


import bo.custom.impl.ManageCourseBOImpl;
import bo.custom.impl.ManageStudentBOImpl;
import bo.custom.impl.RegisterStudentBOImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case REGISTER_STUDENT:
                return new RegisterStudentBOImpl();
            case COURSE:
                return new ManageCourseBOImpl();
            case MANAGE_STUDENT:
                return new ManageStudentBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        REGISTER_STUDENT,COURSE,MANAGE_STUDENT
    }
}
