public static boolean isLeapYear(int year) {

    LocalDate localDate = LocalDate.of(year, Month.JULY, 1);
    
    return localDate.isLeapYear();

}
