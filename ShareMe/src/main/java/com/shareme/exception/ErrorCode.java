package com.shareme.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategories error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXITED(1001, "User exited",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOTEXITED(1003,"User notexited",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1004, "INVALID KEY ERROR", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1005, "INVALID PASSWORD ERROR", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006,"UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(10007, "You do not have permission", HttpStatus.FORBIDDEN ),
    TOKEN_NOTEXITED(10008, "Your token does not exist", HttpStatus.BAD_REQUEST ),
    CATEGORIES_EXITED(1009, "Categories exited",HttpStatus.BAD_REQUEST),
    STORE_NOTEXITED(1010, "Store not exited",HttpStatus.BAD_REQUEST),
    CATEGORY_NOTEXITED(1011, "Category not exited",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOTEXITED(1012, "Employee not exited",HttpStatus.BAD_REQUEST),
    ROLE_NOTEXITED(1013, "Role not exited",HttpStatus.BAD_REQUEST),
    FOLLOWSTORE_NOTEXITED(1014, "Follow store not exited",HttpStatus.BAD_REQUEST),
    FOLLOWUSER_NOTEXITED(1015, "Follow user not exited",HttpStatus.BAD_REQUEST),
    LIKEITEMS_NOTEXITED(1016, "Like item not exited",HttpStatus.BAD_REQUEST),
    ORDERS_NOTEXITED(1017, "Order not exited",HttpStatus.BAD_REQUEST),
    MENUITEMS_NOTEXITED(1018, "Menu item not exited",HttpStatus.BAD_REQUEST),
    REPORT_NOTEXITED(1019, "Report not exited",HttpStatus.BAD_REQUEST),
    TAGS_NOTEXITED(1020, "Tags not exited",HttpStatus.BAD_REQUEST),

    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
