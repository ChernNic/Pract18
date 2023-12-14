package com.example.sqlapp;

import android.content.Context;

import io.paperdb.Paper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String PERSON_BOOK = "persons";

    public DatabaseHelper(Context context) {
        Paper.init(context);
    }

    public void addPerson(Person person) {
        ArrayList<Person> persons = Paper.book().read(PERSON_BOOK, new ArrayList<>());
        persons.add(person);
        Paper.book().write(PERSON_BOOK, persons);
    }

    public ArrayList<Person> getPersonList() {
        return Paper.book().read(PERSON_BOOK, new ArrayList<>());
    }

    public void updatePerson(Person updatedPerson) {
        ArrayList<Person> persons = Paper.book().read(PERSON_BOOK, new ArrayList<>());
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getName().equals(updatedPerson.getName())) {
                persons.set(i, updatedPerson);
                break;
            }
        }
            Paper.book().write(PERSON_BOOK, persons);
        }

    public void deletePerson(String personName) {
        ArrayList<Person> persons = Paper.book().read(PERSON_BOOK, new ArrayList<>());
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getName().equals(personName)) {
                persons.remove(i);
                break;
            }
        }
        Paper.book().write(PERSON_BOOK, persons);
    }
}