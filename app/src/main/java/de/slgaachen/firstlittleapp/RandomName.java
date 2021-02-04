package de.slgaachen.firstlittleapp;

public class RandomName {

    private final String imageId;
    private final String randomName;

    public RandomName(String randomName, String imageId){
        this.randomName = randomName;
        this.imageId = imageId;
    }

    public String getImageId(){ return imageId;}
    public String getRandomName(){ return  randomName;}
}
