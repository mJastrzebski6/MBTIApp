package com.example.mbtifriends;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TypeClass {
    String type;
    String name;
    ArrayList<String> functions;
    ArrayList<Float> population;
    ArrayList<String> strengths;
    ArrayList<String> weaknesses;
    String description;
    String friendships;
    String dating;
    String howToAttract;
    ArrayList<String> careers;
    ArrayList<String> enneagram;
    ArrayList<String> famous;

    public TypeClass(String type, String name, ArrayList<String> functions, ArrayList<Float> population, ArrayList<String> strengths, ArrayList<String> weaknesses, String description, String friendships, String dating, String howToAttract, ArrayList<String> careers, ArrayList<String> enneagram, ArrayList<String> famous) {
        this.type = type;
        this.name = name;
        this.functions = functions;
        this.population = population;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.description = description;
        this.friendships = friendships;
        this.dating = dating;
        this.howToAttract = howToAttract;
        this.careers = careers;
        this.enneagram = enneagram;
        this.famous = famous;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<String> functions) {
        this.functions = functions;
    }

    public ArrayList<Float> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Float> population) {
        this.population = population;
    }

    public ArrayList<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(ArrayList<String> strengths) {
        this.strengths = strengths;
    }

    public ArrayList<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(ArrayList<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFriendships() {
        return friendships;
    }

    public void setFriendships(String friendships) {
        this.friendships = friendships;
    }

    public String getDating() {
        return dating;
    }

    public void setDating(String dating) {
        this.dating = dating;
    }

    public String getHowToAttract() {
        return howToAttract;
    }

    public void setHowToAttract(String howToAttract) {
        this.howToAttract = howToAttract;
    }

    public ArrayList<String> getCareers() {
        return careers;
    }

    public void setCareers(ArrayList<String> careers) {
        this.careers = careers;
    }

    public ArrayList<String> getEnneagram() {
        return enneagram;
    }

    public void setEnneagram(ArrayList<String> enneagram) {
        this.enneagram = enneagram;
    }

    public ArrayList<String> getFamous() {
        return famous;
    }

    public void setFamous(ArrayList<String> famous) {
        this.famous = famous;
    }
}
