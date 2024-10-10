package com.potatotech.screem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.potatotech.Main;


public class InitialGame implements Screen {

    private SpriteBatch batch;
    private Texture backgroundImage;
    private Main main;
    private Stage stage;
    private Skin skin;


    public InitialGame(Main main) {
        batch = new SpriteBatch();
        backgroundImage = new Texture("gov.jpeg");
        this.main = main;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        TextButton newGameButton = new TextButton("Novo Jogo", skin);
        newGameButton.setSize(200,50);
        newGameButton.setPosition(200, 100);

        TextButton loadGameButton = new TextButton("Carregar Jogo", skin);
        TextButton exitButton = new TextButton("Sair do Jogo", skin);

        newGameButton.addListener(event -> {
            if (newGameButton.isPressed()) {
                System.out.println("Novo Jogo Iniciado");
                this.main.setScreen(new NewGame());
                return true;
            }
            return false;
        });

        loadGameButton.addListener(event -> {
            if (loadGameButton.isPressed()) {
                // Ação para carregar um jogo existente
                System.out.println("Carregar Jogo");
                return true;
            }
            return false;
        });

        exitButton.addListener(event -> {
            if (exitButton.isPressed()) {
                // Ação para sair do jogo
                Gdx.app.exit();
                return true;
            }
            return false;
        });

        Table table = new Table();
        table.setFillParent(true); // Faz a table ocupar a tela inteira
        table.center(); // Centraliza o conteúdo na tela

        table.add(newGameButton).size(200,50).pad(10).row();  // Adiciona com espaçamento
        table.add(loadGameButton).size(200,50).pad(10).row();
        table.add(exitButton).size(200,50).pad(10);

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Desenha a imagem em fullscreen
        batch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
        stage.dispose();
        skin.dispose();
    }
}
