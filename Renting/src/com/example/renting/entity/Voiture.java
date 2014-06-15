package com.example.renting.entity;

import java.io.Serializable;

public class Voiture implements Serializable{
	private int id ;
	private String marque ;
	private String modele;
	private String type;
	private int nbPlace;
	private boolean automatique;
	private float surface;
	private String icone;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNbPlace() {
		return nbPlace;
	}
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	public boolean isAutomatique() {
		return automatique;
	}
	public void setAutomatique(boolean automatique) {
		this.automatique = automatique;
	}
	public float getSurface() {
		return surface;
	}
	public void setSurface(float surface) {
		this.surface = surface;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	
}
