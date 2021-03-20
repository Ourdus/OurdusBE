package ourdus.ourdusspring.dto.user;

public class FindUserRequest {
    private String email;
    private String tel;

    public FindUserRequest() {
    }

    public FindUserRequest(String email, String tel) {
        this.email = email;
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
