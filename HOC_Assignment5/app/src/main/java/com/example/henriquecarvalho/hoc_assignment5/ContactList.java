package com.example.henriquecarvalho.hoc_assignment5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by henriquecarvalho on 15-02-11.
 */
public class ContactList {
    // 	Contact(int id, String name, String address, String phone, String email, String website)
    static Contact[] allContacts = new Contact[] {
            new Contact(1, "Lucas", "123 Someplace, SomewhereVille, CA", "250-123-4567", "maru@maru.net", "http://ntewink.imgd.ca","http://holiveir.imgd.ca/111/a4/img/Lucas1.jpg"),
            new Contact(2, "Lucas Bobao", "42 Answer St., DeepthoughtCity, BC", "250-222-4242", "dont@bother.me", "http://ntewink.imgd.ca/textbook.html","http://holiveir.imgd.ca/111/a4/img/Lucas2.jpg"),
            new Contact(3, "Cavalo", "123 Someplace, SomewhereVille, CA", "250-123-4567", "maru@maru.net", "http://ntewink.imgd.ca","http://holiveir.imgd.ca/111/a4/img/Lucas3.jpg"),
            new Contact(4, "Mario Bobao", "42 Answer St., DeepthoughtCity, BC", "250-222-4242", "dont@bother.me", "http://ntewink.imgd.ca/textbook.html","http://holiveir.imgd.ca/111/a4/img/Lucas4.jpg"),
            new Contact(5, "Lucas Bobao", "123 Someplace, SomewhereVille, CA", "250-123-4567", "maru@maru.net", "http://ntewink.imgd.ca","http://holiveir.imgd.ca/111/a4/img/Lucas4.jpg"),
            new Contact(6, "Thiago V1da l0ka", "42 Answer St., DeepthoughtCity, BC", "250-222-4242", "dont@bother.me", "http://ntewink.imgd.ca/textbook.html","http://holiveir.imgd.ca/111/a4/img/Thiago1.jpg"),
            new Contact(7, "Lucas Again", "123 Someplace, SomewhereVille, CA", "250-123-4567", "maru@maru.net", "http://ntewink.imgd.ca","http://holiveir.imgd.ca/111/a4/img/Lucas4.jpg"),
            new Contact(8, "Lucas' Return", "42 Answer St., DeepthoughtCity, BC", "250-222-4242", "dont@bother.me", "http://ntewink.imgd.ca/textbook.html","http://holiveir.imgd.ca/111/a4/img/Thiago1.jpg"),
            new Contact(9, "L.B.", "123 Someplace, SomewhereVille, CA", "250-123-4567", "maru@maru.net", "http://ntewink.imgd.ca","http://holiveir.imgd.ca/111/a4/img/Lucas4.jpg"),
            new Contact(10,"Rio Pomba", "42 Answer St., DeepthoughtCity, BC", "250-222-4242", "dont@bother.me", "http://ntewink.imgd.ca/textbook.html","http://holiveir.imgd.ca/111/a4/img/Lucas4.jpg")
    };

    public static String[] getAllContactNames() {

        List<String> allNames = new ArrayList<String>();

        for (Contact contact : allContacts) {
            allNames.add(contact.name);
        }

        // Convert to an array of Strings
        String[] allNamesArray = new String[allNames.size()];
        return allNames.toArray(allNamesArray);
    }

    public static Contact[] getAllContacts() {
        return allContacts;
    }

    public static ArrayList<Contact> getArrayListOfContacts() {
        return new ArrayList<Contact>(Arrays.asList(allContacts));
    }


    public static Contact getContactForName(String contactName) {
        Contact result = null;

        if (contactName == null) {
            return null;
        }

        for (Contact contact : allContacts) {

            if (contactName.equalsIgnoreCase(contact.name)) {
                result = contact;
                break;
            }
        }

        return result;
    }

}
