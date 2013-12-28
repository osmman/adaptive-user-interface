package cz.cvut.fel.aui.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by Tomáš on 27.12.13.
 */
@Startup
@Singleton
public class Initialization
{
    @PostConstruct
    public void deploy(){

    }
}
