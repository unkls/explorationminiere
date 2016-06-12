package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minexploration.MEGame;

/**
 * Cette classe sert a l'affichage du menu de depart quand l'utilisateur lance le programme
 * 
 */



public class MainMenuScreen implements Screen {
    
    /**
     * @param batch sert pour ecrire dans une fentre 2D
     * @param playBoutonActif represente le bouton Play actif (fond jaune)
     * @param playBoutonInactif represente le bouton Play inactif (fond blanc)
     * @param exitBoutonActif represente le bouton Exit actif (fond jaune)
     * @param exitBoutonInactif represente le bouton Exit inactif (fond blanc)
     * @param background represente le fond de la fenetre (fond d'ecran)
     */

    private static final int PLAY_BOUTON_WIDTH = 200;
    private static final int PLAY_BOUTON_HEIGHT = 100;
    private static final int EXIT_BOUTON_WIDTH = 200;
    private static final int EXIT_BOUTON_HEIGHT = 100;
    private static final int PLAY_BOUTON_Y = 600;
    private static final int EXIT_BOUTON_Y = 400;
    private MEGame game;  
    private Texture playBoutonActif, playBoutonInactif, exitBoutonActif, exitBoutonInactif, background;
    
    /**
     * Ce constructeur initialise les boutons avec leur images
     * @param game represente le jeu pour agir dessus (le lancer)
     */
    
    public MainMenuScreen (MEGame game){
        this.game = game;
        background = new Texture ("background_menudebut.jpg");
        playBoutonActif = new Texture("bouton_play_actif.png");
        playBoutonInactif = new Texture("bouton_play_inactif.png");
        exitBoutonInactif = new Texture("Bouton_exit_inactif.png");
        exitBoutonActif = new Texture("Bouton_exit_actif.png");   
    }
    

    @Override
    public void show() {
    }
/**
 * Cette methode sert pour l'affichage dans l'ecrans du menu
 * 
 */
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        
        int xPlayBouton = Gdx.graphics.getWidth() / 2 - PLAY_BOUTON_WIDTH / 2;
        
        if(Gdx.input.getX() < xPlayBouton + PLAY_BOUTON_WIDTH && Gdx.input.getX() > xPlayBouton && Gdx.graphics.getHeight() - Gdx.input.getY() < PLAY_BOUTON_Y + PLAY_BOUTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > PLAY_BOUTON_Y ){
            batch.draw(playBoutonActif, xPlayBouton , PLAY_BOUTON_Y, PLAY_BOUTON_WIDTH, PLAY_BOUTON_HEIGHT);
            if(Gdx.input.isTouched()){
                this.dispose();
                game.createGame();
            }
        }else{
            batch.draw(playBoutonInactif, xPlayBouton , PLAY_BOUTON_Y, PLAY_BOUTON_WIDTH, PLAY_BOUTON_HEIGHT);
        }
        
        
        int xExitBouton = Gdx.graphics.getWidth() / 2 - EXIT_BOUTON_WIDTH / 2;

        if(Gdx.input.getX() < xExitBouton + EXIT_BOUTON_WIDTH && Gdx.input.getX() > xExitBouton && Gdx.graphics.getHeight() - Gdx.input.getY() < EXIT_BOUTON_Y + EXIT_BOUTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > EXIT_BOUTON_Y ){
            batch.draw(exitBoutonActif, xExitBouton , EXIT_BOUTON_Y, EXIT_BOUTON_WIDTH, EXIT_BOUTON_HEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }else{
            batch.draw(exitBoutonInactif, xExitBouton , EXIT_BOUTON_Y, EXIT_BOUTON_WIDTH, EXIT_BOUTON_HEIGHT);        
        }
        
        batch.end();
        
    }

    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }
    
    
}
