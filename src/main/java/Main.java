import view.MainLogic;

public class Main {


    public static void main(String[] args) {

        try {
            MainLogic mainLogic = new MainLogic();
            mainLogic.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
