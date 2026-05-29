package org.exam.ejb;

import jakarta.ejb.Singleton;

@Singleton
public class VisitorCounterEJB {

    private int count = 0;

    public synchronized int incrementAndGet() {
        count++;
        return count;
    }
}
