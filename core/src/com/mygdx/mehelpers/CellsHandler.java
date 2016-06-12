/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.mehelpers;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.mygdx.gameobjects.Mineur;
import com.mygdx.gameobjects.Mineur.Direction;
import com.mygdx.gameobjects.Mineur.Etat;
import com.mygdx.mehelpers.Deplacement.Fluide;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Hugo
 */
public class CellsHandler {
    private boolean victory, cassageEnCour;
    private final Mineur mineur;
    private final TiledMapTileLayer layerSurface, layerObjets;
    private final boolean[] cellsSAM; // CellsSurfaceAroundMineur
    
    /**
     *
     * @param mineur
     */
    public CellsHandler(Mineur mineur) {
        this.mineur = mineur;
        this.victory = false;
        this.layerSurface = (TiledMapTileLayer) mineur.getMap().getLayers().get("surface");
        this.layerObjets =  (TiledMapTileLayer) mineur.getMap().getLayers().get("objets");
        this.cellsSAM = new boolean[4];  
    }
    
    /**
     *
     * @return
     */
    public boolean isVictory(){
        return this.victory;
    }
    
    // On lit les cell près du mineur et on renvoit un tableau de boolean si elle sont pleines ou pas

    /**
     *
     */
    public void getCellsSurfaceAroundMineur() {
        int y = (int) mineur.getPosition().y;
        int x = (int) mineur.getPosition().x;
        
        //   0
        // 3 M 1
        //   2
        if(layerSurface.getCell(x, y+1)!=null)
            cellsSAM[0] = true;
        else
            cellsSAM[1] = false;  
        
        if(layerSurface.getCell(x+1, y)!=null) {
            float distanceADroite = x+1 - (mineur.getPosition().x + mineur.getLARGEUR());
            if(distanceADroite < 0.1 && distanceADroite > 0) cellsSAM[1] = true;;
        }
        else
            cellsSAM[1] = false;
        
        if(layerSurface.getCell(x, y-1)!=null)
            cellsSAM[2] = true;
        else
            cellsSAM[2] = false;
        
        if(layerSurface.getCell(x-1, y)!=null) {
            float distanceAGauche = mineur.getPosition().x - x;
            if(distanceAGauche < 0.1 && distanceAGauche > 0) cellsSAM[3] = true;
        }
        else
            cellsSAM[3] = false;   
    }
    
    /**
     *
     * @return
     */
    public boolean[] getCellsSAM() {
        return cellsSAM;
    }
    
    private boolean isLadderHere(int x,int y){
        if(layerObjets.getCell(x, y) != null){
            if(layerObjets.getCell(x,y).getTile().getId() == 5) return true;
        }
        return false;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void setLadder(int x,int y){
        Cell cell = new Cell();
        TiledMapTileSet tileSet = mineur.getMap().getTileSets().getTileSet("ladder");
        cell.setTile(tileSet.getTile(5));
        layerObjets.setCell(x, y, cell);
    }
    
    private boolean isCellSurfaceHere(int x, int y) {
        return layerSurface.getCell(x, y) != null;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void destructionBloc(int x, int y) {  
        final int xBloc = x;
        final int yBloc = y;
        if(isCellSurfaceHere(x, y) && !cassageEnCour) {
            checkVictory(x, y);
            mineur.setEtatMineur(Etat.Miner);
            mineur.setDirectionMineur(Direction.Arret);
            cassageEnCour = true;
            System.out.println("Je vais casser le bloc en mode " + mineur.getTypeDeplacement().getClass().getName());
            //mineur.setTypeDeplacement(new Fluide(mineur));
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    layerSurface.setCell(xBloc, yBloc, null);
                    mineur.setEtatMineur(Etat.Arret);
                    mineur.setDirectionMineur(Direction.Arret);
                    cassageEnCour = false;
                }
            }, 1000);
        }
    } 
    
    /**
     *
     * @param x
     * @param y
     */
    public void checkVictory (int x, int y){
        if(layerSurface.getCell(x, y).getTile().getId() == 4)
            victory = true;
    }
    
    
}