package com.potatotech;

import com.badlogic.gdx.Game;
import com.potatotech.screem.InitialGame;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    @Override
    public void create() {
        setScreen(new InitialGame(this));
    }
}
