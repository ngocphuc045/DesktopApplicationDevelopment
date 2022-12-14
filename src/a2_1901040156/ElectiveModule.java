package a2_1901040156;

public class ElectiveModule extends Module {
    private String departmentName;

    public ElectiveModule(String code, String name, int semester, int credits, String departmentName) throws Exception {
        super(code, name, semester, credits);
        if (!validateDepartmentName(departmentName)) {
            throw new Exception("Invalid departmentName!");
        } else {
            this.departmentName = departmentName;
        }
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        if (!validateDepartmentName(departmentName)) {
            return;
        }
        this.departmentName = departmentName;
    }

    private boolean validateDepartmentName(String departmentName) {
        return departmentName.length() > 0;
    }
}
