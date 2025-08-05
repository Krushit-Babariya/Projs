package com.nj.common.messages;

public final class Message {

    public static final String INVALID_CONTENT_TYPE = "Invalid Content Type";
    public static final String GENERIC_ERROR = "Oops something went wong. please try after sometime or contact our support team.";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String DATABASE_ERROR = "Database Error";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    public static final String INVALID_ROLE = "Invalid Role";
    public static final String HANDLER_METHOD_NOT_FOUND = "Requested api is not available";

    private Message() {
    }

    public final class User {

        public static final String USER_ALREADY_EXIST = "User Already Exist";
        public static final String USER_NOT_FOUND = "Ooops..!! User not found";
        public static final String USER_CANNOT_DELETE = "You can not delete manager, many employees are under his/her";

        private User() {
        }
    }

    public final class Database {

        public static String ERROR_OCCUR_WHILE_GETTING_EMPLOYEE_RECORDS = "Error occur while fetching employees record";

        private Database() {
        }
    }

    public final class Constants {

        public static int PAGE_SIZE = 6;
        public static int PAGE = 1;
        public static String DEFAULT_SORT_BY = "employee_id";
        public static String DEFAULT_SORTING_ORDER = "asc";

        private Constants() {
        }
    }

}
