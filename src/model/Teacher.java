package model;


import java.util.List;

public class Teacher {

    private String id;
    private String account;
    private String password;
    private String email;
    private String name;
    private String sex;
    private String img;
    private List<Class> classList;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public String getClassListString() {
        if(classList.isEmpty()) {
            return "占位班级";
        }
        String classListString = "";
        for (Class c : classList) {
            classListString += c.getName() + "|";
        }
        return classListString.substring(0, classListString.length() - 1);
    }
}
