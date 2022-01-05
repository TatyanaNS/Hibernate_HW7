package com.goit;

import com.goit.config.DbMigration;

public class App {
    public static void main( String[] args ) {
        DbMigration.migrate();
        }
    }