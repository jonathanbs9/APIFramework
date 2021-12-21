package resources;

/*** Enum is a special class in java which has collections of constant and methods***/
public enum ApiResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("maps/api/place/delete/json");
    private String resource;

    ApiResources(String resource) {
        this.resource=resource;
    }

    public String GetResource(){
        return resource;
    }
}
