import java.util.HashMap;
import java.util.Map;

public class categories {
    String name;
    private HashMap<Integer, String> categories = new HashMap<>();

    public void addSubCategorie(int id, String name){
        categories.put(id, name);
        System.out.println("сабкатегория успешно добавлена!\n");
    }

    int countCategories(HashMap<Integer, String> map){
        int counter = 0;

        System.out.println("количество категорий: ");
        for (Map.Entry<Integer, String> id : map.entrySet()){
            counter++;
        }
        return counter;
    }

    void showCategories(){

    }
}
