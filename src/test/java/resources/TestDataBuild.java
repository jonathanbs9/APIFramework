package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number("+549 223 456 7899");
        place.setWebsite("https://intranet-frontend-permisos.vercel.app/");
        place.setName(name);

        // Types
        List<String> myList = new ArrayList<String>();
        myList.add("IT ");
        myList.add("Programming");

        place.setTypes(myList);

        // Location
        Location location = new Location();
        location.setLat(-37.9669684);
        location.setLng(-57.5534532);

        place.setLocation(location);

        return place;
    }

    public String deletePlacePayload(String placeID){
        return "{\n" +
                "    \"place_id\" : \""+placeID+"\"\n" +
                "}";
    }
}


