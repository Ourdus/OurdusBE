package ourdus.ourdusspring.dto.user;

public class JoinRequest {
    private String email;
    private String password;
    private String name;
    private String tel;

    public JoinRequest() {
    }

    public JoinRequest(String email, String password, String name, String tel) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
    }

    public String getPassword() {
        return password;
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

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

}
