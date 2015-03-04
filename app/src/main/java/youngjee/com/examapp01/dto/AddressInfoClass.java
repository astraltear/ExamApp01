package youngjee.com.examapp01.dto;

public class AddressInfoClass {
    public int _id;
    public String name;
    public String contact;
    public String email;

    public AddressInfoClass(int _id, String name, String contact, String email) {
        this._id = _id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public int get_id() {
        return _id;
    }
}
