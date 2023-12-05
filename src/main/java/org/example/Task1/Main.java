package org.example.Task1;

public class Main {
    public static void main(String[] args) {
        ExtensionThread extensionThread = new ExtensionThread();
        extensionThread.start();
        extensionThread.start();
    }
}