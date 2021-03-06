package com.lightning.jpipeworks.resources;

import com.lightning.jpipeworks.Engine;
import com.lightning.jpipeworks.things.Thing;

public abstract class Resource implements Runnable {
    public boolean loaded;
    public boolean isLoading;
    public boolean error;
    public Object resource;
    public Thread loadingThread;
    public String filename;
    protected Thing parent;
    protected Engine engine;
    
    public Resource(Thing parent, String filename, Engine engine) {
        loadingThread = new Thread(this);
        loadingThread.start();
        this.filename = filename;
        this.parent = parent;
        this.engine = engine;
    }
    
    public void run() {
        synchronized(this) {
            try { wait(); } catch(InterruptedException e) {}
        }
        isLoading = true;
        load(filename);
        loaded = true;
        Engine.numLoadThreads--;
    }
    
    public abstract void load(String filename);
    
    public abstract void free();
}
