package com.example.mybankapplication.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String title;
    private int status;
    private String detail;
    private Date timeStamp;
    private String path;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<>();
}
