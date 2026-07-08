package com.cafe.uas;

import view.TampilMenuFrame;

public class Uas {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TampilMenuFrame().setVisible(true);
        });
    }
}