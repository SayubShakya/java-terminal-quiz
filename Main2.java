public class Main2 {

    public static void main(String[] args) {
        

        Integer[] numbers = new Integer[] { 1, 2, 3,4 ,5,6};


        Integer[] numbers2 = new Integer[5];

        for (int i = 0; i < numbers.length; i++){
            numbers2[i] = numbers[i];
        }
        for (int i = 0; i < numbers2.length; i++){
        System.out.println(numbers2[i]);
        }




    }
}