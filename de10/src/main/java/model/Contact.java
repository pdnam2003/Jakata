package model;

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private int groupId;
    private String groupName; // Dùng để hiển thị tên nhóm thay vì chỉ hiện ID số
    private String phoneNumber;

    public Contact() {}

    // Constructor đầy đủ để lấy dữ liệu từ DB
    public Contact(int id, String firstName, String lastName, int groupId, String groupName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
    }

    // Getters và Setters...
}