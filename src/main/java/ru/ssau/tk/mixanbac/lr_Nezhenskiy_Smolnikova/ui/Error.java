package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;

import javax.swing.*;
import java.awt.*;

public class Error {
    Error(Component parent, Exception e) {
        showError(parent, e);
    }

    public void showError(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Error!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Expected: Number, Found: String";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Array is not sorted";
        }
        return "Unknown error";
    }
}
