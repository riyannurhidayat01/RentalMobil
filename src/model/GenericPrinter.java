/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class GenericPrinter {
    public static <T> void printList(List<? extends T> list) {
        for (T item : list) {
            System.out.println(item.toString());
        }
    }
}

