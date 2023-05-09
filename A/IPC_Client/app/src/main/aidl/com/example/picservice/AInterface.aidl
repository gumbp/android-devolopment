// AInterface.aidl
package com.example.picservice;

// Declare any non-default types here with import statements

interface AInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int plus(int a,int b);
     int sub(int a,int b);
     int mul(int a,int b);
     int div(int a,int b);
}