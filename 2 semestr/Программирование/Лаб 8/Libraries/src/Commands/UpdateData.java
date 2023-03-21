//package Commands;
//
//import Data.SpaceMarine;
//import Data.SpaceMarines;
//import Data.User;
//
//import java.util.LinkedList;
//
//public class UpdateData extends Command {
//
//    public UpdateData(String name, String description, Class<?>[] argsTypes) {
//        super(name, description, argsTypes);
//    }
//
//    @Override
//    public LinkedList<SpaceMarine> execute(User user, DataBase dataBase, SpaceMarines spaceMarines, Object... args) {
//        LinkedList<SpaceMarine> po=new LinkedList<>();
//        for (SpaceMarine space:spaceMarines){
//           po.add(space);
//        }
//        return po;
//    }
//}
