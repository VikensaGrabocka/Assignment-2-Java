package calendarprocessing;

public class Date  {
    private String date;

    public Date(String date) {
        try{
            if(date.split("/").length!=3){
                throw new IllegalArgumentException();
            }
            this.date = date;
        }catch (IllegalArgumentException e){
            System.out.println("Incorrect date format. Please provide the date in the " +
                    "dd/mm/yyyy format.");
            this.date = "-1/-1/-1";
        }
    }

    public int getDay() {
        return extractFromStringToInt()[0];
    }

    public int getYear(){
        return extractFromStringToInt()[2];
    }

    public int getMonth(){
        return extractFromStringToInt()[1];
    }

    private int[] extractFromStringToInt(){
        String[] dateParts = date.split("/");
        int[] dateInt = new int[3];
        for(int i = 0; i < dateParts.length; i++){
            dateInt[i] = Integer.parseInt(dateParts[i]);
        }
        return dateInt;
    }

    @Override
    public String toString() {
        return  date;

    }
}
