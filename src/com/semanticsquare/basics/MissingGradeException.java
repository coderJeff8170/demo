package com.semanticsquare.basics;

public class MissingGradeException extends Exception {
    int studentId;

    public MissingGradeException(int studentId) {
        super("Grade missing for student " + studentId);
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
